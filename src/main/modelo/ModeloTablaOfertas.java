package main.modelo;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaOfertas extends AbstractTableModel {
	private String columnas[] = {"Puesto ofertado", "Fecha de creación", "Nº de inscripciones"};
	private ArrayList<Oferta> lista;

	public ModeloTablaOfertas(ArrayList<Oferta> lista) {
		this.lista = lista;
	}
	
	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Oferta oferta = lista.get(rowIndex);
		
		switch (columnIndex) {
			case 0:
				return oferta.getPuestoOfertado();

			case 1:
				return oferta.getFechaCreacion();

			case 2:
				return oferta.getNumInscritos();

			default:
				return "Dato no encontrado";
		}
	}

}
