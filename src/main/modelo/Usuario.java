package main.modelo;

public class Usuario {
	protected String nickName;
	protected String contraseña;

	public Usuario(String nickName, String contraseña) {
		this.nickName = nickName;
		this.contraseña = contraseña;
	}

	// Métodos
	public String getNickName() {
		return nickName;
	}
	
	public String getContraseña() {
		return contraseña;
	}
}
