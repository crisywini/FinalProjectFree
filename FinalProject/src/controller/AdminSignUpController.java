package controller;

import exceptions.AdministradorNoExistenteException;
import exceptions.AdministradorRepetidoException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Administrador;
import model.Genero;

public class AdminSignUpController {

	private PrincipalController ventanaPrincipal;
	private ObservableList<Genero> misGeneros;

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
	void handleRegistrarseButton() {
		if (isInputValid()) {
			try {
				getVentanaPrincipal().getPrincipal().agregarAdministrador(nombreField.getText(),
						apellidoField.getText(), idField.getText(),
						generoComboBox.getSelectionModel().getSelectedItem(), correoField.getText(),
						passwordField.getText());
				Administrador miAdmin = getVentanaPrincipal().getPrincipal().obtenerAdministrador(idField.getText());
				ventanaPrincipal.showAlert(
						"Administrador: " + miAdmin.getNombre() + " " + miAdmin.getApellido() + " agregado.", "",
						"Informacion", AlertType.INFORMATION);
				nombreField.setText("");
				apellidoField.setText("");
				idField.setText("");
				correoField.setText("");
				passwordField.setText("");
				generoComboBox.setValue(null);
			} catch (AdministradorRepetidoException e) {
				ventanaPrincipal.showAlert(e.getMessage(), "Error", "ERROR!", AlertType.ERROR);
			} catch (AdministradorNoExistenteException e) {
				ventanaPrincipal.showAlert(e.getMessage(), "Error", "ERROR!", AlertType.ERROR);
			}
		}
	}

	@FXML
	void handleVolverButton() {
		getVentanaPrincipal().volverMenuPrincipal();
	}

	@FXML
	void initialize() {
		assert nombreField != null : "fx:id=\"nombreField\" was not injected: check your FXML file 'AdminSignUpPane.fxml'.";
		assert apellidoField != null : "fx:id=\"apellidoField\" was not injected: check your FXML file 'AdminSignUpPane.fxml'.";
		assert idField != null : "fx:id=\"idField\" was not injected: check your FXML file 'AdminSignUpPane.fxml'.";
		assert correoField != null : "fx:id=\"correoField\" was not injected: check your FXML file 'AdminSignUpPane.fxml'.";
		assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'AdminSignUpPane.fxml'.";
		assert generoComboBox != null : "fx:id=\"generoComboBox\" was not injected: check your FXML file 'AdminSignUpPane.fxml'.";

	}

	public PrincipalController getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public void setVentanaPrincipal(PrincipalController ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
		misGeneros = FXCollections.observableArrayList(ventanaPrincipal.getPrincipal().getMisGeneros());
		generoComboBox.setItems(misGeneros);
	}

	public ObservableList<Genero> getMisGeneros() {
		return misGeneros;
	}

	public void setMisGeneros(ObservableList<Genero> misGeneros) {
		this.misGeneros = misGeneros;
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
		if (passwordField.getText() == null || passwordField.getText().length() == 0)
			errorMessage += "Debe ingresar una contrasenia\n";
		if (errorMessage.length() == 0)
			isValid = true;
		else
			getVentanaPrincipal().showAlert(errorMessage, "", "Cuidado", AlertType.WARNING);
		return isValid;
	}
}