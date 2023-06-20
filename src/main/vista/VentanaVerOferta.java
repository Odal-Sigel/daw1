package main.vista;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import main.modelo.Oferta;

public class VentanaVerOferta extends JDialog {
	private Oferta oferta;
	
	/**
	 * Create the dialog.
	 */
	public VentanaVerOferta(Oferta oferta) {
		this.oferta = oferta;
		initialize();
	}
	
	/**
	 * Initialize the dialog
	 */
	public void initialize() {
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(400, 350);
		setTitle("Oferta");
		getContentPane().setLayout(null);
		
		JLabel lblPuesto = new JLabel("Puesto");
		lblPuesto.setBounds(10, 11, 364, 20);
		getContentPane().add(lblPuesto);
		
		JTextArea campoPuesto = new JTextArea();
		campoPuesto.setWrapStyleWord(true);
		campoPuesto.setLineWrap(true);
		campoPuesto.setEditable(false);
		campoPuesto.setBounds(10, 42, 364, 22);
		getContentPane().add(campoPuesto);
		campoPuesto.setText(oferta.getPuestoOfertado());
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(10, 75, 364, 20);
		getContentPane().add(lblDescripcion);
		
		JTextArea campoDescripcion = new JTextArea();
		campoDescripcion.setWrapStyleWord(true);
		campoDescripcion.setLineWrap(true);
		campoDescripcion.setEditable(false);
		campoDescripcion.setBounds(10, 106, 364, 50);
		getContentPane().add(campoDescripcion);
		campoDescripcion.setText(oferta.getDescripcion());
		
		JLabel lblEmpresa = new JLabel("Empresa");
		lblEmpresa.setBounds(10, 167, 364, 20);
		getContentPane().add(lblEmpresa);
		
		JTextArea campoEmpresa = new JTextArea();
		campoEmpresa.setWrapStyleWord(true);
		campoEmpresa.setLineWrap(true);
		campoEmpresa.setEditable(false);
		campoEmpresa.setBounds(10, 198, 364, 22);
		getContentPane().add(campoEmpresa);
		campoEmpresa.setText(oferta.getDatosEmpresa());
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, 231, 364, 20);
		getContentPane().add(lblLocalidad);
		
		JTextArea campoLocalidad = new JTextArea();
		campoLocalidad.setWrapStyleWord(true);
		campoLocalidad.setLineWrap(true);
		campoLocalidad.setEditable(false);
		campoLocalidad.setBounds(10, 262, 364, 22);
		getContentPane().add(campoLocalidad);
		campoLocalidad.setText(oferta.getLocalidad());
		
	}
}
