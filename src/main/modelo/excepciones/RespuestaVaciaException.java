package main.modelo.excepciones;

public class RespuestaVaciaException extends Exception {
	public RespuestaVaciaException() {
		super("Vaya, aún no ha sido respondida la pregunta");
	}
}
