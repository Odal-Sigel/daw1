package test.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.modelo.Usuario;

class UsuarioTest {

	@Test
	void pruebaConstructor() {
		String nickName = "Prueba";
		String password = "PrueBa";

		// Objeto
		Usuario usuario = new Usuario(nickName, password);

		// Condiciones test
		assertEquals(nickName, usuario.getNickName());
		assertEquals(password, usuario.getContraseña());
	}
}
