package main.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import main.modelo.ConexionDB;
import main.modelo.Demandante;
import main.modelo.ModeloTablaOfertasDisponibles;
import main.modelo.Oferta;
import main.modelo.Usuario;
import main.modelo.excepciones.DemandanteYaInscritoException;

public class VentanaDemandante extends JFrame {
	private ConexionDB conexion;
	private Demandante demandante;
	private JTable tabla;
	private ArrayList<Oferta> lista;

	/**
	 * Create the frame.
	 */
	public VentanaDemandante(ConexionDB conexion, Usuario userU) {
		this.conexion = conexion;
		demandante = (Demandante) userU;
		initialize();
	}

	/**
	 * Initialize the dialog
	 */
	public void initialize() {
		// Configuración de la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 800);
		setTitle(demandante.getNombreCompleto());

		/*** Componentes ***/
		/** Botones **/
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout(FlowLayout.LEFT));
		getContentPane().add(panelBotones, BorderLayout.NORTH);

		/* Ver Oferta */
		JButton btnVerOferta = new JButton("Ver");
		btnVerOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tabla.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(getContentPane(), "Por favor, seleccione una oferta");
				} else {
					Oferta oferta = lista.get(tabla.getSelectedRow());
					VentanaVerOferta ventana = new VentanaVerOferta(oferta);
					ventana.setLocationRelativeTo(getContentPane());
					ventana.setVisible(true);
				}
			}
		});
		panelBotones.add(btnVerOferta);

		/* Nueva Pregunta */
		JButton btnNuevaPregunta = new JButton("Preguntar");
		btnNuevaPregunta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tabla.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(getContentPane(), "Por favor, seleccione una oferta");
				} else {
					Oferta oferta = lista.get(tabla.getSelectedRow());
					VentanaNuevaPregunta ventana = new VentanaNuevaPregunta(conexion, demandante, oferta);
					ventana.setLocationRelativeTo(getContentPane());
					ventana.setVisible(true);
				}
			}
		});
		panelBotones.add(btnNuevaPregunta);

		/* Inscribirse Oferta */
		JButton btnInscribirseOferta = new JButton("Inscribirse");
		btnInscribirseOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tabla.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(getContentPane(), "Por favor, seleccione una oferta");
				} else {
					Oferta oferta = lista.get(tabla.getSelectedRow());
					try {
						demandante.inscribirseOferta(oferta);
						JOptionPane.showMessageDialog(getContentPane(), "Se ha inscrito a la oferta");
					} catch (DemandanteYaInscritoException ex) {
						JOptionPane.showMessageDialog(getContentPane(), ex.getMessage());
					}
				}
			}
		});
		panelBotones.add(btnInscribirseOferta);

		// Lista ofertas
		lista = conexion.rellenarListaOfertas();

		/** Tabla **/
		ModeloTablaOfertasDisponibles modelo = new ModeloTablaOfertasDisponibles(lista);

		tabla = new JTable(modelo);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.getTableHeader().setReorderingAllowed(false); // No permitir modificar la posición de las columnas
		tabla.getTableHeader().setResizingAllowed(false); // No permitir modificar el ancho de las columnas
		tabla.getColumnModel().getColumn(0).setPreferredWidth(this.getWidth() / 5 * 2); // Ancho de la primera
																						// columna -> puesto
		tabla.getColumnModel().getColumn(1).setPreferredWidth(this.getWidth() / 5); // Ancho de la segunda columna
																					// -> empresa
		DefaultTableCellRenderer alinearCentro = new DefaultTableCellRenderer();
		alinearCentro.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		tabla.getColumnModel().getColumn(1).setCellRenderer(alinearCentro); // Alinear al centro
		tabla.getColumnModel().getColumn(2).setPreferredWidth(this.getWidth() / 5); // Ancho de la tercera columna
																					// -> localidad
		tabla.getColumnModel().getColumn(2).setCellRenderer(alinearCentro); // Alinear al centro
		tabla.getColumnModel().getColumn(3).setPreferredWidth(this.getWidth() / 5); // Ancho de la cuarta columna ->
																					// fecha

		JScrollPane panelTabla = new JScrollPane(tabla);
		getContentPane().add(panelTabla, BorderLayout.CENTER);
	}
}
