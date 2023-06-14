package main.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import main.modelo.Empresa;
import main.modelo.ModeloTablaOfertas;

public class VentanaEmpresa extends JFrame {
	private JDialog ventanaInicioSesion;
	private Empresa empresa;
	private JTable tabla;

	/**
	 * Create the frame.
	 */
	public VentanaEmpresa(JDialog ventanaInicioSesion, Empresa empresa) {
		this.ventanaInicioSesion = ventanaInicioSesion;
		this.empresa = empresa;
		initialize();
	}

	/**
	 * Initialize the dialog
	 */
	public void initialize() {
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
		panelEdicion.add(btnNuevaOferta);
		
		/** Ofertas **/		
		// PROTOTIPO - Rellenar las ofertas del usuario
		empresa.crearOferta("Analista/Programador", "Soria", "Se busca Analista/Programador en Soria para una importante empresa del sector tecnológico");
		
		/* Tabla */
		ModeloTablaOfertas modelo = new ModeloTablaOfertas(empresa.getListaOfertas());
		tabla = new JTable(modelo);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.getTableHeader().setReorderingAllowed(false); // No permitir modificar la posición de las columnas
		tabla.getTableHeader().setResizingAllowed(false); // No permitir modificar el ancho de las columnas
		tabla.getColumnModel().getColumn(0).setPreferredWidth(this.getWidth() / 4 * 2); // Ancho de la primera columna -> puesto
		tabla.getColumnModel().getColumn(1).setPreferredWidth(this.getWidth() / 4); // Ancho de la segunda columna -> fecha
		tabla.getColumnModel().getColumn(2).setPreferredWidth(this.getWidth() / 4); // Ancho de la tercera columna -> inscritos
		
		/* Panel */
		JScrollPane panelTabla = new JScrollPane(tabla);
		getContentPane().add(panelTabla, BorderLayout.CENTER);

	}
}
