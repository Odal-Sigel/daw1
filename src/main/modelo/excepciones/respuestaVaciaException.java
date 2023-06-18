package main.modelo.excepciones;

public class respuestaVaciaException extends Exception {
	public respuestaVaciaException() {
		super("Vaya, aún no ha sido respondida la pregunta");
	}
}
