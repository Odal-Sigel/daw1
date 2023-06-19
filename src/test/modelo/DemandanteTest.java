package test.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.modelo.Demandante;

class DemandanteTest {

	@Test
	void pruebaConstructor() {
		String nickName = "Prueba";
		String password = "PrueBa";
		String nombre = "Prueba";
		String apellidos = "Prueba Prueba";
		int edad = 24;

		// Objeto
		Demandante demandante = new Demandante(nickName, password, nombre, apellidos, edad);

		// Condiciones test
		assertEquals(nickName, demandante.getNickName());
		assertEquals(password, demandante.getContraseña());
		assertEquals(nombre, demandante.getNombre());
		assertEquals(apellidos, demandante.getApellidos());
		assertEquals(edad, demandante.getEdad());
	}

}
