package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Escenario implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Seccion> misSecciones;
	/**
	 * Metodo constructor de la clase Escenario
	 */
	public Escenario() {
		misSecciones = new ArrayList<Seccion>();
	}
	/**
	 * Metodo que permite agregar una seccion 
	 * 
	 * @param miTipo
	 */
	public void agregarSeccion(TipoSeccion miTipo)
	{
		misSecciones.add(new Seccion(miTipo, this));
	}

	public ArrayList<Seccion> getMisSecciones() {
		return misSecciones;
	}

	public void setMisSecciones(ArrayList<Seccion> misSecciones) {
		this.misSecciones = misSecciones;
	}
}
