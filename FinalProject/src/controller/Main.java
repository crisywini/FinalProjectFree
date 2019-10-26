package controller;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import exceptions.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.*;
import persistencia.*;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application implements IControlBoleteria {
	private Boleteria miBoleteria;

	@Override
	public void start(Stage primaryStage) {
		cargarDatos(Persistencia.BOLETERIA_RUTA);
		if (miBoleteria == null) {
			miBoleteria = new Boleteria();
		}
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
			primaryStage.setTitle("Boleteria");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// -----------------set y get

	public Boleteria getMiBoleteria() {
		return miBoleteria;
	}

	public void setMiBoleteria(Boleteria miBoleteria) {
		this.miBoleteria = miBoleteria;
	}
	// ------------------Persistencia------------

	public void guardarClientesEnArchivo() throws IOException {
		Persistencia.guardarClientesEnArchivo(getMisClientes());
	}

	public void guardarAdministradoresEnArchivo() throws IOException {
		Persistencia.guardarAdministradoresEnArchivo(getMisAdministradores());
	}

	public void guardarEspectaculosEnArchivo() throws IOException {
		Persistencia.guardarEspectaculosEnArchivo(null);
	}

	public void serializarBoleteria() throws IOException {
		guardarAdministradoresEnArchivo();
		guardarClientesEnArchivo();
		Persistencia.serializarXML(Persistencia.BOLETERIA_RUTA, getMiBoleteria());
	}

	public void crearArchivos() {
		if (Archivo.isCreatedFile(Persistencia.CLIENTES_RUTA))
			try {
				guardarClientesEnArchivo();
			} catch (Exception e) {
				e.printStackTrace();
			}
		if (Archivo.isCreatedFile(Persistencia.ADMINISTRADORES_RUTA))
			try {
				guardarAdministradoresEnArchivo();
			} catch (Exception e) {
				e.printStackTrace();
			}
		if (Archivo.isCreatedFile(Persistencia.ESPECTACULOS_RUTA))
			try {
				guardarEspectaculosEnArchivo();
			} catch (Exception e) {
				e.printStackTrace();
			}
		if (Archivo.isCreatedFile(Persistencia.BOLETERIA_RUTA))
			try {
				serializarBoleteria();
				;
			} catch (Exception e) {
				e.printStackTrace();
			}

	}

	public void cargarDatos(String ruta) {
		Boleteria miBoleteriaAux = null;
		File archivo = new File(ruta);
		if (archivo.exists()) {
			try {
				miBoleteriaAux = (Boleteria) Persistencia.deserializarObjetoXML(ruta);
			} catch (Exception e) {
				e.printStackTrace();
			}
			setMiBoleteria(miBoleteriaAux);
		}
		crearArchivos();
	}
	// -----------------Services----------------

	@Override
	public boolean estaElAdministrador(String id) {
		return miBoleteria.estaElCliente(id);
	}

	@Override
	public Administrador removerAdministrador(String id) throws AdministradorNoExistenteException {
		return miBoleteria.removerAdministrador(id);
	}

	@Override
	public Administrador obtenerAdministrador(String id) throws AdministradorNoExistenteException {
		return miBoleteria.obtenerAdministrador(id);
	}

	@Override
	public boolean agregarAdministrador(String nombre, String apellido, String id, Genero miGenero, String email,
			String contrasenia) throws AdministradorRepetidoException {
		return miBoleteria.agregarAdministrador(nombre, apellido, id, miGenero, email, contrasenia);
	}

	@Override
	public void setMisAdministradores(HashMap<String, Administrador> misAdministradores) {
		miBoleteria.setMisAdministradores(misAdministradores);
	}

	@Override
	public HashMap<String, Administrador> getMisAdministradores() {
		return miBoleteria.getMisAdministradores();
	}

	@Override
	public boolean estaElCliente(String id) {
		return miBoleteria.estaElCliente(id);
	}

	@Override
	public Cliente removerCliente(String id) throws ClienteNoExistenteException {
		return miBoleteria.removerCliente(id);
	}

	@Override
	public Cliente obtenerCliente(String id) throws ClienteNoExistenteException {
		return miBoleteria.obtenerCliente(id);
	}

	@Override
	public boolean agregarCliente(String nombre, String apellido, String id, Genero miGenero, String direccion,
			String email, Cuenta miCuentaAsociada, Boleta miBoletaAsociada, Date miFechaDeNacimiento,
			String ciudadDeResidencia, EstratoSocioeconomico miEstrato, EstadoCivil miEstadoCivil,
			NivelDeEstudio miNivelDeEstudio, String contrasenia) throws ClienteRepetidoException {
		return miBoleteria.agregarCliente(nombre, apellido, id, miGenero, direccion, email, miCuentaAsociada,
				miBoletaAsociada, miFechaDeNacimiento, ciudadDeResidencia, miEstrato, miEstadoCivil, miNivelDeEstudio,
				contrasenia);
	}

	@Override
	public void setMisClientes(HashMap<String, Cliente> misClientes) {
		miBoleteria.setMisClientes(misClientes);
	}

	@Override
	public HashMap<String, Cliente> getMisClientes() {
		return miBoleteria.getMisClientes();
	}

	@Override
	public boolean estaElEspectaculo(String nombre) {
		return miBoleteria.estaElEspectaculo(nombre);
	}

	@Override
	public Espectaculo obtenerEspectaculo(String nombre) throws EspectaculoNullException {
		return miBoleteria.obtenerEspectaculo(nombre);
	}

	@Override
	public Espectaculo removerEspectaculo(String nombre) throws EspectaculoNullException {
		return miBoleteria.removerEspectaculo(nombre);
	}

	@Override
	public HashMap<String, Espectaculo> getMisEspectaculos() {
		return miBoleteria.getMisEspectaculos();
	}

	@Override
	public void setMisEspectaculos(HashMap<String, Espectaculo> misEspectaculos) {
		miBoleteria.setMisEspectaculos(misEspectaculos);
	}

	@Override
	public ArrayList<Genero> getMisGeneros() {
		return miBoleteria.getMisGeneros();
	}

	@Override
	public ArrayList<EstadoCivil> getEstadosCiviles() {
		return miBoleteria.getEstadosCiviles();
	}

	@Override
	public ArrayList<EstratoSocioeconomico> getEstratos() {
		return miBoleteria.getEstratos();
	}

	@Override
	public ArrayList<NivelDeEstudio> getMisEstudios() {
		return miBoleteria.getMisEstudios();
	}

	@Override
	public boolean agregarEspectaculo(String nombre, TipoEspectaculo miTipo) throws EspectaculoRepetidoException {
		return miBoleteria.agregarEspectaculo(nombre, miTipo);
	}

}