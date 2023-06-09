package com.java;

import javax.swing.JDialog;

import com.java.theme.EasyCV;
import com.java.vista.InicioSesionVentana;

public class Principal {

	public static void main(String[] args) {
		// Cargar Look and Feel personalizado
		EasyCV.setup();
		
		// Crear Ventana de Inicio de sesión
		try {
			InicioSesionVentana dialog = new InicioSesionVentana();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
