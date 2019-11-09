package controller;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Cliente;
import model.EstadoCivil;
import model.EstratoSocioeconomico;
import model.Genero;
import model.NivelDeEstudio;

public class ActualizarDatosUserPaneController {

	private PrincipalController ventanaPrincipal;
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
	private TextField cupoField;
	private Cliente miCliente;

	@FXML
	void initialize() {

	}

	@FXML
	void handleActualizarButton() {
		if (isInputValid()) {
			miCliente.setMiGenero(generoComboBox.getSelectionModel().getSelectedItem());
			miCliente.setDireccion(direccionField.getText());
			miCliente.getMiCuentaAsociada().setDineroTotal(Double.parseDouble(cupoField.getText()));
			miCliente.setCiudadDeResidencia(ciudadDeResidenciaField.getText());
			miCliente.setMiEstrato(estratoComboBox.getSelectionModel().getSelectedItem());
			miCliente.setMiEstadoCivil(estadoCivilComboBox.getSelectionModel().getSelectedItem());
			miCliente.setMiNivelDeEstudio(nivelDeEstudioComboBox.getSelectionModel().getSelectedItem());
			miCliente.setEmail(correoField.getText());
			miCliente.setContrasenia(passwordField.getText());
			ventanaPrincipal.showAlert("Los datos del cliente: " + miCliente.getNombre() + " " + miCliente.getApellido()
					+ " han sido actualizados", "", "INFORMACION", AlertType.INFORMATION);
			ventanaPrincipal.cargarUserPane(miCliente);
		}
	}

	@FXML
	void handleVolverButton() {
		ventanaPrincipal.cargarUserPane(miCliente);
	}

	public PrincipalController getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public void setVentanaPrincipal(PrincipalController ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
		initCombos();
	}

	public Cliente getMiCliente() {
		return miCliente;
	}

	public void setMiCliente(Cliente miCliente) {
		this.miCliente = miCliente;
		nombreField.setText(miCliente.getNombre());
		apellidoField.setText(miCliente.getApellido());
		idField.setText(miCliente.getId());
		correoField.setText(miCliente.getEmail());
		LocalDate fecha = LocalDate.of(miCliente.getMiFechaDeNacimiento().getYear(),
				miCliente.getMiFechaDeNacimiento().getMonth(), miCliente.getMiFechaDeNacimiento().getDay());
		datePicker.setValue(fecha);
		generoComboBox.setValue(miCliente.getMiGenero());
		direccionField.setText(miCliente.getDireccion());
		cupoField.setText("$" + miCliente.getMiCuentaAsociada().getDineroTotal() + "");
		ciudadDeResidenciaField.setText(miCliente.getCiudadDeResidencia());
		estratoComboBox.setValue(miCliente.getMiEstrato());
		estadoCivilComboBox.setValue(miCliente.getMiEstadoCivil());
		nivelDeEstudioComboBox.setValue(miCliente.getMiNivelDeEstudio());
	}

	public void initCombos() {
		ObservableList<Genero> generosData = FXCollections
				.observableArrayList(ventanaPrincipal.getPrincipal().getMisGeneros());
		ObservableList<EstratoSocioeconomico> estratoData = FXCollections
				.observableArrayList(ventanaPrincipal.getPrincipal().getEstratos());
		ObservableList<EstadoCivil> estadoCivilData = FXCollections
				.observableArrayList(ventanaPrincipal.getPrincipal().getEstadosCiviles());
		ObservableList<NivelDeEstudio> nivelDeEstudioData = FXCollections
				.observableArrayList(ventanaPrincipal.getPrincipal().getMisEstudios());
		generoComboBox.setItems(generosData);
		estratoComboBox.setItems(estratoData);
		estadoCivilComboBox.setItems(estadoCivilData);
		nivelDeEstudioComboBox.setItems(nivelDeEstudioData);
	}

	public boolean isInputValid() {
		boolean isValid = false;
		String errorMessage = "";
		if (generoComboBox.getSelectionModel().isEmpty())
			errorMessage += "Debe seleccionar el genero\n";
		if (direccionField.getText() == null || direccionField.getText().length() == 0)
			errorMessage += "Debe ingresar la direccion\n";
		if (cupoField.getText() == null || cupoField.getText().length() == 0)
			errorMessage += "Debe ingresar el cupo de la tarjeta de credito para realizar cualquier compra\n";
		else
			try {
				Double.parseDouble(cupoField.getText());
			} catch (Exception e) {
				errorMessage += "El cupo de la tarjeta de credito debe ser un numero real\n";
			}
		if (ciudadDeResidenciaField.getText() == null || ciudadDeResidenciaField.getText().length() == 0)
			errorMessage += "Debe ingresar la ciudad de residencia\n";
		if (estratoComboBox.getSelectionModel().isEmpty())
			errorMessage += "Debe ingresar el estrato socioeconomico\n";
		if (estadoCivilComboBox.getSelectionModel().isEmpty())
			errorMessage += "Debe ingresar el estado civil\n";
		if (nivelDeEstudioComboBox.getSelectionModel().isEmpty())
			errorMessage += "Debe ingresar el nivel de estudio\n";
		if (correoField.getText() == null || correoField.getText().length() == 0)
			errorMessage += "Debe ingresar el correo electronico\n";
		if (passwordField.getText() == null || passwordField.getText().length() == 0)
			errorMessage += "Debe ingresar la contraseña\n";
		if (errorMessage.length() == 0)
			isValid = true;
		else
			ventanaPrincipal.showAlert(errorMessage, "", "ERROR", AlertType.ERROR);
		return isValid;
	}

}
