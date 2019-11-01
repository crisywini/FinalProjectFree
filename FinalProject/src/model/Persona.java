package model;

import java.io.Serializable;

public abstract class Persona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String apellido;
	private String id;
	private Genero miGenero;
	
	/**
	 * Constructor completo de la clase persona
	 * 
	 * @param nombre   de la persona
	 * @param apellido de la persona
	 * @param id       de la persona
	 * @param miGenero Genero de la persona
	 */
	public Persona(String nombre, String apellido, String id, Genero miGenero) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.id = id;
		this.miGenero = miGenero;
	}

	public Persona() {
		nombre = "";
		apellido = "";
		id = "";
		miGenero = Genero.NO_BINARIO;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Genero getMiGenero() {
		return miGenero;
	}

	public void setMiGenero(Genero miGenero) {
		this.miGenero = miGenero;
	}

}
