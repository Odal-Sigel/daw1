package main.modelo;

public class Demandante extends Usuario {
	private String apellidos;
	private int edad;

	public Demandante(int usuarioID, String nombre, String apellidos, int edad) {
		super(usuarioID, nombre);
		this.apellidos = apellidos;
		this.edad = edad;
	}

	// Getters
	public String getApellidos() {
		return apellidos;
	}

	public int getEdad() {
		return edad;
	}

	// Métodos
}
