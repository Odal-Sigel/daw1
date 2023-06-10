package test.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.modelo.Empresa;

class EmpresaTest {

	@Test
	void pruebaConstructor() {
		int id = 1;
		String nombre = "Prueba";
		String nif = "56232585T";

		// Objeto
		Empresa empresa = new Empresa(id, nombre, nif);

		// Condiciones test
		assertEquals(id, empresa.getUsuarioID());
		assertEquals(nombre, empresa.getNombre());
		assertEquals(nif, empresa.getNif());
	}

}
