package main.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import main.modelo.ConexionDB;
import main.modelo.Empresa;
import main.modelo.ModeloTablaPreguntas;
import main.modelo.Oferta;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPreguntasOferta extends JFrame {
	private JTable tablaPreguntas;
	private ModeloTablaPreguntas modelo;
	private Empresa empresa;
	private int indiceOferta;
	private Oferta oferta;
	private ConexionDB conexion;

	/**
	 * Create the frame.
	 */
	public VentanaPreguntasOferta(ConexionDB conexion, Empresa empresa, int indiceOferta) {
		this.conexion = conexion;
		this.empresa = empresa;
		this.indiceOferta = indiceOferta;
		this.oferta = empresa.getListaOfertas().get(indiceOferta);
		initialize();
	}

	/**
	 * Initialize the frame
	 */
	public void initialize() {
		// Configuración de la ventana
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(oferta.getPuestoOfertado());
		setSize(800, 800);

		/*** Componentes ***/
		/** Responder **/
		JPanel panelResponder = new JPanel();
		panelResponder.setLayout(new FlowLayout(FlowLayout.LEFT));
		getContentPane().add(panelResponder, BorderLayout.NORTH);

		JButton btnResponder = new JButton("Responder");
		btnResponder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tablaPreguntas.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(getContentPane(), "Por favor, seleccione una pregunta");
				} else {
					VentanaResponderPregunta ventanaResponder = new VentanaResponderPregunta(conexion, empresa,
							indiceOferta, tablaPreguntas.getSelectedRow());
					ventanaResponder.setLocationRelativeTo(getContentPane());
					ventanaResponder.setVisible(true);
					modelo.fireTableDataChanged(); // Actualizar los datos de la tabla una vez se cierre el JDialog
				}
			}
		});
		panelResponder.add(btnResponder);

		/** Preguntas **/
		/* Tabla */
		modelo = new ModeloTablaPreguntas(oferta.getListaPreguntas());
		tablaPreguntas = new JTable(modelo);
		tablaPreguntas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaPreguntas.getTableHeader().setReorderingAllowed(false); // No permitir modificar la posición de las columnas
		tablaPreguntas.getTableHeader().setResizingAllowed(false); // No permitir modificar el ancho de las columnas
		tablaPreguntas.getColumnModel().getColumn(0).setPreferredWidth(this.getWidth() / 6 * 3); // Ancho de la primera columna -> Pregunta
		tablaPreguntas.getColumnModel().getColumn(1).setPreferredWidth(this.getWidth() / 6 * 2); // Ancho de la seguna columna -> Demandante
		tablaPreguntas.getColumnModel().getColumn(2).setPreferredWidth(this.getWidth() / 6 * 1); // Ancho de la tercera columna -> ¿Respondida?
		DefaultTableCellRenderer alinearCentro = new DefaultTableCellRenderer();
		alinearCentro.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		tablaPreguntas.getColumnModel().getColumn(2).setCellRenderer(alinearCentro); // Alinear al centro -> columna respuesta

		JScrollPane scrollPane = new JScrollPane(tablaPreguntas);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
	}
}
