package model;

public class Puesto {
	private Seccion miSeccionAsociada;
	private Boleta miBoletaAsociada;
	private char letra;
	private int numero;

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
}
