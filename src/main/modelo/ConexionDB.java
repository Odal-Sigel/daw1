package main.modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import main.modelo.excepciones.DemandanteYaInscritoException;
import main.modelo.excepciones.NifNoValidoException;
import main.modelo.excepciones.RespuestaVaciaException;
import main.modelo.excepciones.UsuarioNoValidoException;

public class ConexionDB {
	private Connection con;
	private final int USUARIO_DEMANDANTE = 1;
	private final int USUARIO_EMPRESA = 2;

	public ConexionDB(String url, String user, String pass) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
	}

	public Usuario inicioDeSesion(String usuario, String contraseña) throws UsuarioNoValidoException {
		Usuario userU = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT ID, TIPO FROM usuario WHERE NICKNAME='" + usuario
					+ "' AND PASSWORD='" + contraseña + "'");

			rs.next(); // Mover el puntero al primer valor
			int id = rs.getInt("ID");
			int tipo = rs.getInt("TIPO");
			rs.close(); // Cerrar el result set

			if (tipo == USUARIO_EMPRESA) {
				ResultSet rsE = st.executeQuery("SELECT NOMBRE, NIF FROM empresa WHERE ID=" + id);

				rsE.next();
				String nombre = rsE.getString(1);
				String nif = rsE.getString(2);
				rsE.close();

				try {
					userU = new Empresa(usuario, contraseña, nombre, nif);
				} catch (NifNoValidoException ex) {
					System.out.println(ex.getMessage());
				}
			} else if (tipo == USUARIO_DEMANDANTE) {
				ResultSet rsD = st.executeQuery(
						"SELECT NOMBRE, APELLIDO1, APELLIDO2, TIMESTAMPDIFF(YEAR, FECHANAC, CURDATE()) FROM demandante WHERE ID="
								+ id);

				rsD.next();
				String nombre = rsD.getString(1);
				String apellido = rsD.getString(2);
				if (!rsD.getString(3).equalsIgnoreCase("NULL")) {
					apellido = apellido + " " + rsD.getString(3);
				}
				int edad = rsD.getInt(4);
				rsD.close();

				userU = new Demandante(usuario, contraseña, nombre, apellido, edad);
			} else {
				// TODO: implementar gestión usuario Administrador
				throw new UsuarioNoValidoException();
			}

			// Cerrar el statement
			st.close();
		} catch (SQLException ex) {
			throw new UsuarioNoValidoException();
		}

		return userU;
	}

	public int siguienteIDOferta() {
		int id = 0;
		try {
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("SELECT MAX(ID) FROM oferta");
			rs.next();
			id = rs.getInt(1) + 1;

			rs.close();
			st.close();
		} catch (Exception e) {
			id = -1;
		}
		return id;
	}

	public int siguienteIDPregunta() {
		int id = 0;
		try {
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("SELECT MAX(ID) FROM pregunta");
			rs.next();
			id = rs.getInt(1) + 1;

			rs.close();
			st.close();
		} catch (Exception e) {
			id = -1;
		}
		return id;
	}

	public void sacarOfertas(Empresa empresa) {
		try {
			Statement st = con.createStatement();

			// Sacar ID de la empresa
			int id = sacarID(empresa);

			// Sacar las ofertas de esa empresa
			ResultSet rsO = st.executeQuery(
					"SELECT * FROM oferta WHERE EMPRESA='" + id
							+ "'");
			while (rsO.next()) {
				int idOferta = rsO.getInt(1);
				String puesto = rsO.getString(2);
				rsO.getInt(3); // ID empreas -> No útil
				String localidad = rsO.getString(4);
				String descripcion = rsO.getString(5);
				Date fechaCreacion = rsO.getDate(6);
				empresa.crearOferta(idOferta, puesto, localidad, descripcion, fechaCreacion);
			}
			rsO.close();
			st.close();
		} catch (SQLException ex) {
			System.out.println("Error al cargar las ofertas");
		}
	}

	public void sacarPreguntas(Empresa empresa) {
		try {
			Statement st = con.createStatement();

			// Sacar ID de la empresa
			int id = sacarID(empresa);

			// Sacar los demandantes que han hecho preguntas
			ResultSet rsD = st.executeQuery(
					"SELECT DISTINCT usuario.ID, usuario.NICKNAME, usuario.`PASSWORD`, demandante.NOMBRE, demandante.APELLIDO1, demandante.APELLIDO2, TIMESTAMPDIFF(YEAR, FECHANAC, CURDATE()) FROM pregunta JOIN oferta ON pregunta.OFERTA = oferta.ID JOIN demandante ON pregunta.DEMANDANTE = demandante.ID JOIN usuario ON demandante.ID = usuario.ID WHERE oferta.EMPRESA = "
							+ id);
			while (rsD.next()) {
				int idDemandante = rsD.getInt(1);
				String nickName = rsD.getString(2);
				String contraseña = rsD.getString(3);
				String nombre = rsD.getString(4);
				String apellidos = rsD.getString(5);
				if (!rsD.getString(6).equalsIgnoreCase("NULL")) {
					apellidos = apellidos + " " + rsD.getString(6);
				}
				int edad = rsD.getInt(7);
				Demandante dem = new Demandante(nickName, contraseña, nombre, apellidos, edad);

				// Sacar las preguntas que ha hecho cada demandante a cada oferta
				ResultSet rsOferta = st.executeQuery("SELECT ID FROM oferta WHERE empresa=" + id);
				rsOferta.next();
				for (int i = 0; i < empresa.getListaOfertas().size(); i++) {
					ResultSet rsP = st.executeQuery("SELECT ID, CONTENIDO, RESPUESTA FROM pregunta WHERE OFERTA = "
							+ rsOferta.getInt(1) + " AND DEMANDANTE=" + idDemandante);

					while (rsP.next()) {
						String respuesta = rsP.getString(3);
						if (respuesta == null) {
							dem.realizarPregunta(rsP.getInt(1), empresa.getListaOfertas().get(i), rsP.getString(2));
						} else {
							dem.realizarPregunta(rsP.getInt(1), empresa.getListaOfertas().get(i), rsP.getString(2),
									rsP.getString(3));
						}
					}
					rsP.close();

					rsOferta.next();
				}
				rsOferta.close();
			}
			rsD.close();

			st.close();
		} catch (SQLException ex) {
			System.out.println("Error al cargar las pregutnas");
		}
	}

	public void crearOferta(Empresa empresa) {
		try {
			Statement st = con.createStatement();

			int ultimaOferta = empresa.getListaOfertas().size() - 1;
			Oferta oferta = empresa.getListaOfertas().get(ultimaOferta);

			// Sacar id de la empresa
			int id = sacarID(empresa);

			// Crear la oferta
			st.executeUpdate(
					"INSERT INTO oferta VALUES('" + oferta.getID() + "','" + oferta.getPuestoOfertado() + "'," + id
							+ ",'" + oferta.getLocalidad() + "','"
							+ oferta.getDescripcion() + "','" + oferta.getFechaCreacion()
							+ "')");
			st.close();
		} catch (SQLException ex) {
			System.out.println("Error al crear la nueva oferta");
		}
	}

	public void contestarPregunta(Empresa empresa, int indiceOferta, int indicePregunta) {
		try {
			Statement st = con.createStatement();

			// Sacar el id de la pregunta
			Pregunta pregunta = empresa.getListaOfertas().get(indiceOferta).getListaPreguntas().get(indicePregunta);
			int id = pregunta.getID();

			// responder pregunta
			try {
				st.executeUpdate("UPDATE pregunta SET RESPUESTA = '" + pregunta.getRespuesta() + "' WHERE ID =" + id);
			} catch (RespuestaVaciaException e) {
				st.executeUpdate("UPDATE pregunta SET RESPUESTA = '" + "" + "' WHERE ID =" + id);
			}

			st.close();
		} catch (SQLException ex) {
			System.out.println("Error al contestar la pregunta");
		}
	}

	public ArrayList<Oferta> rellenarListaOfertas() {
		ArrayList<Oferta> lista = new ArrayList<Oferta>();
		try {
			Statement st = con.createStatement();

			// Sacar las empresas que tienen ofertas
			ResultSet rs = st.executeQuery("SELECT DISTINCT EMPRESA FROM oferta");
			while (rs.next()) {
				int id = rs.getInt(1);

				ResultSet rsE = st.executeQuery(
						"SELECT NICKNAME, PASSWORD, NOMBRE, NIF FROM usuario JOIN empresa ON usuario.ID = empresa.ID WHERE usuario.ID = "
								+ id);

				rsE.next();
				try {
					Empresa empresa = new Empresa(rsE.getString(1), rsE.getString(2), rsE.getString(3),
							rsE.getString(4));

					sacarOfertas(empresa);
					sacarPreguntas(empresa);

					for (int i = 0; i < empresa.getListaOfertas().size(); i++) {
						lista.add(empresa.getListaOfertas().get(i));
					}
				} catch (NifNoValidoException e) {
					e.printStackTrace();
				}
				rsE.close();
			}

			rs.close();
		} catch (SQLException e) {
			System.out.println("Error al rellenar la lista de ofertas");
		}
		return lista;
	}

	public void realizarPregunta(Demandante demandante) {
		try {
			Statement st = con.createStatement();

			// Sacar el id de la pregunta
			int ultima = demandante.getListaPreguntasHechas().size() - 1;
			Pregunta pregunta = demandante.getListaPreguntasHechas().get(ultima);

			// realizar pregunta
			st.executeUpdate(
					"INSERT INTO pregunta VALUES(" + pregunta.getID() + "," + pregunta.getIDOferta() + ","
							+ sacarID(demandante) + ",'" + pregunta.getContenido() + "', NULL)");
			st.close();
		} catch (SQLException ex) {
			System.out.println("Error al realizar la pregunta");
		}
	}

	public void inscribirseOferta(Oferta oferta, Demandante demandante) throws DemandanteYaInscritoException {
		try {
			Statement st = con.createStatement();

			// Sacar el id de la oferta y del demandante
			int idOferta = oferta.getID();
			int idDemandante = sacarID(demandante);

			// ver si ya está inscrito
			ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM oferta_inscritos WHERE OFERTA=" + idOferta
					+ " AND DEMANDANTE=" + idDemandante);
			rs.next();
			int cuenta = rs.getInt(1);
			if (cuenta != 0) {
				throw new DemandanteYaInscritoException();
			}

			st.executeUpdate("INSERT INTO oferta_inscritos VALUES(" + idOferta + "," + idDemandante + ")");

			st.close();
		} catch (SQLException ex) {
			System.out.println("Error al inscribirse a una oferta");
		}
	}

	public void cerrarConexion() throws SQLException {
		con.close();
	}

	private int sacarID(Usuario usuario) {
		int id = 0;
		try {
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("SELECT ID FROM usuario WHERE NICKNAME='" + usuario.getNickName() + "'");
			rs.next();
			id = rs.getInt(1);

			st.close();
		} catch (SQLException e) {
			id = -1;
		}

		return id;
	}
}
