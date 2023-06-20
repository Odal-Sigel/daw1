package test.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.modelo.Empresa;
import main.modelo.excepciones.nifNoValidoException;

class EmpresaTest {

	@Test
	void pruebaConstructor() {
		String nickName = "Prueba";
		String password = "PrueBa";
		String nombre = "Prueba";
		String nif = "56232585T";

		// Objeto
		Empresa empresa;
		try {
			empresa = new Empresa(nickName, password, nombre, nif);

			// Condiciones test
			assertEquals(nickName, empresa.getNickName());
			assertEquals(password, empresa.getContraseña());
			assertEquals(nombre, empresa.getNombre());
			assertEquals(nif, empresa.getNIF());
		} catch (nifNoValidoException ex) {
			fail(ex.getMessage());
		}

	}

}
