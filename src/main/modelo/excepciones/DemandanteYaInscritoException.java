package main.modelo.excepciones;

public class DemandanteYaInscritoException extends Exception {
	public DemandanteYaInscritoException() {
		super("Ya estás inscrito a esta oferta");
	}
}
