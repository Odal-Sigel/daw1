package main.modelo;

import main.modelo.excepciones.RespuestaVaciaException;

public class Pregunta {
	private int id;
	private Demandante demandante;
	private String contenido;
	private String respuesta;
	private Oferta oferta;

	public Pregunta(int id, Oferta oferta, Demandante demandante, String contenido) {
		this.oferta = oferta;
		this.id = id;
		oferta.addPregunta(this);
		this.demandante = demandante;
		this.contenido = contenido;
	}

	public int getID() {
		return id;
	}

	public int getIDOferta() {
		return oferta.getID();
	}

	public String getDatosDemandante() {
		String datos = demandante.getNombreCompleto();
		return datos;
	}

	public String getContenido() {
		return contenido;
	}

	public String getRespuesta() throws RespuestaVaciaException {
		if (respuesta == null) {
			throw new RespuestaVaciaException();
		} else {
			return respuesta;
		}
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public Demandante getDemandante() {
		return demandante;
	}
}
