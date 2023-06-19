package main.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionDB {
	private Connection con;

	public ConexionDB(String url, String user, String pass) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
	}

	public boolean inicioDeSesion(String usuario, String contraseña) {
		boolean esValido = true;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT ");
		} catch (SQLException ex) {
			esValido = false;
		}

		return esValido;
	}
}
