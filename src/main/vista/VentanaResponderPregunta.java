package main.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import main.modelo.ConexionDB;
import main.modelo.Empresa;
import main.modelo.Oferta;
import main.modelo.Pregunta;
import main.modelo.excepciones.RespuestaVaciaException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class VentanaResponderPregunta extends JDialog {
	private final JPanel panelTexto = new JPanel();
	private ConexionDB conexion;
	private Empresa empresa;
	private int indiceOferta;
	private int indicePregunta;

	private Oferta oferta;
	private Pregunta pregunta;

	private JTextArea textArea;

	/**
	 * Create the dialog.
	 */
	public VentanaResponderPregunta(ConexionDB conexion, Empresa empresa, int indiceOferta, int indicePregunta) {
		this.conexion = conexion;
		this.empresa = empresa;
		this.indiceOferta = indiceOferta;
		this.indicePregunta = indicePregunta;

		oferta = empresa.getListaOfertas().get(indiceOferta);
		pregunta = oferta.getListaPreguntas().get(indicePregunta);

		initialize();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	/**
	 * Initialize the dialog.
	 */
	public void initialize() {
		// Configuración de la ventana
		setModalityType(DEFAULT_MODALITY_TYPE);
		setSize(400, 220);
		setTitle("Responder pregunta");
		getContentPane().setLayout(new BorderLayout());
		panelTexto.setBorder(new EmptyBorder(5, 5, 5, 5));

		/*** Componentes ***/
		/** Texto **/
		getContentPane().add(panelTexto, BorderLayout.CENTER);
		panelTexto.setLayout(null);

		JLabel lblNewLabel = new JLabel(pregunta.getContenido() + " - " + pregunta.getDatosDemandante());
		lblNewLabel.setBounds(10, 11, 364, 20);
		panelTexto.add(lblNewLabel);

		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		// Agregar la respuesta anterior si la hubiese para editarla
		try {
			textArea.setText(pregunta.getRespuesta());
		} catch (RespuestaVaciaException ex) {
			textArea.setText("");
		}
		textArea.setBounds(10, 35, 364, 82);

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(10, 42, 364, 81);
		panelTexto.add(scrollPane);

		/** Botones **/
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		/* Enviar */
		JButton okButton = new JButton("Enviar");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textArea.getText().equals("")) {
					JOptionPane.showMessageDialog(getContentPane(), "Por favor, rellene el campo requerido");
				} else {
					empresa.contestarPregunta(indiceOferta, indicePregunta, textArea.getText());
					conexion.contestarPregunta(empresa, indiceOferta, indicePregunta);
					JOptionPane.showMessageDialog(getContentPane(), "Pregunta respondida");
					dispose();
				}
			}
		});
		panelBotones.add(okButton);
		getRootPane().setDefaultButton(okButton);
		/* Cancelar */
		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panelBotones.add(cancelButton);
	}
}
