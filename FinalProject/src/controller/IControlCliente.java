package controller;

import java.util.HashMap;

import exceptions.ClienteNoExistenteException;
import exceptions.ClienteRepetidoException;
import model.Boleta;
import model.Cliente;
import model.Cuenta;
import model.Date;
import model.EstadoCivil;
import model.EstratoSocioeconomico;
import model.Genero;
import model.NivelDeEstudio;

public interface IControlCliente {
	public boolean estaElCliente(String id);

	public Cliente removerCliente(String id) throws ClienteNoExistenteException;

	public Cliente obtenerCliente(String id) throws ClienteNoExistenteException;

	public boolean agregarCliente(String nombre, String apellido, String id, Genero miGenero, String direccion,
			String email, Cuenta miCuentaAsociada, Boleta miBoletaAsociada, Date miFechaDeNacimiento,
			String ciudadDeResidencia, EstratoSocioeconomico miEstrato, EstadoCivil miEstadoCivil,
			NivelDeEstudio miNivelDeEstudio, String contrasenia) throws ClienteRepetidoException;

	public void setMisClientes(HashMap<String, Cliente> misClientes);

	public HashMap<String, Cliente> getMisClientes();
}
