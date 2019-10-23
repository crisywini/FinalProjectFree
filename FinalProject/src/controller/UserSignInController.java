package controller;


import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class UserSignInController {
	private PrincipalController ventanaPrincipal;
    @FXML
    private TextField nickNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void handleIngresarButton() {
    	if(isInputValid())
    	{
    		//Verificar datos de usuario 
    	}
    }

    @FXML
    void initialize() {
        assert nickNameField != null : "fx:id=\"nickNameField\" was not injected: check your FXML file 'UserSignInPane.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'UserSignInPane.fxml'.";

    }
    public boolean isInputValid()
    {
    	boolean isValid = false;
    	String errorMessage = "";
    	if(nickNameField.getText().length()==0||nickNameField.getText()==null)
    		errorMessage +="Debe ingresar el nick name\n";
    	if(passwordField.getText().length()==0||passwordField.getText()==null)
    		errorMessage += "Debe ingresar la contrasenia\n";
    	if(errorMessage.length()==0)
    		isValid = true;
    	else
    		getVentanaPrincipal().showAlert(errorMessage, "", "Error", AlertType.WARNING);
    	return isValid;
    }

	public PrincipalController getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public void setVentanaPrincipal(PrincipalController ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}
}