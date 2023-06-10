package test.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.modelo.Usuario;

class UsuarioTest {

	@Test
	void pruebaConstructor() {
		int id = 1;
		String nombre = "Prueba";

		// Objeto
		Usuario usuario = new Usuario(id, nombre);

		// Condiciones test
		assertEquals(id, usuario.getUsuarioID());
		assertEquals(nombre, usuario.getNombre());
	}
}
