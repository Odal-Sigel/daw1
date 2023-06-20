package main.modelo;

import java.util.ArrayList;

import main.modelo.excepciones.DemandanteYaInscritoException;

public class Demandante extends Usuario {
	private String nombre;
	private String apellidos;
	private int edad;
	private ArrayList<Pregunta> listaPreguntas;
	private ArrayList<Oferta> listaOfertasInscritas;

	public Demandante(String nickName, String contraseña, String nombre, String apellidos, int edad) {
		super(nickName, contraseña);
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		listaPreguntas = new ArrayList<Pregunta>();
		
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}
	
	public String getNombreCompleto() {
		return apellidos + ", " + nombre;
	}
	
	public int getEdad() {
		return edad;
	}
	
	public void realizarPregunta(Oferta oferta, String contenido) {
		Pregunta pregunta = new Pregunta(oferta, this, contenido);
		listaPreguntas.add(pregunta);
	}

	public ArrayList<Pregunta> getListaPreguntasHechas() {
		return listaPreguntas;
	}

	public void inscribirseOferta(Oferta oferta) throws DemandanteYaInscritoException {
		oferta.addDemandanteInscrito(this);
	}
	
	public ArrayList<Oferta> getListaOfertasInscritas() {
		return listaOfertasInscritas;
	}
}
