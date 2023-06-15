package main;

import main.vista.InicioSesionVentana;
import main.vista.theme.EasyCV;

public class Principal {

	public static void main(String[] args) {
		// Cargar Look and Feel personalizado
		EasyCV.setup();

		// Crear Ventana de Inicio de sesión
		try {
			InicioSesionVentana ventana = new InicioSesionVentana();
			ventana.setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
