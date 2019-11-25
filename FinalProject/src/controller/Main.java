package controller;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import exceptions.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.*;
import persistencia.*;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

public class Main extends Application implements IControlBoleteria {
	private static Boleteria miBoleteria;
	public static final ObservableList<Espectaculo> espectaculosData = FXCollections.observableArrayList();
	public static final ObservableList<Cliente> clienteData = FXCollections.observableArrayList();
	static final EventHandler<WindowEvent> closer = new EventHandler<WindowEvent>() {

		@Override
		public void handle(WindowEvent event) {
			if (elegirGuardar()) {
				try {
					serializarStatic();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.exit(0);
		}
	};

	public Main() {
		miBoleteria = new Boleteria();
	}

	@Override
	public void start(Stage primaryStage) {
		cargarDatos(Persistencia.BOLETERIA_RUTA_DAT);
		espectaculosData.addAll(miBoleteria.obtenerListaEspectaculos());
		clienteData.addAll(miBoleteria.obtenerListadoClientes());
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
			primaryStage.setResizable(false);
			primaryStage.setOnCloseRequest(closer);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// -----------------set y get----------------

	public Boleteria getMiBoleteria() {
		return miBoleteria;
	}

	public void setMiBoleteria(Boleteria miBoleteria) {
		Main.miBoleteria = miBoleteria;
	}
	// ------------------Persistencia------------

	public void guardarClientesEnArchivo() throws IOException {

		Persistencia.guardarClientesEnArchivo(getMisClientes());
	}

	public void guardarAdministradoresEnArchivo() throws IOException {
		Persistencia.guardarAdministradoresEnArchivo(getMisAdministradores());
	}

	public void guardarEspectaculosEnArchivo() throws IOException {
		Persistencia.guardarEspectaculosEnArchivo(getMisEspectaculos());
	}

	public void serializarBoleteria() throws IOException {
		Persistencia.serializarObjeto(Persistencia.BOLETERIA_RUTA_DAT, miBoleteria);
	}

	public static void serializarStatic() throws IOException {
		Persistencia.serializarObjeto(Persistencia.BOLETERIA_RUTA_DAT, miBoleteria);
	}

	public void crearArchivos() {
		if (!Archivo.isCreatedFile(Persistencia.CLIENTES_RUTA)) {
			try {
				guardarClientesEnArchivo();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!Archivo.isCreatedFile(Persistencia.ADMINISTRADORES_RUTA))
			try {
				guardarAdministradoresEnArchivo();
			} catch (Exception e) {
				e.printStackTrace();
			}
		if (!Archivo.isCreatedFile(Persistencia.ESPECTACULOS_RUTA)) {
			try {
				guardarEspectaculosEnArchivo();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!Archivo.isCreatedFile(Persistencia.BOLETERIA_RUTA_DAT)) {
			try {
				serializarBoleteria();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void guardarDatos() throws IOException {
		serializarBoleteria();
		guardarAdministradoresEnArchivo();
		guardarClientesEnArchivo();
		guardarEspectaculosEnArchivo();
	}

	public void cargarDatos(String ruta) {
		Boleteria miBoleteriaAux = null;
		File archivo = new File(ruta);
		if (archivo.exists()) {
			try {
				miBoleteriaAux = (Boleteria) Persistencia.deserializarObjeto(ruta);
			} catch (Exception e) {
				e.printStackTrace();
			}
			setMiBoleteria(miBoleteriaAux);
		} else
			crearArchivos();
	}

	// -----------------Services----------------
	public static boolean elegirGuardar() {
		boolean centinela;
		Alert miAlert = new Alert(AlertType.CONFIRMATION);
		miAlert.setTitle("Guardar?");
		miAlert.setContentText("Desea guardar los datos?");
		ButtonType buttonTypeOne = new ButtonType("Si");
		ButtonType buttonTypeTwo = new ButtonType("No");
		miAlert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
		Optional<ButtonType> resultado = miAlert.showAndWait();
		centinela = resultado.get() == buttonTypeOne;
		return centinela;
	}

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
	public boolean agregarEspectaculo(String nombre, TipoEspectaculo miTipo, ArrayList<Date> fechas)
			throws EspectaculoRepetidoException {
		return miBoleteria.agregarEspectaculo(nombre, miTipo, fechas);
	}

	@Override
	public ArrayList<Espectaculo> obtenerListaEspectaculos() {
		return miBoleteria.obtenerListaEspectaculos();
	}

	@Override
	public ArrayList<TipoEspectaculo> obtenerListadoTipoEspectaculo() {
		return miBoleteria.obtenerListadoTipoEspectaculo();
	}

	public void actualizarListaEspectaculos() {
		espectaculosData.clear();
		espectaculosData.addAll(miBoleteria.obtenerListaEspectaculos());
	}

	public void eliminarEspectaculo(Espectaculo e) {
		try {
			miBoleteria.removerEspectaculo(e.getNombre());
		} catch (EspectaculoNullException e1) {
			showAlert(e1.getMessage(), "", "ERROR", AlertType.ERROR);
		}
		actualizarListaEspectaculos();
	}

	public void showAlert(String message, String headerText, String title, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setContentText(message);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.showAndWait();
	}

}