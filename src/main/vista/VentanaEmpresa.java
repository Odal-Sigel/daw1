package main.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import main.modelo.ConexionDB;
import main.modelo.Empresa;
import main.modelo.ModeloTablaOfertas;
import main.modelo.Usuario;

public class VentanaEmpresa extends JFrame {
	private JDialog ventanaInicioSesion;
	private Empresa empresa;
	private JTable tabla;
	private VentanaCrearOferta ventanaNuevaOferta;
	private ModeloTablaOfertas modelo;
	private ConexionDB conexion;

	/**
	 * Create the frame.
	 */
	public VentanaEmpresa(ConexionDB conexion, JDialog ventanaInicioSesion, Usuario user) {
		this.conexion = conexion;
		this.ventanaInicioSesion = ventanaInicioSesion;
		this.empresa = (Empresa) user;
		initialize();
	}

	/**
	 * Initialize the frame
	 */
	public void initialize() {
		conexion.sacarOfertas(empresa);
		conexion.sacarPreguntas(empresa);
		// Configuración de la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 800);
		setTitle(empresa.getNombre());
		setLocationRelativeTo(ventanaInicioSesion);

		/*** Componentes ***/
		/** Edicion **/
		JPanel panelEdicion = new JPanel();
		panelEdicion.setLayout(new FlowLayout(FlowLayout.LEFT));
		getContentPane().add(panelEdicion, BorderLayout.NORTH);

		/* Nueva Oferta */
		JButton btnNuevaOferta = new JButton("Nueva");
		btnNuevaOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaNuevaOferta = new VentanaCrearOferta(conexion, empresa);
				ventanaNuevaOferta.setLocationRelativeTo(getContentPane()); // Centrar la ventana respecto a esta
				ventanaNuevaOferta.setVisible(true);
				modelo.fireTableDataChanged(); // Actualizar los datos de la tabla una vez se cierre el JDialog
			}
		});
		panelEdicion.add(btnNuevaOferta);

		JButton btnVerPreguntas = new JButton("Preguntas");
		btnVerPreguntas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tabla.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(getContentPane(), "Por favor, seleccione una oferta");
				} else {
					VentanaPreguntasOferta ventanaPreguntas = new VentanaPreguntasOferta(conexion, empresa,
							tabla.getSelectedRow());
					ventanaPreguntas.setLocationRelativeTo(getContentPane());
					ventanaPreguntas.setVisible(true);
				}
			}
		});
		panelEdicion.add(btnVerPreguntas);

		/** Ofertas **/
		/* Tabla */
		modelo = new ModeloTablaOfertas(empresa.getListaOfertas());
		tabla = new JTable(modelo);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.getTableHeader().setReorderingAllowed(false); // No permitir modificar la posición de las columnas
		tabla.getTableHeader().setResizingAllowed(false); // No permitir modificar el ancho de las columnas
		tabla.getColumnModel().getColumn(0).setPreferredWidth(this.getWidth() / 4 * 2); // Ancho de la primera columna -> puesto
		tabla.getColumnModel().getColumn(1).setPreferredWidth(this.getWidth() / 4); // Ancho de la segunda columna -> fecha
		DefaultTableCellRenderer alinearCentro = new DefaultTableCellRenderer();
		alinearCentro.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		tabla.getColumnModel().getColumn(1).setCellRenderer(alinearCentro); // Alinear el texto al centro -> fecha
		tabla.getColumnModel().getColumn(2).setPreferredWidth(this.getWidth() / 4); // Ancho de la tercera columna -> inscritos
		DefaultTableCellRenderer alinearDerecha = new DefaultTableCellRenderer();
		alinearDerecha.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		tabla.getColumnModel().getColumn(2).setCellRenderer(alinearDerecha); // Alinear el texto a la derecha -> inscritos

		/* Panel */
		JScrollPane panelTabla = new JScrollPane(tabla);
		getContentPane().add(panelTabla, BorderLayout.CENTER);

	}
}
