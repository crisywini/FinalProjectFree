package controller;

import java.util.ArrayList;
import java.util.HashMap;

import exceptions.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.*;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application implements IControlEspectaculo {
	private Espectaculo miEspectaculo;

	@Override
	public void start(Stage primaryStage) {
		miEspectaculo = new Espectaculo();
		showPrincipalPane(primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void showPrincipalPane(Stage primaryStage) {
		try {
			FXMLLoader cargador = new FXMLLoader();
			cargador.setLocation(Main.class.getResource("../view/PrincipalPane.fxml"));
			BorderPane panelPrincipal = (BorderPane) cargador.load();
			Scene scene = new Scene(panelPrincipal);
			PrincipalController controlador = cargador.getController();
			controlador.setPrincipalPane(panelPrincipal);
			controlador.setPrincipal(this);
			controlador.setPrincipalStage(primaryStage);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// -----------------set y get
	public Espectaculo getMiEspectaculo() {
		return miEspectaculo;
	}

	public void setMiEspectaculo(Espectaculo miEspectaculo) {
		this.miEspectaculo = miEspectaculo;
	}

	// -----------------Services----------------
	@Override
	public boolean agregarCliente(String nombre, String apellido, String id, Genero miGenero, String direccion,
			String email, Cuenta miCuentaAsociada, Date miFechaDeNacimiento, String ciudadDeResidencia,
			EstratoSocioeconomico miEstrato, EstadoCivil miEstadoCivil, NivelDeEstudio miNivelDeEstudio)
			throws ClienteRepetidoException {
		return getMiEspectaculo().agregarCliente(nombre, apellido, id, miGenero, direccion, email, miCuentaAsociada,
				miFechaDeNacimiento, ciudadDeResidencia, miEstrato, miEstadoCivil, miNivelDeEstudio);
	}

	@Override
	public Cliente removerCliente(String id) throws ClienteNoExistenteException {
		return getMiEspectaculo().removerCliente(id);
	}

	@Override
	public void programarEvento(Date fechaDePresentacion) {
		getMiEspectaculo().programarEvento(fechaDePresentacion);
	}

	@Override
	public HashMap<String, Administrador> getMisAdministradores() {
		return getMiEspectaculo().getMisAdministradores();
	}

	@Override
	public void setMisAdministradores(HashMap<String, Administrador> misAdministradores) {
		getMiEspectaculo().setMisAdministradores(misAdministradores);

	}

	@Override
	public HashMap<String, Reserva> getMisReservas() {
		return getMiEspectaculo().getMisReservas();
	}

	@Override
	public void setMisReservas(HashMap<String, Reserva> misReservas) {
		getMiEspectaculo().setMisReservas(misReservas);

	}

	@Override
	public ArrayList<Date> getFechas() {
		return getMiEspectaculo().getFechas();
	}

	@Override
	public void setFechas(ArrayList<Date> fechas) {
		getMiEspectaculo().setFechas(fechas);

	}

	@Override
	public ArrayList<Escenario> getMisEscenarios() {
		return getMiEspectaculo().getMisEscenarios();
	}

	@Override
	public void setMisEscenarios(ArrayList<Escenario> misEscenarios) {
		getMiEspectaculo().setMisEscenarios(misEscenarios);

	}

	@Override
	public HashMap<String, Cliente> getMisClientes() {
		return getMiEspectaculo().getMisClientes();
	}

	@Override
	public void setMisClientes(HashMap<String, Cliente> misClientes) {
		getMiEspectaculo().setMisClientes(misClientes);

	}

	@Override
	public int verificarExistenciaFecha(int day, int month, int year) {
		return getMiEspectaculo().verificarExistenciaFecha(day, month, year);
	}

	@Override
	public void agregarFecha(int day, int month, int year) throws FechaExistente {
		getMiEspectaculo().agregarFecha(day, month, year);

	}

	@Override
	public void eliminarFecha(int day, int month, int year) throws FechaExistente {
		getMiEspectaculo().eliminarFecha(day, month, year);

	}

	@Override
	public boolean estaElCliente(String id) {
		return getMiEspectaculo().estaElCliente(id);
	}

	@Override
	public boolean estaElAdministrador(String id) {
		return getMiEspectaculo().estaElAdministrador(id);
	}

	@Override
	public boolean agregarAdministrador(String nombre, String apellido, String id, Genero miGenero, String email,
			String contrasenia) throws AdministradorRepetidoException {
		return getMiEspectaculo().agregarAdministrador(nombre, apellido, id, miGenero, email, contrasenia);
	}

	@Override
	public Administrador removerAdministrador(String id) throws AdministradorNoExistenteException {
		return getMiEspectaculo().removerAdministrador(id);
	}

	@Override
	public void agregarReserva(String id) throws ReservaRepetidaException {
		getMiEspectaculo().agregarReserva(id);

	}

	@Override
	public Reserva eliminarReserva(String id) throws ReservaNoExisteException {
		return getMiEspectaculo().eliminarReserva(id);
	}
	public ArrayList<Genero> getMisGeneros()
	{
		return getMiEspectaculo().getMisGeneros();
	}

	@Override
	public Cliente obtenerCliente(String id) throws ClienteNoExistenteException {
		return getMiEspectaculo().obtenerCliente(id);
	}

	@Override
	public Administrador obtenerAdministrador(String id) throws AdministradorNoExistenteException {
		return getMiEspectaculo().obtenerAdministrador(id);
	}
}
