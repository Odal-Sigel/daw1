package main.modelo;

public class Usuario {
	private int usuarioID;
	private String nombre;

	public Usuario(int usuarioID, String nombre) {
		this.usuarioID = usuarioID;
		this.nombre = nombre;
	}

	// Getters
	public int getUsuarioID() {
		return usuarioID;
	}

	public String getNombre() {
		return nombre;
	}
}
