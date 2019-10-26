package controller;

import java.util.HashMap;

import exceptions.EspectaculoNullException;
import exceptions.EspectaculoRepetidoException;
import model.Espectaculo;

public interface IControlEspectaculo {
	public boolean estaElEspectaculo(String nombre);

	public Espectaculo obtenerEspectaculo(String nombre) throws EspectaculoNullException;

	public Espectaculo removerEspectaculo(String nombre) throws EspectaculoNullException;

	public boolean agregarEspectaculo(String nombre) throws EspectaculoRepetidoException;

	public HashMap<String, Espectaculo> getMisEspectaculos();

	public void setMisEspectaculos(HashMap<String, Espectaculo> misEspectaculos);
}
