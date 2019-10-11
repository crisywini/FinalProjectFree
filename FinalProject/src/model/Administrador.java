package model;

import java.io.Serializable;

import controller.IControlAdministrador;
import exceptions.ClienteNoExistenteException;
import exceptions.ClienteRepetidoException;

public class Administrador extends Persona implements Serializable, IControlAdministrador {
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

	/**
	 * Agregar un cliente
	 */

	@Override
	public boolean agregarCliente(String nombre, String apellido, String id, Genero miGenero, String direccion,
			String email, Cuenta miCuentaAsociada, Date miFechaDeNacimiento, String ciudadDeResidencia,
			EstratoSocioeconomico miEstrato, EstadoCivil miEstadoCivil, NivelDeEstudio miNivelDeEstudio)
			throws ClienteRepetidoException {
		boolean agregadoCompleto = false;
		if (getMiEspectaculoAsociado().estaElCliente(id)) {
			throw new ClienteRepetidoException(
					"El cliente: " + nombre + " " + apellido + " id: " + id + " ya se encuentra en el espectaculo");
		}

		getMiEspectaculoAsociado().getMisClientes().put(id,
				new Cliente(nombre, apellido, id, miGenero, direccion, email, miCuentaAsociada, miFechaDeNacimiento,
						ciudadDeResidencia, miEstrato, miEstadoCivil, miNivelDeEstudio));
		agregadoCompleto = true;
		return agregadoCompleto;
	}

	/**
	 * Eliminar un cliente
	 */
	@Override
	public Cliente removerCliente(String id) throws ClienteNoExistenteException {
		if (!getMiEspectaculoAsociado().estaElCliente(id))
			throw new ClienteNoExistenteException(
					"El cliente con id: " + id + " no se encuentra registrado en el espectadulo.");
		Cliente miCliente = getMiEspectaculoAsociado().getMisClientes().remove(id);
		return miCliente;
	}

	@Override
	public void programarEvento(Date fechaDePresentacion) {
	}
}