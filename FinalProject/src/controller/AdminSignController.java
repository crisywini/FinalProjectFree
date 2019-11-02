package controller;

import exceptions.AdministradorNoExistenteException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Administrador;
import model.Cliente;

public class AdminSignController {

	private PrincipalController ventanaPrincipal;
	@FXML
	private TextField nickNameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	void handleIngresarButton() {
		if (isInputValid()) {
			try {
				Administrador miAdmin = getVentanaPrincipal().getPrincipal()
						.obtenerAdministrador(nickNameField.getText());
				if(verifyPassword(miAdmin))
				{
					ventanaPrincipal.showAlert("Bienvenido: " + miAdmin.getNombre(), "", "Bienvenido",
							AlertType.INFORMATION);
					ventanaPrincipal.cargarAdminViewPane(miAdmin);
				}
				nickNameField.setText("");
				passwordField.setText("");
				
				
			} catch (AdministradorNoExistenteException e) {
				ventanaPrincipal.showAlert(e.getMessage(), "", "Error", AlertType.ERROR);
			}
		}
	}

	@FXML
	void initialize() {
		assert nickNameField != null : "fx:id=\"nickNameField\" was not injected: check your FXML file 'AdminSignPane.fxml'.";
		assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'AdminSignPane.fxml'.";

	}

	@FXML
	void handleVolverButton() {
		ventanaPrincipal.volverMenuPrincipal();
	}

	public boolean isInputValid() {
		boolean isValid = false;
		String errorMessage = "";
		if (nickNameField.getText().length() == 0 || nickNameField.getText() == null)
			errorMessage += "Debe ingresar el nick name\n";
		if (passwordField.getText().length() == 0 || passwordField.getText() == null)
			errorMessage += "Debe ingresar la contrasenia\n";
		if (errorMessage.length() == 0)
			isValid = true;
		else
			getVentanaPrincipal().showAlert(errorMessage, "", "Error", AlertType.WARNING);
		return isValid;
	}
	
	public boolean verifyPassword(Administrador miAdmin) {
		boolean isCorrect = true;
		String passwordClient = miAdmin.getContrasenia();
		String passwordTyped = passwordField.getText().trim();
		if (passwordClient.length() == passwordTyped.length()) {
			for (int i = 0; i < passwordClient.length() && isCorrect; i++) {
				if (passwordClient.charAt(i) != passwordTyped.charAt(i))
					isCorrect = false;
			}
		} else
			isCorrect = false;
		if (!isCorrect)
			getVentanaPrincipal().showAlert("La contraseña no es correcta!", "", "ERROR", AlertType.ERROR);
		return isCorrect;
	}

	public PrincipalController getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public void setVentanaPrincipal(PrincipalController ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}
}
