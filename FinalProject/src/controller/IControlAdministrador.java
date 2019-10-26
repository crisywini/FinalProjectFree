package controller;

import java.util.HashMap;

import exceptions.AdministradorNoExistenteException;
import exceptions.AdministradorRepetidoException;
import model.Administrador;
import model.Genero;

public interface IControlAdministrador {
	public boolean estaElAdministrador(String id);

	public Administrador removerAdministrador(String id) throws AdministradorNoExistenteException;

	public Administrador obtenerAdministrador(String id) throws AdministradorNoExistenteException;

	public boolean agregarAdministrador(String nombre, String apellido, String id, Genero miGenero, String email,
			String contrasenia) throws AdministradorRepetidoException;

	public void setMisAdministradores(HashMap<String, Administrador> misAdministradores);

	public HashMap<String, Administrador> getMisAdministradores();
}
