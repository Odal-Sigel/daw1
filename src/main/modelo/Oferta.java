package main.modelo;

import java.time.LocalDate;
import java.util.ArrayList;

import main.modelo.excepciones.respuestaVaciaException;

public class Oferta {
	private String puestoOfertado;
	private Empresa empresa;
	private String localidad;
	private String descripcion;
	private LocalDate fechaCreacion;
	private ArrayList<Demandante> listaInscritos;
	private ArrayList<Pregunta> listaPreguntas;

	public Oferta(String puestoOfertado, Empresa empresa, String localidad, String descripcion) {
		this.puestoOfertado = puestoOfertado;
		this.empresa = empresa;
		this.localidad = localidad;
		this.descripcion = descripcion;
		fechaCreacion = LocalDate.now();

		listaInscritos = new ArrayList<Demandante>();
		listaPreguntas = new ArrayList<Pregunta>();
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

	public LocalDate getFechaCreacion() {
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
		} catch (respuestaVaciaException ex) {
			respuesta = ex.getMessage();
		}

		String datos = pregunta + " - " + respuesta;
		return datos;
	}

	public ArrayList<Demandante> getListaDemandantesInscritos() {
		return listaInscritos;
	}

	public void addDemandanteInscrito(Demandante demandante) {
		listaInscritos.add(demandante);
	}

	public int getNumInscritos() {
		int numInscritos = listaInscritos.size();
		return numInscritos;
	}
}
