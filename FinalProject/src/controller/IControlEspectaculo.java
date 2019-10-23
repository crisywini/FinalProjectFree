package controller;

import java.util.ArrayList;
import java.util.HashMap;

import exceptions.AdministradorNoExistenteException;
import exceptions.AdministradorRepetidoException;
import exceptions.FechaExistente;
import exceptions.ReservaNoExisteException;
import exceptions.ReservaRepetidaException;
import model.Administrador;
import model.Cliente;
import model.Date;
import model.Escenario;
import model.Genero;
import model.Reserva;

public interface IControlEspectaculo extends IControlAdministrador {
	public HashMap<String, Administrador> getMisAdministradores();

	public void setMisAdministradores(HashMap<String, Administrador> misAdministradores);

	public HashMap<String, Reserva> getMisReservas();

	public void setMisReservas(HashMap<String, Reserva> misReservas);

	public ArrayList<Date> getFechas();

	public void setFechas(ArrayList<Date> fechas);

	public ArrayList<Escenario> getMisEscenarios();

	public void setMisEscenarios(ArrayList<Escenario> misEscenarios);

	public HashMap<String, Cliente> getMisClientes();

	public void setMisClientes(HashMap<String, Cliente> misClientes);

	public int verificarExistenciaFecha(int day, int month, int year);

	public void agregarFecha(int day, int month, int year) throws FechaExistente;

	public void eliminarFecha(int day, int month, int year) throws FechaExistente;

	public boolean estaElCliente(String id);

	public boolean estaElAdministrador(String id);

	public boolean agregarAdministrador(String nombre, String apellido, String id, Genero miGenero, String email,
			String contrasenia) throws AdministradorRepetidoException;

	public Administrador removerAdministrador(String id) throws AdministradorNoExistenteException;

	public void agregarReserva(String id) throws ReservaRepetidaException;

	public Reserva eliminarReserva(String id) throws ReservaNoExisteException;
}
