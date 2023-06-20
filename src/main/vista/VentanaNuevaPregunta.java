package main.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.modelo.Demandante;
import main.modelo.Oferta;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VentanaNuevaPregunta extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private Demandante demandante;
	private Oferta oferta;
	private JTextArea textArea;

	/**
	 * create the dialog.
	 */
	public VentanaNuevaPregunta(Demandante demandante, Oferta oferta) {
		this.demandante = demandante;
		this.oferta = oferta;
		initialize();
	}

	/**
	 * initialize the dialog.
	 */
	public void initialize() {
		// Configuracion ventana
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(450, 250);
		setTitle(oferta.getPuestoOfertado() + " - " + oferta.getDatosEmpresa());
		
		/*** Componentes ***/
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				textArea = new JTextArea();
				scrollPane.setViewportView(textArea);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Enviar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (textArea.getText().equals("")) {
							JOptionPane.showMessageDialog(getContentPane(), "Por favor, rellene el campo");
						} else {
							demandante.realizarPregunta(oferta, textArea.getText());
							JOptionPane.showMessageDialog(getContentPane(), "Pregunta realizada");
							dispose();
						}
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}

}
