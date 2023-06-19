package main;

import main.vista.InicioSesionVentana;
import main.vista.theme.EasyCV;

public class Principal {

	public static void main(String[] args) {
		// Cargar Look and Feel personalizado
		EasyCV.setup();

		// DEBUG
		// System.out.println("DEBUG");
		/*
		// PRUEBA CONEXION BASE DE DATOS
		try {
			Connection con = DriverManager.getConnection("jdbc:mariadb://cornagopablo.com:3306/easycv", "easycv", "Projecto1.");
			System.out.println("OK");
		} catch (SQLException e) {
			System.out.println("NO");
		}
		*/
		// PRUEBA DE LECTURA DE FICHERO
		/*
		try {
			FileReader fichero = new FileReader("./resources/db.conf");
			BufferedReader buf = new BufferedReader(fichero);
			for (int i=0; i<3; i++) {
				String textoS = buf.readLine();
				String texto[] = textoS.split("=");
				if (texto[0].equals("url")) {
					System.out.println("URL - " + texto[1]);
				} else if (texto[0].equals("user")) {
					System.out.println("USER - " + texto[1]);
				} else if (texto[0].equals("pass")) {
					System.out.println("PASSWORD - " + texto[1]);
				} else {
					System.out.println("ERROR");
				}
			}
			buf.close();
			fichero.close();
		} catch (FileNotFoundException ex) {
			System.out.println("El fichero no existe");
		} catch (IOException ex) {
			System.out.println("Erro de lectura");
		}
		*/
		
		// Crear Ventana de Inicio de sesión
		try {
			InicioSesionVentana ventana = new InicioSesionVentana();
			ventana.setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
