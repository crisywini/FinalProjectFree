package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

import exceptions.BoletaNoExisteException;
import exceptions.BoletaRepetidaException;
import exceptions.LimiteExcedidoException;

public class Reserva implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String ubicacionEntregaBoletas;
	private static final int LIMITE_DE_PUESTOS = 6;
	private HashMap<String, Boleta> misBoletas;
	private double valorTotalReserva;

	/**
	 * Metodo constructor de la clase Reserva
	 * 
	 * @param id de la reserva
	 */
	public Reserva(String id) {
		misBoletas = new HashMap<String, Boleta>();
		this.id = id;
	}

	/**
	 * Metodo por defecto de la clase Reserva
	 */
	public Reserva() {
		this("$$$$");
	}

	public HashMap<String, Boleta> getMisBoletas() {
		return misBoletas;
	}

	public void setMisBoletas(HashMap<String, Boleta> misBoletas) {
		this.misBoletas = misBoletas;
	}

	public String getUbicacionEntregaBoletas() {
		return ubicacionEntregaBoletas;
	}

	public void setUbicacionEntregaBoletas(String ubicacionEntregaBoletas) {
		this.ubicacionEntregaBoletas = ubicacionEntregaBoletas;
	}

	public double getValorTotalReserva() {
		return valorTotalReserva;
	}

	public void setValorTotalReserva(double valorTotalReserva) {
		this.valorTotalReserva = valorTotalReserva;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Metodo que permite calcular el precio total de la reserva teniendo en cuenta
	 * el valor de cada seccion
	 * 
	 * @param esEnFisico          si el cliente quiere entrego en fisico
	 * @param esSeguroDeCobertura si el cliente quiere pagar seguro de cobertura
	 */
	public void calcularPrecioTotal(boolean esSeguroDeCobertura) {
		double auxiliar = 0;
		Boleta miBoletaAuxiliar = null;
		Iterator<String> iterator = getMisBoletas().keySet().iterator();
		while (iterator.hasNext()) {
			miBoletaAuxiliar = getMisBoletas().get(iterator.next());
			auxiliar += miBoletaAuxiliar.getValor();
		}
		if (ubicacionEntregaBoletas.length() != 0)
			auxiliar *= (0.8 / 100) + auxiliar;
		if (esSeguroDeCobertura)
			auxiliar *= (0.12 / 100) + auxiliar;
		setValorTotalReserva(auxiliar);
	}

	/**
	 * Metodo que permite agregar una boleta
	 * 
	 * @param id                de la boleta
	 * @param miPuesto          asociado a la boleta
	 * @param miClienteAsociado a la boleta
	 * @throws BoletaRepetidaException si la boleta ya se encuentra en la reserva
	 * @throws BoletaNoExisteException si la boleta no existe al intentar eliminarla
	 * @throws LimiteExcedidoException si el limite de puestos esta excedido
	 */
	public void agregarBoleta(String id, Puesto miPuesto, Cliente miClienteAsociado)
			throws BoletaRepetidaException, BoletaNoExisteException, LimiteExcedidoException {
		if (getMisBoletas().containsKey(id))
			throw new BoletaRepetidaException("La boleta: (id)" + id + " ya se encuentra en la reserva");
		getMisBoletas().put(id, new Boleta(id, miPuesto, miClienteAsociado));
		if (getMisBoletas().size() > LIMITE_DE_PUESTOS) {
			eliminarBoleta(id);
			throw new LimiteExcedidoException(
					"No se puede agregar la boleta: (id) " + id + " ya que se ha excedido el limite");
		}
	}

	/**
	 * Metodo que permite eliminar una boleta
	 * 
	 * @param id de la boleta
	 * @return la boleta eliminada
	 * @throws BoletaNoExisteException si la boleta no existe en la reserva
	 */
	public Boleta eliminarBoleta(String id) throws BoletaNoExisteException {
		if (!getMisBoletas().containsKey(id))
			throw new BoletaNoExisteException("La boleta: (id) " + id + " no existe en la reserva");
		return getMisBoletas().remove(id);
	}

	/**
	 * Metodo que permite reservar todos los puestos(Antes de la compra)
	 */
	public void reservarTodosLosPuestos() {
		Puesto miPuestoAuxiliar;
		Iterator<String> iterator = getMisBoletas().keySet().iterator();
		while (iterator.hasNext()) {
			miPuestoAuxiliar = getMisBoletas().get(iterator.next()).getMiPuesto();
			miPuestoAuxiliar.setMiEstado(EstadoPuesto.RESERVADO);
		}
	}

	/**
	 * Metodo que permite ocupar todos los puestos(Despues de la compra)
	 */
	public void ocuparTodosLosPuestos() {
		Puesto miPuestoAuxiliar;
		Iterator<String> iterator = getMisBoletas().keySet().iterator();
		while (iterator.hasNext()) {
			miPuestoAuxiliar = getMisBoletas().get(iterator.next()).getMiPuesto();
			miPuestoAuxiliar.setMiEstado(EstadoPuesto.OCUPADO);
		}
	}
}