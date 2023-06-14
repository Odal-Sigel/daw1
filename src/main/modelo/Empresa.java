package main.modelo;

import java.util.ArrayList;

public class Empresa extends Usuario {
	private String nombre;
	private String nif;
	private ArrayList<Oferta> listaOfertas;

	public Empresa(String nickName, String contraseña, String nombre, String nif) {
		super(nickName, contraseña);
		this.nombre = nombre;
		this.nif = nif; // TODO: comprobar que el NIF tenga 9 caracteres
		listaOfertas = new ArrayList<Oferta>();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getNIF() {
		return nif;
	}
	
	public void crearOferta(String puestoOfertado, String localidad, String descripcion) {
		listaOfertas.add(new Oferta(puestoOfertado, this, localidad, descripcion));
	}

	public ArrayList<Oferta> getListaOfertas() {
		return listaOfertas;
	}
	
	public int getInscritosOferta(int indiceOferta) {
		int numero = listaOfertas.get(indiceOferta).getNumInscritos();
		return numero;
	}
	
	public ArrayList<Pregunta> getListaPreguntas(int indiceOferta) {
		ArrayList<Pregunta> preguntas = listaOfertas.get(indiceOferta).getListaPreguntas();
		return preguntas;
	}
	
	public void contestarPregunta(int indiceOferta, int indicePregunta, String respuesta) {
		listaOfertas.get(indicePregunta).responderPregunta(indicePregunta, respuesta);
	}
}
