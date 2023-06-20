package main.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.modelo.excepciones.nifNoValidoException;

public class ConexionDB {
	private Connection con;
	private final int USUARIO_DEMANDANTE = 1;
	private final int USUARIO_EMPRESA = 2;

	public ConexionDB(String url, String user, String pass) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
	}

	public boolean inicioDeSesion(String usuario, String contraseña, Usuario userU) {
		boolean esValido = true;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT ID, TIPO FROM usuario WHERE NICKNAME='" + usuario
					+ "' AND PASSWORD=SHA2('" + contraseña + "',256)");

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
				} catch (nifNoValidoException ex) {
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
				esValido = false;
			}

			// Cerrar el statement
			st.close();
		} catch (SQLException ex) {
			esValido = false;
		}

		return esValido;
	}

	public void cerrarConexion() throws SQLException {
		con.close();
	}
}
