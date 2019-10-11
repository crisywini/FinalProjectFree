package model;

import java.io.Serializable;

public class Puesto implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Seccion miSeccionAsociada;
	private Boleta miBoletaAsociada;
	private char letra;
	private int numero;
	/**
	 * Constructor vacio de la clase puesto
	 */
	public Puesto() {
		this('X', 99);
	}
	/**
	 * Constructor completo de la clase puesto
	 * @param letra
	 * @param numero
	 */
	public Puesto(char letra, int numero)
	{
		this.letra = letra;
		this.numero = numero;
	}
	
	public char getLetra() {
		return letra;
	}
	public void setLetra(char letra) {
		this.letra = letra;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Seccion getMiSeccionAsociada() {
		return miSeccionAsociada;
	}
	public void setMiSeccionAsociada(Seccion miSeccionAsociada) {
		this.miSeccionAsociada = miSeccionAsociada;
	}
	public Boleta getMiBoletaAsociada() {
		return miBoletaAsociada;
	}
	public void setMiBoletaAsociada(Boleta miBoletaAsociada) {
		this.miBoletaAsociada = miBoletaAsociada;
	}
}
