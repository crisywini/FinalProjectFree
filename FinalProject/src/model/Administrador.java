package model;

import java.io.Serializable;


public class Administrador extends Persona implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String contrasenia;
	private Espectaculo miEspectaculoAsociado;

	public Administrador() {
		setEmail("@@@");
		setContrasenia("***");
		setMiEspectaculoAsociado(new Espectaculo());
	}

	public Administrador(String nombre, String apellido, String id, Genero miGenero, String email, String contrasenia,
			Espectaculo miEspectaculoAsociado) {
		super(nombre, apellido, id, miGenero);
		this.email = email;
		this.contrasenia = contrasenia;
		this.miEspectaculoAsociado = miEspectaculoAsociado;
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

	public Espectaculo getMiEspectaculoAsociado() {
		return miEspectaculoAsociado;
	}

	public void setMiEspectaculoAsociado(Espectaculo miEspectaculoAsociado) {
		this.miEspectaculoAsociado = miEspectaculoAsociado;
	}
}