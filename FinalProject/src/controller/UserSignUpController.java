package controller;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.ClienteNoExistenteException;

import exceptions.ClienteRepetidoException;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.*;

public class UserSignUpController {

	private PrincipalController ventanaPrincipal;
	private ObservableList<Genero> misGeneros;
	private ObservableList<EstadoCivil> misEstados;
	private ObservableList<EstratoSocioeconomico> misEstratos;
	private ObservableList<NivelDeEstudio> misNiveles;
	private boolean isOnRegistrarUsuariosPane;
	private Cliente miCliente;
	private Espectaculo miEspectaculo;
	private Seccion miSeccion;
	private HashMap<String, Boleta> misBoletas;

	@FXML
	private TextField nombreField;

	@FXML
	private TextField apellidoField;

	@FXML
	private TextField idField;

	@FXML
	private TextField correoField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private ComboBox<Genero> generoComboBox;

	@FXML
	private DatePicker datePicker;

	@FXML
	private TextField direccionField;

	@FXML
	private TextField cuentaField;

	@FXML
	private TextField ciudadDeResidenciaField;

	@FXML
	private ComboBox<EstratoSocioeconomico> estratoComboBox;

	@FXML
	private ComboBox<EstadoCivil> estadoCivilComboBox;

	@FXML
	private ComboBox<NivelDeEstudio> nivelDeEstudioComboBox;

	@FXML
	void handleAgregarButton() {
		if (isInputValid()) {
			Cuenta nuevaCuenta = new Cuenta(0, cuentaField.getText());
			Date fechaDeNacimiento = new Date(datePicker.getValue().getDayOfMonth(),
					datePicker.getValue().getMonthValue(), datePicker.getValue().getYear());
			Cliente miClienteNuevo = null;
			Boleta miBoletaNueva = new Boleta();
			try {
				ventanaPrincipal.getPrincipal().agregarCliente(nombreField.getText(), apellidoField.getText(),
						idField.getText(), generoComboBox.getSelectionModel().getSelectedItem(),
						direccionField.getText(), correoField.getText(), nuevaCuenta, miBoletaNueva, fechaDeNacimiento,
						ciudadDeResidenciaField.getText(), estratoComboBox.getSelectionModel().getSelectedItem(),
						estadoCivilComboBox.getSelectionModel().getSelectedItem(),
						nivelDeEstudioComboBox.getSelectionModel().getSelectedItem(), passwordField.getText());
				miClienteNuevo = ventanaPrincipal.getPrincipal().obtenerCliente(idField.getText().trim());
				ventanaPrincipal.showAlert("El cliente: " + miClienteNuevo.getNombre() + " "
						+ miClienteNuevo.getApellido() + " ha sido agregado.", "", "Informacion",
						AlertType.INFORMATION);
				nombreField.setText("");
				apellidoField.setText("");
				idField.setText("");
				datePicker.setValue(null);
				generoComboBox.setValue(null);
				direccionField.setText("");
				correoField.setText("");
				cuentaField.setText("");
				estratoComboBox.setValue(null);
				estadoCivilComboBox.setValue(null);
				nivelDeEstudioComboBox.setValue(null);
				passwordField.setText("");
				ciudadDeResidenciaField.setText("");
			} catch (ClienteRepetidoException e) {
				ventanaPrincipal.showAlert(e.getMessage(), "", "ERROR", AlertType.ERROR);
			} catch (ClienteNoExistenteException e) {
				ventanaPrincipal.showAlert(e.getMessage(), "", "ERROR", AlertType.ERROR);
			}
		}
	}

	@FXML
	void handleVolverButton() {
		if (isOnRegistrarUsuariosPane)
			getVentanaPrincipal().cargarRegistrarUsuariosPane(miCliente, miSeccion, miEspectaculo, misBoletas);
		else
			getVentanaPrincipal().volverMenuPrincipal();
		nombreField.setText("");
		apellidoField.setText("");
		idField.setText("");
		datePicker.setValue(null);
		generoComboBox.setValue(null);
		direccionField.setText("");
		correoField.setText("");
		cuentaField.setText("");
		estratoComboBox.setValue(null);
		estadoCivilComboBox.setValue(null);
		nivelDeEstudioComboBox.setValue(null);
		passwordField.setText("");
		ciudadDeResidenciaField.setText("");
		Main.clienteData.clear();
		Main.clienteData.addAll(ventanaPrincipal.getPrincipal().getMiBoleteria().obtenerListadoClientes());
	}

	@FXML
	void initialize() {
		assert nombreField != null : "fx:id=\"nombreField\" was not injected: check your FXML file 'UserSignUpPane.fxml'.";
		assert apellidoField != null : "fx:id=\"apellidoField\" was not injected: check your FXML file 'UserSignUpPane.fxml'.";
		assert idField != null : "fx:id=\"idField\" was not injected: check your FXML file 'UserSignUpPane.fxml'.";
		assert correoField != null : "fx:id=\"correoField\" was not injected: check your FXML file 'UserSignUpPane.fxml'.";
		assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'UserSignUpPane.fxml'.";
		assert generoComboBox != null : "fx:id=\"generoComboBox\" was not injected: check your FXML file 'UserSignUpPane.fxml'.";
		assert datePicker != null : "fx:id=\"datePicker\" was not injected: check your FXML file 'UserSignUpPane.fxml'.";
	}

