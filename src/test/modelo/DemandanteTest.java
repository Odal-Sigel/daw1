package test.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.modelo.Demandante;

class DemandanteTest {

	@Test
	void pruebaConstructor() {
		int id = 1;
		String nombre = "Prueba";
		String apellidos = "Prueba Prueba";
		int edad = 24;

		// Objeto
		Demandante demandante = new Demandante(id, nombre, apellidos, edad);

		// Condiciones test
		assertEquals(id, demandante.getUsuarioID());
		assertEquals(nombre, demandante.getNombre());
		assertEquals(apellidos, demandante.getApellidos());
		assertEquals(edad, demandante.getEdad());
	}

}
