package model;

import java.io.Serializable;

import exceptions.DineroNoValidoException;

public class Cuenta implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double dineroTotal;
	private String id;
	private Cliente miClienteAsociado;
	
	public Cuenta() {
		this(0, "%$%$%$");
		setMiClienteAsociado(new Cliente());
	}
	public Cuenta(double dineroTotal, String id) {
		this.dineroTotal = dineroTotal;
		this.id = id;
	}

	public double getDineroTotal() {
		return dineroTotal;
	}

	public void setDineroTotal(double dineroTotal) {
		this.dineroTotal = dineroTotal;
	}

	public Cliente getMiClienteAsociado() {
		return miClienteAsociado;
	}

	public void setMiClienteAsociado(Cliente miClienteAsociado) {
		this.miClienteAsociado = miClienteAsociado;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Metodo que permite retirar dinero de una cuenta
	 * @param dineroARetirar
	 * @throws DineroNoValidoException
	 */
	public void retirarDinero(double dineroARetirar) throws DineroNoValidoException
	{
		if(dineroARetirar>dineroTotal)
			throw new DineroNoValidoException("El monto: "+dineroARetirar+" no es valido.");
		setDineroTotal(getDineroTotal()-dineroARetirar);
	}
	/**
	 * Metodo que permite agregar dinero a una cuenta
	 * @param monto
	 */
	public void agregarDinero(double monto)
	{
		setDineroTotal(getDineroTotal()+monto);
	}
}
