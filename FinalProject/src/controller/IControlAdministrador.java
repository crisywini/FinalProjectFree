package controller;

import exceptions.ClienteNoExistenteException;
import exceptions.ClienteRepetidoException;
import model.Cliente;
import model.Cuenta;
import model.Date;
import model.EstadoCivil;
import model.EstratoSocioeconomico;
import model.Genero;
import model.NivelDeEstudio;

public interface IControlAdministrador {
	//Revisar la cantidad de servicios que tiene un administrador
	public boolean agregarCliente(String nombre, String apellido, String id, Genero miGenero, String direccion, String email,
			Cuenta miCuentaAsociada, Date miFechaDeNacimiento, String ciudadDeResidencia,
			EstratoSocioeconomico miEstrato, EstadoCivil miEstadoCivil, NivelDeEstudio miNivelDeEstudio)throws ClienteRepetidoException;
	public Cliente removerCliente(String id)throws ClienteNoExistenteException;
	public void programarEvento(Date fechaDePresentacion);
}
