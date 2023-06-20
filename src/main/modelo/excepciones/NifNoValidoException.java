package main.modelo.excepciones;

public class NifNoValidoException extends Exception {
	public NifNoValidoException() {
		super("El NIF ingresado no es válido");
	}
}
