package main.modelo;

import main.modelo.excepciones.respuestaVaciaException;

public class Pregunta {
	private Demandante demandante;
	private String contenido;
	private String respuesta;

	public Pregunta(Oferta oferta, Demandante demandante, String contenido) {
		oferta.addPregunta(this);
		this.demandante = demandante;
		this.contenido = contenido;
	}

	public String getDatosDemandante() {
		String datos = demandante.getNombreCompleto();
		return datos;
	}

	public String getContenido() {
		return contenido;
	}

	public String getRespuesta() throws respuestaVaciaException {
		if (respuesta == null) {
			throw new respuestaVaciaException();
		} else {
			return respuesta;
		}
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
}
