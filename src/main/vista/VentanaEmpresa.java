package main.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;

import main.modelo.Empresa;
import main.modelo.ModeloTablaOfertas;

public class VentanaEmpresa extends JFrame {
	private JDialog ventanaInicioSesion;
	private Empresa empresa;
	private JTable tabla;
	private VentanaCrearOferta ventanaNuevaOferta;

	/**
	 * Create the frame.
	 */
	public VentanaEmpresa(JDialog ventanaInicioSesion, Empresa empresa) {
		this.ventanaInicioSesion = ventanaInicioSesion;
		this.empresa = empresa;
		ventanaNuevaOferta = new VentanaCrearOferta(empresa);
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
		btnNuevaOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaNuevaOferta.setLocationRelativeTo(getContentPane());
				ventanaNuevaOferta.setVisible(true);
			}
		});
		panelEdicion.add(btnNuevaOferta);
		
		/** Ofertas **/		
		// PROTOTIPO - Rellenar las ofertas del usuario
		empresa.crearOferta("Analista/Programador", "Soria", "Se busca Analista/Programador en Soria para una importante empresa del sector tecnológico");
		empresa.crearOferta("Recursos Humanos", "Soria", "Se busca responsable de Recursos Humanos en Soria para una importante empresa del sector tecnológico");
		
		/* Tabla */
		ModeloTablaOfertas modelo = new ModeloTablaOfertas(empresa.getListaOfertas());
		modelo.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {				
			}
		});
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
