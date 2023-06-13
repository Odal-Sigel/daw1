package main.modelo;

public abstract class Usuario {
	private String nickName;
	private String contraseña;

	public Usuario(String nickName, String contraseña) {
		this.nickName = nickName;
		this.contraseña = contraseña;
	}

	// Métodos
	public abstract void iniciarSesion(String nickName, String contraseña);
}