	/**
	 * Metodo que permite verificar si hay informacion en los campos
	 * 
	 * @return verdadero cuando se ingresaron todos los datos necesarios
	 */
	public boolean isInputValid() {
		boolean isValid = false;
		String errorMessage = "";
		if (nombreField.getText() == null || nombreField.getText().length() == 0)
			errorMessage += "Debe ingresar el nombre\n";
		if (apellidoField.getText() == null || apellidoField.getText().length() == 0)
			errorMessage += "Debe ingresar el apellido\n";
		if (idField.getText() == null || idField.getText().length() == 0)
			errorMessage += "Debe ingresar el id\n";
		if (generoComboBox.getSelectionModel().isEmpty())
			errorMessage += "Debe seleccionar un genero\n";
		if (correoField.getText() == null || correoField.getText().length() == 0)
			errorMessage += "Debe ingresar el correo\n";
		else
		{
			Pattern pattern = Pattern
	                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	        String email = correoField.getText();
	        Matcher mather = pattern.matcher(email);

	        if (!mather.find())
	        	errorMessage += "El correo: "+email+" no es valido.\n";
		}
		if (passwordField.getText() == null || passwordField.getText().length() == 0)
			errorMessage += "Debe ingresar una contrasenia\n";
		if (datePicker.getValue() == null)
			errorMessage += "Debe ingresar la fecha de nacimiento\n";
		if (estadoCivilComboBox.getSelectionModel().isEmpty())
			errorMessage += "Debe ingresar el estado civil\n";
		if (nivelDeEstudioComboBox.getSelectionModel().isEmpty())
			errorMessage += "Debe ingresar el nivel de estudio\n";
		if (estratoComboBox.getSelectionModel().isEmpty())
			errorMessage += "Debe seleccionar el estrato\n";
		if (direccionField.getText() == null || direccionField.getText().length() == 0)
			errorMessage += "Debe ingresar la direccion\n";
		if (ciudadDeResidenciaField.getText() == null || ciudadDeResidenciaField.getText().length() == 0)
			errorMessage += "Debe ingresar la ciudad de residencia\n";
		if (errorMessage.length() == 0)
			isValid = true;
		else
			getVentanaPrincipal().showAlert(errorMessage, "", "Cuidado", AlertType.WARNING);
		return isValid;
	}

	public PrincipalController getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public void setVentanaPrincipal(PrincipalController ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
		misGeneros = FXCollections.observableArrayList(ventanaPrincipal.getPrincipal().getMisGeneros());
		generoComboBox.setItems(misGeneros);
		misEstados = FXCollections.observableArrayList(ventanaPrincipal.getPrincipal().getEstadosCiviles());
		estadoCivilComboBox.setItems(misEstados);
		misEstratos = FXCollections.observableArrayList(ventanaPrincipal.getPrincipal().getEstratos());
		estratoComboBox.setItems(misEstratos);
		misNiveles = FXCollections.observableArrayList(ventanaPrincipal.getPrincipal().getMisEstudios());
		nivelDeEstudioComboBox.setItems(misNiveles);
	}

	public ObservableList<EstadoCivil> getMisEstados() {
		return misEstados;
	}

	public void setMisEstados(ObservableList<EstadoCivil> misEstados) {
		this.misEstados = misEstados;
	}

	public ObservableList<EstratoSocioeconomico> getMisEstratos() {
		return misEstratos;
	}

	public void setMisEstratos(ObservableList<EstratoSocioeconomico> misEstratos) {
		this.misEstratos = misEstratos;
	}

	public ObservableList<NivelDeEstudio> getMisNiveles() {
		return misNiveles;
	}

	public void setMisNiveles(ObservableList<NivelDeEstudio> misNiveles) {
		this.misNiveles = misNiveles;
	}

	public boolean isOnRegistrarUsuariosPane() {
		return isOnRegistrarUsuariosPane;
	}

	public void setOnRegistrarUsuariosPane(boolean isOnRegistrarUsuariosPane) {
		this.isOnRegistrarUsuariosPane = isOnRegistrarUsuariosPane;
	}

	public Cliente getMiCliente() {
		return miCliente;
	}

	public void setMiCliente(Cliente miCliente) {
		this.miCliente = miCliente;
	}

	public Espectaculo getMiEspectaculo() {
		return miEspectaculo;
	}

	public void setMiEspectaculo(Espectaculo miEspectaculo) {
		this.miEspectaculo = miEspectaculo;
	}

	public Seccion getMiSeccion() {
		return miSeccion;
	}

	public void setMiSeccion(Seccion miSeccion) {
		this.miSeccion = miSeccion;
	}

	public HashMap<String, Boleta> getMisBoletas() {
		return misBoletas;
	}

	public void setMisBoletas(HashMap<String, Boleta> misBoletas) {
		this.misBoletas = misBoletas;
	}

}
