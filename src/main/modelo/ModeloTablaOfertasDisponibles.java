package main.modelo;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaOfertasDisponibles extends AbstractTableModel {
	private String columnas[] = { "Puesto", "Empresa", "Localidad", "Fecha" };
	private ArrayList<Oferta> lista;

	public ModeloTablaOfertasDisponibles(ArrayList<Oferta> lista) {
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
				return oferta.getNombreEmpresa();

			case 2:
				return oferta.getLocalidad();

			case 3:
				return oferta.getFechaCreacion();

			default:
				return "Dato no encontrado";
		}
	}

	@Override
	public String getColumnName(int column) {
		return columnas[column];
	}
}
