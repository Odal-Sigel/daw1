package main.modelo;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import main.modelo.excepciones.DemandanteYaInscritoException;
import main.modelo.excepciones.RespuestaVaciaException;

public class Oferta {
	private int id;
	private String puestoOfertado;
	private Empresa empresa;
	private String localidad;
	private String descripcion;
	private Date fechaCreacion;
	private ArrayList<Demandante> listaInscritos;
	private ArrayList<Pregunta> listaPreguntas;

	public Oferta(int id, String puestoOfertado, Empresa empresa, String localidad, String descripcion) {
		this.id = id;
		this.puestoOfertado = puestoOfertado;
		this.empresa = empresa;
		this.localidad = localidad;
		this.descripcion = descripcion;
		fechaCreacion = Date.valueOf(LocalDate.now());

		listaInscritos = new ArrayList<Demandante>();
		listaPreguntas = new ArrayList<Pregunta>();
	}

	public Oferta(int id, String puestoOfertado, Empresa empresa, String localidad, String descripcion,
			Date fechaCreacion) {
		this.id = id;
		this.puestoOfertado = puestoOfertado;
		this.empresa = empresa;
		this.localidad = localidad;
		this.descripcion = descripcion;
		this.fechaCreacion = fechaCreacion;

		listaInscritos = new ArrayList<Demandante>();
		listaPreguntas = new ArrayList<Pregunta>();
	}

	public int getID() {
		return id;
	}

	public String getPuestoOfertado() {
		return puestoOfertado;
	}

	public String getDatosEmpresa() {
		String datos = empresa.getNombre() + " - " + empresa.getNIF();
		return datos;
	}

	public String getLocalidad() {
		return localidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public ArrayList<Pregunta> getListaPreguntas() {
		return listaPreguntas;
	}

	public void responderPregunta(int indicePregunta, String respuesta) {
		listaPreguntas.get(indicePregunta).setRespuesta(respuesta);
	}

	public void addPregunta(Pregunta pregunta) {
		listaPreguntas.add(pregunta);
	}

	public String getDatosPregunta(int indicePregunta) {
		String pregunta = listaPreguntas.get(indicePregunta).getContenido();
		String respuesta;

		try {
			respuesta = listaPreguntas.get(indicePregunta).getRespuesta();
		} catch (RespuestaVaciaException ex) {
			respuesta = ex.getMessage();
		}

		String datos = pregunta + " - " + respuesta;
		return datos;
	}

	public ArrayList<Demandante> getListaDemandantesInscritos() {
		return listaInscritos;
	}

	public void addDemandanteInscrito(Demandante demandante) throws DemandanteYaInscritoException {
		if (listaInscritos.contains(demandante)) {
			throw new DemandanteYaInscritoException();
		} else {
			listaInscritos.add(demandante);
		}
	}

	public int getNumInscritos() {
		int numInscritos = listaInscritos.size();
		return numInscritos;
	}
}
