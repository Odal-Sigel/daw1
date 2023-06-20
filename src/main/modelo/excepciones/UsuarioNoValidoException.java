package main.modelo.excepciones;

public class UsuarioNoValidoException extends Exception {
	public UsuarioNoValidoException() {
		super("Usuario no valido");
	}
}
