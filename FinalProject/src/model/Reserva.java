package model;

import java.util.ArrayList;

public class Reserva 
{
	private String ubicacionEntregaBoletas;
	private ArrayList<Boleta> misBoletas;

	public String getUbicacionEntregaBoletas() {
		return ubicacionEntregaBoletas;
	}

	public void setUbicacionEntregaBoletas(String ubicacionEntregaBoletas) {
		this.ubicacionEntregaBoletas = ubicacionEntregaBoletas;
	}

	public ArrayList<Boleta> getMisBoletas() {
		return misBoletas;
	}

	public void setMisBoletas(ArrayList<Boleta> misBoletas) {
		this.misBoletas = misBoletas;
	}
}