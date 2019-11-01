package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import exceptions.*;

/**
 * Clase principal de la logica
 * 
 * @author Tatiana Mora, Oscar Moreno, Cristian Sánchez
 *
 */
public class Boleteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String, Espectaculo> misEspectaculos;
	private HashMap<String, Administrador> misAdministradores;
	private HashMap<String, Cliente> misClientes;

	/**
	 * Metodo constructor vacio de la clase Boleteria
	 */
	public Boleteria() {
		misEspectaculos = new HashMap<String, Espectaculo>();
		misAdministradores = new HashMap<String, Administrador>();
		misClientes = new HashMap<String, Cliente>();
	}

	public HashMap<String, Espectaculo> getMisEspectaculos() {
		return misEspectaculos;
	}

	public void setMisEspectaculos(HashMap<String, Espectaculo> misEspectaculos) {
		this.misEspectaculos = misEspectaculos;
	}

	public HashMap<String, Administrador> getMisAdministradores() {
		return misAdministradores;
	}

	public void setMisAdministradores(HashMap<String, Administrador> misAdministradores) {
		this.misAdministradores = misAdministradores;
	}

	public HashMap<String, Cliente> getMisClientes() {
		return misClientes;
	}

	public void setMisClientes(HashMap<String, Cliente> misClientes) {
		this.misClientes = misClientes;
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
	 * Metodo que permite verificar si existe un administrador en el espectaculo
	 * 
	 * @param id
	 * @return un booleano verificando la existencia del cliente
	 */
	public boolean estaElAdministrador(String id) {
		return misAdministradores.containsKey(id);
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
			String email, Cuenta miCuentaAsociada, Boleta miBoletaAsociada, Date miFechaDeNacimiento,
			String ciudadDeResidencia, EstratoSocioeconomico miEstrato, EstadoCivil miEstadoCivil,
			NivelDeEstudio miNivelDeEstudio, String contrasenia) throws ClienteRepetidoException {
		boolean agregadoCompleto = false;
		if (estaElCliente(id)) {
			throw new ClienteRepetidoException(
					"El cliente: " + nombre + " " + apellido + " id: " + id + " ya se encuentra en el espectaculo");
		}

		getMisClientes().put(id,
				new Cliente(nombre, apellido, id, miGenero, direccion, email, miCuentaAsociada, miBoletaAsociada,
						miFechaDeNacimiento, ciudadDeResidencia, miEstrato, miEstadoCivil, miNivelDeEstudio,
						contrasenia));
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

	/**
	 * Metodo que permite verificar si existe el espectaculo
	 * 
	 * @param nombre del espectaculo
	 * @return un booleano con la verificacion si esta o no el espectaculo
	 */
	public boolean estaElEspectaculo(String nombre) {
		return misEspectaculos.containsKey(nombre);
	}

	/**
	 * Metodo que permite obtener un espectaculo segun el nombre
	 * 
	 * @param nombre del espectaculo
	 * @return el espectaculo si existe en la boleteria
	 * @throws EspectaculoNullException si el espectaculo no se encuentra en la
	 *                                  boleteria
	 */
	public Espectaculo obtenerEspectaculo(String nombre) throws EspectaculoNullException {
		if (!estaElEspectaculo(nombre))
			throw new EspectaculoNullException("El espectaculo: " + nombre + " no se encuentra en la boleteria");
		return misEspectaculos.get(nombre);
	}

	/**
	 * Metodo que permite remover un espectaculo
	 * 
	 * @param nombre del espectaculo
	 * @return la informacion del espectaculo eliminado
	 * @throws EspectaculoNullException si el espectaculo no se encuentra en la
	 *                                  boleteria
	 */
	public Espectaculo removerEspectaculo(String nombre) throws EspectaculoNullException {
		if (!estaElEspectaculo(nombre))
			throw new EspectaculoNullException("El espectaculo: " + nombre + " no se encuentra en la boleteria");
		return misEspectaculos.remove(nombre);
	}

	/**
	 * Metodo que permite agregar un espectaculo
	 * 
	 * @param nombre del espectaculo
	 * @return un booleano si agrego o no el espectaculo
	 * @throws EspectaculoRepetidoException si el espectaculo ya se encuentra en la
	 *                                      boleteria
	 */
	public boolean agregarEspectaculo(String nombre, TipoEspectaculo miTipo) throws EspectaculoRepetidoException {
		if (estaElEspectaculo(nombre))
			throw new EspectaculoRepetidoException("El espectaculo: " + nombre + " ya se encuentra en la boleteria");
		Espectaculo miEspectaculoNuevo = new Espectaculo(nombre, miTipo);
		misEspectaculos.put(nombre, miEspectaculoNuevo);
		return true;
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

	/**
	 * Metodo que permite obtener una lista de espectaculos
	 * 
	 * @return un {@link ArrayList} de espectaculos
	 */
	public ArrayList<Espectaculo> obtenerListaEspectaculos() {
		ArrayList<Espectaculo> misEspectaculosLista = new ArrayList<Espectaculo>();
		Iterator<String> iterator = misEspectaculos.keySet().iterator();
		String nombreAux;
		while (iterator.hasNext()) {
			nombreAux = iterator.next();
			misEspectaculosLista.add(misEspectaculos.get(nombreAux));
		}
		return misEspectaculosLista;
	}

}
