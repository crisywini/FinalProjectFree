package controller;

import exceptions.ClienteNoExistenteException;
import javafx.fxml.FXML;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Cliente;
import javafx.scene.control.Alert.AlertType;

public class UserSignInController {
	private PrincipalController ventanaPrincipal;
	@FXML
	private TextField nickNameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	void handleIngresarButton() {
		if (isInputValid()) {
			try {
				Cliente miCliente = getVentanaPrincipal().getPrincipal().obtenerCliente(nickNameField.getText());

				if (verifyPassword(miCliente)) {
					ventanaPrincipal.showAlert("Bienvenido: " + miCliente.getNombre(), "", "Bienvenido",
							AlertType.INFORMATION);
					ventanaPrincipal.cargarUserPane(miCliente);
				}
				nickNameField.setText("");
				passwordField.setText("");

			} catch (ClienteNoExistenteException e) {
				ventanaPrincipal.showAlert(e.getMessage(), "", "Error", AlertType.ERROR);
			}
		}
	}

	@FXML
	void handleVolverButton() {
		ventanaPrincipal.volverMenuPrincipal();
	}

	@FXML
	void initialize() {
		assert nickNameField != null : "fx:id=\"nickNameField\" was not injected: check your FXML file 'UserSignInPane.fxml'.";
		assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'UserSignInPane.fxml'.";

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

	public boolean verifyPassword(Cliente miCliente) {
		boolean isCorrect = true;
		String passwordClient = miCliente.getContrasenia();
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