package model;

import java.util.ArrayList;
import java.util.HashMap;

import exceptions.AdministradorNoExistenteException;
import exceptions.AdministradorRepetidoException;
import exceptions.ClienteNoExistenteException;
import exceptions.ClienteRepetidoException;
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
	 * Metodo que permite obtener un administrador
	 * 
	 * @param id del administrador
	 * @return el administrador
	 * @throws AdministradorNoExistenteException si el administrador no existe
	 */
	public Administrador obtenerAdministrador(String id) throws AdministradorNoExistenteException {
		if (!estaElAdministrador(id))
			throw new AdministradorNoExistenteException("El administrador: " + id + " no esta registrado");
		return misAdministradores.get(id);
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
	 * Metodo que permite obtener una reserca
	 * 
	 * @param id de la reserva
	 * @return la reserva requerida
	 * @throws ReservaNoExisteException si la reserva no existe
	 */
	public Reserva obtenerReserva(String id) throws ReservaNoExisteException {
		if (!getMisReservas().containsKey(id))
			throw new ReservaNoExisteException("La reserva: " + id + " no esta registrada");
		return misReservas.get(id);
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
	 * 
	 * @param id de la reserva
	 * @return la reserva eliminada
	 * @throws ReservaNoExisteException si la reserva no existe
	 */
	public Reserva eliminarReserva(String id) throws ReservaNoExisteException {
		if (!getMisReservas().containsKey(id))
			throw new ReservaNoExisteException("La reserva: (id) " + id + " no existe en el espectaculo");
		return getMisReservas().remove(id);
	}

	/**
	 * Metodo que permite obtener un cliente
	 * 
	 * @param id del cliente
	 * @return un cliente
	 * @throws ClienteNoExistenteException si el cliente no esta registrado
	 */
	public Cliente obtenerCliente(String id) throws ClienteNoExistenteException {
		Cliente miCliente = null;
		if (!misClientes.containsKey(id))
			throw new ClienteNoExistenteException("El Usuario: " + id + " no esta ingresado en los datos");
		miCliente = misClientes.get(id);
		return miCliente;
	}

	/**
	 * Metodo que permite Agregar un cliente
	 * 
	 * @param nombre              del cliente
	 * @param apellido            del cliente
	 * @param id                  del cliente
	 * @param miGenero            del cliente
	 * @param direccion           del cliente
	 * @param email               del cliente
	 * @param miCuentaAsociada    del cliente
	 * @param miFechaDeNacimiento del cliente
	 * @param ciudadDeResidencia  del cliente
	 * @param miEstrato           del cliente
	 * @param miEstadoCivil       del cliente
	 * @param miNivelDeEstudio    del cliente
	 * @return un boolean en true si agrego el cliente, si no una exception
	 * @throws ClienteRepetidoException si el cliente esta repetido
	 */
	public boolean agregarCliente(String nombre, String apellido, String id, Genero miGenero, String direccion,
			String email, Cuenta miCuentaAsociada, Date miFechaDeNacimiento, String ciudadDeResidencia,
			EstratoSocioeconomico miEstrato, EstadoCivil miEstadoCivil, NivelDeEstudio miNivelDeEstudio,
			String contrasenia) throws ClienteRepetidoException {
		boolean agregadoCompleto = false;
		if (estaElCliente(id)) {
			throw new ClienteRepetidoException(
					"El cliente: " + nombre + " " + apellido + " id: " + id + " ya se encuentra en el espectaculo");
		}

		getMisClientes().put(id, new Cliente(nombre, apellido, id, miGenero, direccion, email, miCuentaAsociada,
				miFechaDeNacimiento, ciudadDeResidencia, miEstrato, miEstadoCivil, miNivelDeEstudio, contrasenia));
		agregadoCompleto = true;
		return agregadoCompleto;
	}

	/**
	 * Metodo que permite remover un cliente
	 * 
	 * @param id del cliene
	 * @return la informacion del cliente eliminado
	 * @throws ClienteNoExistenteException si el cliente no existe
	 */
	public Cliente removerCliente(String id) throws ClienteNoExistenteException {
		if (!estaElCliente(id))
			throw new ClienteNoExistenteException(
					"El cliente con id: " + id + " no se encuentra registrado en el espectadulo.");
		Cliente miCliente = getMisClientes().remove(id);
		return miCliente;
	}

	public void programarEvento(Date fechaDePresentacion) {
	}

	/**
	 * Metodo que permite obtener un ArrayList de generos
	 * 
	 * @return un ArrayList con los generos
	 */
	public ArrayList<Genero> getMisGeneros() {
		ArrayList<Genero> misGeneros = new ArrayList<Genero>();
		misGeneros.add(Genero.CIS_MUJER);
		misGeneros.add(Genero.CIS_HOMBRE);
		misGeneros.add(Genero.MUJER);
		misGeneros.add(Genero.HOMBRE);
		misGeneros.add(Genero.NO_BINARIO);
		misGeneros.add(Genero.TRANSGENERO);
		return misGeneros;
	}

	/**
	 * Metodo que permite obtener un ArrayList de los estados civiles
	 * 
	 * @return un ArrayList con los estados civiles
	 */
	public ArrayList<EstadoCivil> getEstadosCiviles() {
		ArrayList<EstadoCivil> misEstados = new ArrayList<EstadoCivil>();
		misEstados.add(EstadoCivil.CASADOA);
		misEstados.add(EstadoCivil.COMPROMETIDOA);
		misEstados.add(EstadoCivil.DIVORCIADOA);
		misEstados.add(EstadoCivil.NOVIAZGO);
		misEstados.add(EstadoCivil.SEPARADOA);
		misEstados.add(EstadoCivil.SOLTEROA);
		misEstados.add(EstadoCivil.UNION_LIBRE);
		misEstados.add(EstadoCivil.VIUDOA);
		return misEstados;
	}

	/**
	 * Metodo que permite obtener un ArrayList con los estratos socio economicos
	 * 
	 * @return un ArrayList con los estratos socio economicos
	 */
	public ArrayList<EstratoSocioeconomico> getEstratos() {
		ArrayList<EstratoSocioeconomico> misEstratos = new ArrayList<EstratoSocioeconomico>();
		misEstratos.add(EstratoSocioeconomico.ALTO);
		misEstratos.add(EstratoSocioeconomico.MEDIO_ALTO);
		misEstratos.add(EstratoSocioeconomico.MEDIO);
		misEstratos.add(EstratoSocioeconomico.MEDIO_BAJO);
		misEstratos.add(EstratoSocioeconomico.BAJO);
		misEstratos.add(EstratoSocioeconomico.BAJO_BAJO);
		return misEstratos;
	}

	/**
	 * Metodo que permite obtener un ArrayList con los niveles de estudio
	 * 
	 * @return un ArrayList con los niveles de estudio
	 */
	public ArrayList<NivelDeEstudio> getMisEstudios() {
		ArrayList<NivelDeEstudio> misNiveles = new ArrayList<NivelDeEstudio>();
		misNiveles.add(NivelDeEstudio.BACHILLER);
		misNiveles.add(NivelDeEstudio.PREGRADO);
		misNiveles.add(NivelDeEstudio.MAESTRIA);
		misNiveles.add(NivelDeEstudio.DOCTORADO);
		return misNiveles;
	}
}