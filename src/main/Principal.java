package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import main.modelo.ConexionDB;
import main.vista.InicioSesionVentana;
import main.vista.theme.EasyCV;

public class Principal {

	public static void main(String[] args) {
		// Cargar Look and Feel personalizado
		EasyCV.setup();

		// Conexión con la Base de Datos
		try {
			// Lectura del fichero de configuración de la base de datos
			String url = "";
			String user = "";
			String pass = "";

			FileReader fichero = new FileReader("./resources/db.conf"); // Fichero a leer
			BufferedReader buf = new BufferedReader(fichero);
			for (int i = 0; i < 3; i++) {
				String textoS = buf.readLine();
				String texto[] = textoS.split("=");
				if (texto[0].equals("url")) {
					url = texto[1];
				} else if (texto[0].equals("user")) {
					user = texto[1];
				} else if (texto[0].equals("pass")) {
					pass = texto[1];
				} else {
					buf.close();
					fichero.close();
					throw new IOException();
				}
			}
			buf.close();
			fichero.close();

			// Conexión con la base de datos
			ConexionDB conexion = new ConexionDB(url, user, pass);

			// Crear Ventana de Inicio de sesión
			InicioSesionVentana ventana = new InicioSesionVentana(conexion);
			ventana.setVisible(true);
		} catch (FileNotFoundException ex) {
			System.out.println("El fichero de configuración no existe");
		} catch (IOException ex) {
			System.out.println("Error de lectura, revisar el fichero de configuración");
		} catch (SQLException ex) {
			System.out.println("Error al conectarse con la base de datos");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
