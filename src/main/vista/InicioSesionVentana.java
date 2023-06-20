package main.vista;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.modelo.ConexionDB;
import main.modelo.Demandante;
import main.modelo.Empresa;
import main.modelo.Usuario;
import main.modelo.excepciones.nifNoValidoException;

public class InicioSesionVentana extends JDialog {
	private JTextField campoUsuario;
	private JPasswordField campoContraseña;

	// Cambiar de imágenes dependiendo del foco
	// Usuario
	private JLabel iconoUsuario;
	private ImageIcon iconoUsuarioSinFoco;
	private ImageIcon iconoUsuarioConFoco;
	// Contraseña
	private JLabel iconoContraseña;
	private ImageIcon iconoContraseñaSinFoco;
	private ImageIcon iconoContraseñaConFoco;

	// Base de datos
	private ConexionDB conexion;
	private Usuario userU;

	/**
	 * Create the dialog.
	 */
	public InicioSesionVentana(ConexionDB conexion) {
		this.conexion = conexion;
		initialize();
	}

	/**
	 * Initialize the dialog
	 */
	public void initialize() {
		// Variables
		Color colorIcono = Color.decode("#EE6C4D");

		// Configuración de la ventana
		setTitle("Inicio de sesión");
		setSize(400, 300);
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		setResizable(false); // Evitar que se redimensione la ventana
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Evitar que se termine la ejecución del programa completo cuando se cierra esta ventana de inicio de sesión

		// Layout de la ventana
		GridBagLayout gblInicio = new GridBagLayout();
		gblInicio.rowHeights = new int[] { 25, 100, 25, 25, 25, 75, 25 }; // Medidas de la altura de las filas
		gblInicio.columnWidths = new int[] { 50, 300, 50 }; // Medida de la anchura de las columnas
		getContentPane().setLayout(gblInicio);

		/*** Componentes ***/
		/** Logotipo **/
		JLabel labelLogotipo = new JLabel(new ImageIcon("./resources/logoLetras.png"));
		GridBagConstraints gbcLogotipo = new GridBagConstraints();
		gbcLogotipo.gridx = 1;
		gbcLogotipo.gridy = 1;
		gbcLogotipo.fill = GridBagConstraints.BOTH;
		getContentPane().add(labelLogotipo, gbcLogotipo);

		/** Usuario **/
		JPanel panelUsuario = new JPanel();
		GridBagLayout gblPanelUsuario = new GridBagLayout();
		gblPanelUsuario.rowHeights = new int[] { 25 };
		gblPanelUsuario.columnWidths = new int[] { 22, 278 };
		panelUsuario.setLayout(gblPanelUsuario);

		GridBagConstraints gbcUsuario = new GridBagConstraints();
		gbcUsuario.ipady = 10;
		gbcUsuario.gridx = 1;
		gbcUsuario.gridy = 2;
		gbcUsuario.fill = GridBagConstraints.BOTH;
		getContentPane().add(panelUsuario, gbcUsuario);

		/* Icono */
		JPanel panelIconoUsuario = new JPanel();
		panelIconoUsuario.setBackground(colorIcono);
		iconoUsuarioSinFoco = new ImageIcon("./resources/login/user.png");
		iconoUsuarioConFoco = new ImageIcon("./resources/login/userFoco.png");
		iconoUsuario = new JLabel(iconoUsuarioSinFoco);
		panelIconoUsuario.add(iconoUsuario);

		GridBagConstraints gbcIconoUsuario = new GridBagConstraints();
		gbcIconoUsuario.gridx = 0;
		gbcIconoUsuario.gridy = 0;
		gbcIconoUsuario.fill = GridBagConstraints.BOTH;
		panelUsuario.add(panelIconoUsuario, gbcIconoUsuario);

		/* Campo */
		campoUsuario = new JTextField();
		campoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarSesion();
			}
		});
		campoUsuario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				iconoUsuario.setIcon(iconoUsuarioConFoco);
			}

			@Override
			public void focusLost(FocusEvent e) {
				iconoUsuario.setIcon(iconoUsuarioSinFoco);
			}
		});
		campoUsuario.putClientProperty("JTextField.placeholderText", "Usuario"); // Placeholder -> Usuario

		GridBagConstraints gbcCampoUsuario = new GridBagConstraints();
		gbcCampoUsuario.gridx = 1;
		gbcCampoUsuario.gridy = 0;
		gbcCampoUsuario.fill = GridBagConstraints.BOTH;
		panelUsuario.add(campoUsuario, gbcCampoUsuario);

		/** Contraseña **/
		JPanel panelContraseña = new JPanel();
		GridBagLayout gblPanelContraseña = new GridBagLayout();
		gblPanelContraseña.rowHeights = new int[] { 25 };
		gblPanelContraseña.columnWidths = new int[] { 22, 278 };
		panelContraseña.setLayout(gblPanelContraseña);

		GridBagConstraints gbcContraseña = new GridBagConstraints();
		gbcContraseña.ipady = 10;
		gbcContraseña.gridx = 1;
		gbcContraseña.gridy = 3;
		gbcContraseña.fill = GridBagConstraints.BOTH;
		getContentPane().add(panelContraseña, gbcContraseña);

		/* Icono */
		JPanel panelIconoContraseña = new JPanel();
		panelIconoContraseña.setBackground(colorIcono);
		iconoContraseñaSinFoco = new ImageIcon("./resources/login/lock.png");
		iconoContraseñaConFoco = new ImageIcon("./resources/login/lockFoco.png");
		iconoContraseña = new JLabel(iconoContraseñaSinFoco);
		panelIconoContraseña.add(iconoContraseña);

		GridBagConstraints gbcIconoContraseña = new GridBagConstraints();
		gbcIconoContraseña.gridx = 0;
		gbcIconoContraseña.gridy = 0;
		gbcIconoContraseña.fill = GridBagConstraints.BOTH;
		panelContraseña.add(panelIconoContraseña, gbcIconoContraseña);

		/* Campo */
		campoContraseña = new JPasswordField();
		campoContraseña.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarSesion();
			}
		});
		campoContraseña.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				iconoContraseña.setIcon(iconoContraseñaConFoco);
			}

			@Override
			public void focusLost(FocusEvent e) {
				iconoContraseña.setIcon(iconoContraseñaSinFoco);
			}
		});
		campoContraseña.putClientProperty("JTextField.placeholderText", "Contraseña"); // Placeholder -> Contraseña

		GridBagConstraints gbcCampoContraseña = new GridBagConstraints();
		gbcCampoContraseña.gridx = 1;
		gbcCampoContraseña.gridy = 0;
		gbcCampoContraseña.fill = GridBagConstraints.BOTH;
		panelContraseña.add(campoContraseña, gbcCampoContraseña);

		/** Recordar Usuario **/
		// TODO: Implementar la función para recordar el usuario ingresado
		JPanel panelRecordar = new JPanel();
		FlowLayout flowLayoutRercordar = (FlowLayout) panelRecordar.getLayout();
		flowLayoutRercordar.setAlignment(FlowLayout.LEFT);

		GridBagConstraints gbcRecordar = new GridBagConstraints();
		gbcRecordar.gridx = 1;
		gbcRecordar.gridy = 4;
		gbcRecordar.fill = GridBagConstraints.BOTH;
		getContentPane().add(panelRecordar, gbcRecordar);

		/* Check */
		JCheckBox checkRecordar = new JCheckBox("Recordar usuario");
		checkRecordar.setToolTipText("Función en desarrollo");
		checkRecordar.setEnabled(false);
		panelRecordar.add(checkRecordar);

		/** Inicio de sesión **/
		JPanel panelInicio = new JPanel();

		GridBagConstraints gbcInicio = new GridBagConstraints();
		gbcInicio.gridx = 1;
		gbcInicio.gridy = 5;
		gbcInicio.fill = GridBagConstraints.BOTH;
		getContentPane().add(panelInicio, gbcInicio);

		/* Botón */
		JButton btnInicio = new JButton("Entrar");
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarSesion();
			}
		});
		btnInicio.setFocusable(false);
		panelInicio.add(btnInicio);
	}

	private void iniciarSesion() {
		String usuarioS = campoUsuario.getText();
		String contraseñaS = new String(campoContraseña.getPassword());

		/*
		boolean bandera = conexion.inicioDeSesion(usuarioS, contraseñaS, userU);
		
		if (bandera) {
			if (userU instanceof Demandante) {
				System.out.println("DEBUG - DEMANDANTE");
			} else {
				VentanaEmpresa ventana = new VentanaEmpresa(this, userU);
				this.dispose();
				ventana.setVisible(true);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Usuario y/o contraseña incorrectos");
		}
		*/

		// Prototipo - Objetos del modelo
		/**/
		Demandante dem = new Demandante("pablo", "pablo", "Pablo", "Cornago Gómez", 24);
		Empresa emp;
		try {
			emp = new Empresa("easy", "easy", "EasyCV", "123456789");

			if (dem.getNickName().equals(usuarioS) && dem.getContraseña().equals(contraseñaS)) {
				VentanaDemandante ventana = new VentanaDemandante(dem);
				this.dispose();
				ventana.setLocationRelativeTo(this);
				ventana.setVisible(true);
			} else if (emp.getNickName().equals(usuarioS) && emp.getContraseña().equals(contraseñaS)) {
				VentanaEmpresa ventana = new VentanaEmpresa(this, emp);
				this.dispose();
				ventana.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, "Usuario y/o contraseña incorrectos");
			}
		} catch (nifNoValidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**/
	}
}
