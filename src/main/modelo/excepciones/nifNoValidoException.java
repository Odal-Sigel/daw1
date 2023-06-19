package main.modelo.excepciones;

public class nifNoValidoException extends Exception {
	public nifNoValidoException() {
		super("El NIF ingresado no es válido");
	}
}
