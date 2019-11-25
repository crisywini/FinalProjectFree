package model;

import java.io.Serializable;


public class Administrador extends Persona implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String contrasenia;
	private Boleteria miBoleteriaAsociada;

	public Administrador() {
		setEmail("@@@");
		setContrasenia("***");
		setMiBoleteriaAsociada(new Boleteria());
	}

	public Administrador(String nombre, String apellido, String id, Genero miGenero, String email, String contrasenia,
			Boleteria miBoleteriaAsociada) {
		super(nombre, apellido, id, miGenero);
		this.email = email;
		this.contrasenia = contrasenia;
		this.setMiBoleteriaAsociada(miBoleteriaAsociada);
	}

	public Administrador(String email, String contrasenia) {
		this.contrasenia = contrasenia;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Boleteria getMiBoleteriaAsociada() {
		return miBoleteriaAsociada;
	}

	public void setMiBoleteriaAsociada(Boleteria miBoleteriaAsociada) {
		this.miBoleteriaAsociada = miBoleteriaAsociada;
	}
	@Override
	public String toString() {
		return "Nombre: "+this.getNombre()+" id: "+getId()+" Boleteria: "+miBoleteriaAsociada;
	}
}