package model;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * Clase principal de la logica
 * @author Tatiana Mora, Oscar Moreno, Cristian Sánchez
 *
 */
public class Espectaculo 
{
	private ArrayList<Date> fechas;
	private ArrayList<Escenario> misEscenarios;
	private HashMap<String, Cliente> misClientes;

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
	 * Metodo que permite verificar si existe un cliente en el espectaculo
	 * @param id
	 * @return un booleano verificando la existencia del cliente
	 */
	public boolean estaElCliente(String id)
	{
		return misClientes.containsKey(id);
	}

}
