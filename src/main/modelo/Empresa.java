package main.modelo;

public class Empresa extends Usuario {
	private String nif;

	public Empresa(int usuarioID, String nombre, String nif) {
		super(usuarioID, nombre);
		this.nif = nif;
	}

	// Getters
	public String getNif() {
		return nif;
	}

	// Métodos
}
