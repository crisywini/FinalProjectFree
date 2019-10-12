package model;

import java.util.ArrayList;
import java.util.HashMap;

import exceptions.AdministradorNoExistenteException;
import exceptions.AdministradorRepetidoException;
import exceptions.FechaExistente;
import exceptions.ReservaNoExisteException;
import exceptions.ReservaRepetidaException;

/**
 * Clase principal de la logica
 * 
 * @author Tatiana Mora, Oscar Moreno, Cristian Sánchez
 *
 */
public class Espectaculo {
	private ArrayList<Date> fechas;
	private ArrayList<Escenario> misEscenarios;
	private HashMap<String, Cliente> misClientes;
	private HashMap<String, Administrador> misAdministradores;
	private HashMap<String, Reserva> misReservas;

	public Espectaculo() {
		fechas = new ArrayList<Date>();
		misEscenarios = new ArrayList<Escenario>();
		misClientes = new HashMap<String, Cliente>();
		misAdministradores = new HashMap<String, Administrador>();
		setMisReservas(new HashMap<String, Reserva>());
	}

	public HashMap<String, Administrador> getMisAdministradores() {
		return misAdministradores;
	}

	public void setMisAdministradores(HashMap<String, Administrador> misAdministradores) {
		this.misAdministradores = misAdministradores;
	}

	public HashMap<String, Reserva> getMisReservas() {
		return misReservas;
	}

	public void setMisReservas(HashMap<String, Reserva> misReservas) {
		this.misReservas = misReservas;
	}

	public ArrayList<Date> getFechas() {
		return fechas;
	}

	public void setFechas(ArrayList<Date> fechas) {
		this.fechas = fechas;
	}

	public ArrayList<Escenario> getMisEscenarios() {
		return misEscenarios;
	}

	public void setMisEscenarios(ArrayList<Escenario> misEscenarios) {
		this.misEscenarios = misEscenarios;
	}

	public HashMap<String, Cliente> getMisClientes() {
		return misClientes;
	}

	public void setMisClientes(HashMap<String, Cliente> misClientes) {
		this.misClientes = misClientes;
	}

	/**
	 * Verificar si una fecha existe
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * @return
	 */
	public int verificarExistenciaFecha(int day, int month, int year) {
		int indice = -1;
		boolean centi = false;
		Date aux = null;
		for (int i = 0; i < fechas.size() && centi == false; i++) {
			aux = fechas.get(i);
			if (aux.getDay() == day && aux.getMonth() == month && aux.getYear() == year) {

				centi = true;
				indice = i;
			}

		}
		return indice;
	}

	/**
	 * Agregar una fecha al arraylist de fechas
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * @throws FechaExistente
	 */
	public void agregarFecha(int day, int month, int year) throws FechaExistente {
		if (verificarExistenciaFecha(day, month, year) == -1) {
			Date fecha = new Date(day, month, year);
			fechas.add(fecha);
		} else {

			throw new FechaExistente("La fecha: " + day + "/" + month + "/" + year + " ya existe");
		}
	}

	/**
	 * Eliminar una fecha del arraylist de fecha
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * @throws FechaExistente
	 */
	public void eliminarFecha(int day, int month, int year) throws FechaExistente {
		for (int i = 0; i < fechas.size(); i++) {

			Date aux = fechas.get(i);

			if (aux.getDay() == day && aux.getMonth() == month && aux.getYear() == year) {
				fechas.remove(aux);
			} else {

				throw new FechaExistente("La fecha: " + day + "/" + month + "/" + year + " no existe");
			}
		}
	}

	/**
	 * Metodo que permite verificar si existe un cliente en el espectaculo
	 * 
	 * @param id
	 * @return un booleano verificando la existencia del cliente
	 */
	public boolean estaElCliente(String id) {
		return misClientes.containsKey(id);
	}

	/**
	 * Metodo que permite verificar si existe un administrador en el espectaculo
	 * 
	 * @param id
	 * @return un booleano verificando la existencia del cliente
	 */
	public boolean estaElAdministrador(String id) {
		return misAdministradores.containsKey(id);
	}

	/**
	 * Metodo para agregar un administrador
	 * 
	 * @return
	 */

	public boolean agregarAdministrador(String nombre, String apellido, String id, Genero miGenero, String email,
			String contrasenia) throws AdministradorRepetidoException {
		boolean agregadoCompleto = false;

		if (estaElAdministrador(id)) {
			throw new AdministradorRepetidoException("El administrador: " + nombre + " " + apellido + " id: " + id
					+ " ya se encuentra en el espectaculo");
		}

		getMisAdministradores().put(id, new Administrador(nombre, apellido, id, miGenero, email, contrasenia, this));
		agregadoCompleto = true;
		return agregadoCompleto;
	}

	/**
	 * Metodo para eliminar un administrador
	 * 
	 * @param id
	 * @return
	 * @throws AdministradorNoExistenteException
	 */

	public Administrador removerAdministrador(String id) throws AdministradorNoExistenteException {

		if (!estaElAdministrador(id)) {
			throw new AdministradorNoExistenteException(
					"El administrador con id: " + id + " no se encuentra registrado en el espectadulo.");
		}
		Administrador miAdministrador = getMisAdministradores().remove(id);
		return miAdministrador;
	}

	/**
	 * Metodo que permite agregar una reserva
	 * 
	 * @param id de la reserva
	 * @throws ReservaRepetidaException si la reserva esta repetida
	 */
	public void agregarReserva(String id) throws ReservaRepetidaException {
		if (getMisReservas().containsKey(id))
			throw new ReservaRepetidaException("La reserva: (id) " + id + " ya se encuentra en el espectaculo");
		getMisReservas().put(id, new Reserva(id));
	}
	/**
	 * Metodo que permite eliminar una reserva
	 * @param id de la reserva
	 * @return la reserva eliminada
	 * @throws ReservaNoExisteException si la reserva no existe
	 */
	public Reserva eliminarReserva(String id) throws ReservaNoExisteException {
		if(!getMisReservas().containsKey(id))
			throw new ReservaNoExisteException("La reserva: (id) "+id+" no existe en el espectaculo");
		return getMisReservas().remove(id);
	}
	
}