package model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import exceptions.FechaExistenteException;
import exceptions.ReservaNoExisteException;
import exceptions.ReservaRepetidaException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Espectaculo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private TipoEspectaculo miTipo;
	private ArrayList<Date> fechas;
	private ArrayList<Escenario> misEscenarios;
	private HashMap<String, Cliente> misAsistentes;
	private HashMap<String, Reserva> misReservas;

	/**
	 * Constructor de la clase
	 * 
	 * @param nombre del espectaculo
	 */
	public Espectaculo(String nombre, TipoEspectaculo miTipo) {
		this.nombre = nombre;
		fechas = new ArrayList<Date>();
		fechas.add(new Date());

		Escenario miEscenario = new Escenario();
		misEscenarios = new ArrayList<Escenario>();
		misEscenarios.add(miEscenario);
		misAsistentes = new HashMap<String, Cliente>();
		this.miTipo = miTipo;
		setMisReservas(new HashMap<String, Reserva>());
		initSecciones();
	}

	/**
	 * Constructor vacio de la clase Espectaculo
	 */
	public Espectaculo() {
		fechas = new ArrayList<Date>();
		misAsistentes = new HashMap<String, Cliente>();
		misEscenarios = new ArrayList<Escenario>();
		setMisReservas(new HashMap<String, Reserva>());
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
	 * @throws FechaExistenteException
	 */
	public void agregarFecha(int day, int month, int year) throws FechaExistenteException {
		if (verificarExistenciaFecha(day, month, year) == -1) {
			Date fecha = new Date(day, month, year);
			fechas.add(fecha);
		} else {

			throw new FechaExistenteException("La fecha: " + day + "/" + month + "/" + year + " ya existe");
		}
	}

	/**
	 * Eliminar una fecha del arraylist de fecha
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * @throws FechaExistenteException
	 */
	public void eliminarFecha(int day, int month, int year) throws FechaExistenteException {
		for (int i = 0; i < fechas.size(); i++) {

			Date aux = fechas.get(i);

			if (aux.getDay() == day && aux.getMonth() == month && aux.getYear() == year) {
				fechas.remove(aux);
			} else {

				throw new FechaExistenteException("La fecha: " + day + "/" + month + "/" + year + " no existe");
			}
		}
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

	public void programarEvento(Date fechaDePresentacion) {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public HashMap<String, Cliente> getMisAsistentes() {
		return misAsistentes;
	}

	public TipoEspectaculo getMiTipo() {
		return miTipo;
	}

	public void setMiTipo(TipoEspectaculo miTipo) {
		this.miTipo = miTipo;
	}

	public void setMisAsistentes(HashMap<String, Cliente> misAsistentes) {
		this.misAsistentes = misAsistentes;
	}

	/**
	 * Metodo que permite obtener un listado de asistentes segun una seccion
	 * 
	 * @param miSeccion a buscar
	 * @return un listado de los clientes asistentes segun la seccion
	 */
	public ArrayList<Cliente> obtenerListadoClientesSegunSeccion(Seccion miSeccion) {
		ArrayList<Cliente> misClientes = new ArrayList<Cliente>();
		Iterator<String> iterator = misAsistentes.keySet().iterator();
		String nombreAux = "";
		Boleta miBoleta;
		TipoSeccion tipoSeccion;
		Puesto miPuesto;
		while (iterator.hasNext()) {
			nombreAux = iterator.next();
			miBoleta = misAsistentes.get(nombreAux).getMiBoletaAsociada();
			miPuesto = miBoleta.getMiPuesto();
			tipoSeccion = miPuesto.getMiSeccionAsociada().getMiTipo();
			if (tipoSeccion == miSeccion.getMiTipo())
				misClientes.add(misAsistentes.get(nombreAux));
		}
		return misClientes;
	}

	/**
	 * Metodo que permite obtener un listado de clientes segun el estrato socio
	 * economico
	 * 
	 * @param miEstrato estrato socioeconomico
	 * @return un listado de clientes segun el estrato socioeconomico
	 */
	public ArrayList<Cliente> obtenerListadoClientesSegunEstrato(EstratoSocioeconomico miEstrato) {
		ArrayList<Cliente> misClientes = new ArrayList<Cliente>();
		Iterator<String> iterator = misAsistentes.keySet().iterator();
		String nombreAux = "";
		while (iterator.hasNext()) {
			nombreAux = iterator.next();
			if (misAsistentes.get(nombreAux).getMiEstrato() == miEstrato)
				misClientes.add(misAsistentes.get(nombreAux));
		}
		return misClientes;
	}

	/**
	 * Metodo que permite obtener un {@link StringProperty} con la informacion del
	 * nombre del espectaculo
	 * 
	 * @return un {@link StringProperty}
	 */
	public StringProperty nombreProperty() {
		return new SimpleStringProperty(nombre);
	}

	/**
	 * Metodo que permite obtener un {@link StringProperty} con la informacion del
	 * tipo del espectaculo
	 * 
	 * @return un {@link StringProperty}
	 */
	public StringProperty tipoProperty() {
		return new SimpleStringProperty(getMiTipo().toString());
	}

	/**
	 * Metodo que permite obtener un {@link StringProperty} con la informacion de la
	 * primera fecha
	 * 
	 * @return un {@link StringProperty}
	 */
	public StringProperty fechasProperty() {
		return new SimpleStringProperty(fechas.get(0).toString() + " ");
	}

	public void initSecciones() {
		Escenario miEscenario = getMisEscenarios().get(0);
		miEscenario.agregarSeccion(TipoSeccion.PLATEA_1);
		miEscenario.agregarSeccion(TipoSeccion.PLATEA_2);
		miEscenario.agregarSeccion(TipoSeccion.PLATEA_3);
		miEscenario.agregarSeccion(TipoSeccion.PRIMER_PISO_1);
		miEscenario.agregarSeccion(TipoSeccion.PRIMER_PISO_2);
		miEscenario.agregarSeccion(TipoSeccion.PRIMER_PISO_3);
		miEscenario.agregarSeccion(TipoSeccion.SEGUNDO_PISO_1);
		miEscenario.agregarSeccion(TipoSeccion.SEGUNDO_PISO_2);
		miEscenario.agregarSeccion(TipoSeccion.SEGUNDO_PISO_3);
		miEscenario.getMisSecciones().get(0).generarMatrizCuadrada(10);
		miEscenario.getMisSecciones().get(1).generarMatrizTriangularSuperior(10);
		miEscenario.getMisSecciones().get(2).generarMatrizTriangularSuperiorInversa(10);
		miEscenario.getMisSecciones().get(3).generarMatrizCuadrada(10);
		miEscenario.getMisSecciones().get(4).generarMatrizTriangularInferiorInversa(10);
		miEscenario.getMisSecciones().get(5).generarMatrizTriangularInferior(10);
		miEscenario.getMisSecciones().get(6).generarMatrizCuadrada(10);
		miEscenario.getMisSecciones().get(7).generarMatrizTriangularSuperior(10);
		miEscenario.getMisSecciones().get(8).generarMatrizTriangularSuperiorInversa(10);

	}
}