package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PrincipalController {

	@FXML
	private BorderPane principalPane;
	private Main principal;
	private Stage principalStage;
	AnchorPane menuPane;
	AnchorPane userSignInPane;
	AnchorPane adminSignUpPane;
	MenuPaneController menuController;
	UserSignInController userSignInController;
	AdminSignUpController adminSignUpController;
	
	@FXML
	void initialize() {
		cargarMenu();
	}

	public Main getPrincipal() {
		return principal;
	}

	public void setPrincipal(Main principal) {
		this.principal = principal;
	}
	public void cargarMenu()
	{
		if(menuPane==null)
		{
			try {
				FXMLLoader cargador = new FXMLLoader();
				cargador.setLocation(Main.class.getResource("../view/MenuPane.fxml"));
				menuPane = (AnchorPane)cargador.load();
				menuController = cargador.getController();
				menuController.setVentanaPrincipal(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		principalPane.setCenter(menuPane);
	}
	public void cargarUserSingIn()
	{
		if(userSignInPane==null)
		{
			try {
				FXMLLoader cargador = new FXMLLoader();
				cargador.setLocation(Main.class.getResource("../view/UserSignInPane.fxml"));
				userSignInPane = (AnchorPane)cargador.load();
				userSignInController = cargador.getController();
				userSignInController.setVentanaPrincipal(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		principalPane.setCenter(userSignInPane);
	}
	public void cargarAdminSignUp()
	{
		if(adminSignUpPane==null)
		{
			try {
				FXMLLoader cargador = new FXMLLoader();
				cargador.setLocation(Main.class.getResource("../view/AdminSignUpPane.fxml"));
				adminSignUpPane = (AnchorPane)cargador.load();
				adminSignUpController = cargador.getController();
				adminSignUpController.setVentanaPrincipal(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		principalPane.setCenter(adminSignUpPane);
	}

	public BorderPane getPrincipalPane() {
		return principalPane;
	}

	public void setPrincipalPane(BorderPane principalPane) {
		this.principalPane = principalPane;
	}

    @FXML
    void handleMenuAdmin() {
    	cargarAdminSignUp();
    }

    @FXML
    void handleMenuSaveData() {

    }

    @FXML
    void handleMenuUser() {

    }
    public void showAlert(String message, String headerText, String title, AlertType alertType)
    {
    	Alert alert = new Alert(alertType);
    	alert.setContentText(message);
    	alert.setTitle(title);
    	alert.initOwner(getPrincipalStage());
    	alert.setHeaderText(headerText);
    	alert.showAndWait();
    }
    public void volverMenuPrincipal()
    {
    	cargarMenu();
    }
	public Stage getPrincipalStage() {
		return principalStage;
	}

	public void setPrincipalStage(Stage principalStage) {
		this.principalStage = principalStage;
	}
	
}