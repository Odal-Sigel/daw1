package main.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.modelo.Empresa;
import javax.swing.JScrollPane;

public class VentanaCrearOferta extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private Empresa empresa;
	private JTextField campoPuestoOfertado;
	private JTextField campoLocalidad;
	private JTextArea campoDescripcion;

	/**
	 * Create the dialog.
	 */
	public VentanaCrearOferta(Empresa empresa) {
		this.empresa = empresa;
		initialize();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	/**
	 * Initialize the dialog
	 */
	public void initialize() {
		setModalityType(DEFAULT_MODALITY_TYPE);
		setSize(400, 340);
		setTitle("Nueva oferta");
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Puesto ofertado");
		lblNewLabel.setBounds(10, 11, 100, 14);
		contentPanel.add(lblNewLabel);

		campoPuestoOfertado = new JTextField();
		campoPuestoOfertado.setBounds(10, 36, 364, 20);
		contentPanel.add(campoPuestoOfertado);
		campoPuestoOfertado.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Localidad");
		lblNewLabel_1.setBounds(10, 67, 46, 14);
		contentPanel.add(lblNewLabel_1);

		campoLocalidad = new JTextField();
		campoLocalidad.setBounds(10, 92, 364, 20);
		contentPanel.add(campoLocalidad);
		campoLocalidad.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Descripción");
		lblNewLabel_2.setBounds(10, 123, 100, 14);
		contentPanel.add(lblNewLabel_2);

		campoDescripcion = new JTextArea();
		campoDescripcion.setLineWrap(true);
		campoDescripcion.setWrapStyleWord(true);
		campoDescripcion.setBounds(10, 148, 364, 100);
		campoDescripcion.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane(campoDescripcion);
		scrollPane.setBounds(10, 148, 364, 100);
		contentPanel.add(scrollPane);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (campoPuestoOfertado.getText().equals("") || campoLocalidad.getText().equals("")
								|| campoDescripcion.getText().equals("")) {
							JOptionPane.showMessageDialog(getContentPane(), "Por favor, rellene todos los campos");
						} else {
							empresa.crearOferta(campoPuestoOfertado.getText(), campoLocalidad.getText(),
									campoDescripcion.getText());
							JOptionPane.showMessageDialog(getContentPane(), "Oferta creada");
							setVisible(false);
						}
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
}
