package main.modelo;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import main.modelo.excepciones.RespuestaVaciaException;

public class ModeloTablaPreguntas extends AbstractTableModel {
	private String columnas[] = { "Pregunta", "Demandante", "Respuesta" };
	private ArrayList<Pregunta> listaPreguntas;

	public ModeloTablaPreguntas(ArrayList<Pregunta> listaPreguntas) {
		this.listaPreguntas = listaPreguntas;
	}

	@Override
	public int getRowCount() {
		return listaPreguntas.size();
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Pregunta pregunta = listaPreguntas.get(rowIndex);

		switch (columnIndex) {
			case 0:
				return pregunta.getContenido();

			case 1:
				return pregunta.getDatosDemandante();

			case 2:
				String respuesta;
				try {
					respuesta = pregunta.getRespuesta();
				} catch (RespuestaVaciaException ex) {
					respuesta = "-";
				}
				return respuesta;

			default:
				return "Dato no encontrado";
		}
	}

	@Override
	public String getColumnName(int column) {
		return columnas[column];
	}
}
