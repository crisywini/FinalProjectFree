package controller;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Administrador;
import model.Cliente;
import model.Espectaculo;

public class PrincipalController {

	@FXML
	private BorderPane principalPane;
	private Main principal;
	private Stage principalStage;
	AnchorPane menuPane;
	AnchorPane userSignInPane;
	AnchorPane adminSignUpPane;
	AnchorPane adminSignPane;
	AnchorPane userSignUpPane;
	AnchorPane userPane;
	AnchorPane adminViewPane;
	AnchorPane actualizarDatosUserPane;
	AnchorPane agregarEspectaculoPane;
	AnchorPane comprarBoletasPane;
	MenuPaneController menuController;
	UserSignInController userSignInController;
	AdminSignUpController adminSignUpController;
	AdminSignController adminSignController;
	UserSignUpController userSignUpController;
	UserPaneController userPaneController;
	AdminViewController adminViewPaneController;
	ActualizarDatosUserPaneController actualizarDatosUserPaneController;
	AgregarEspectaculoController agregarEspectaculoController;
	ComprarBoletasPaneController comprarBoletasController;

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

	public void cargarMenu() {
		if (menuPane == null) {
			try {
				FXMLLoader cargador = new FXMLLoader();
				cargador.setLocation(Main.class.getResource("../view/MenuPane.fxml"));
				menuPane = (AnchorPane) cargador.load();
				menuController = cargador.getController();
				menuController.setVentanaPrincipal(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		principalPane.setCenter(menuPane);
	}

	public void cargarUserSingIn() {
		if (userSignInPane == null) {
			try {
				FXMLLoader cargador = new FXMLLoader();
				cargador.setLocation(Main.class.getResource("../view/UserSignInPane.fxml"));
				userSignInPane = (AnchorPane) cargador.load();
				userSignInController = cargador.getController();
				userSignInController.setVentanaPrincipal(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		principalPane.setCenter(userSignInPane);
	}

	public void cargarAdminSignUp() {
		if (adminSignUpPane == null) {
			try {
				FXMLLoader cargador = new FXMLLoader();
				cargador.setLocation(Main.class.getResource("../view/AdminSignUpPane.fxml"));
				adminSignUpPane = (AnchorPane) cargador.load();
				adminSignUpController = cargador.getController();
				adminSignUpController.setVentanaPrincipal(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		principalPane.setCenter(adminSignUpPane);
	}

	public void cargarAdminSign() {
		if (adminSignPane == null) {
			try {
				FXMLLoader cargador = new FXMLLoader();
				cargador.setLocation(Main.class.getResource("../view/AdminSignPane.fxml"));
				adminSignPane = (AnchorPane) cargador.load();
				adminSignController = cargador.getController();
				adminSignController.setVentanaPrincipal(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		principalPane.setCenter(adminSignPane);
	}

	public void cargarUserSignUp() {
		if (userSignUpPane == null) {
			try {
				FXMLLoader cargador = new FXMLLoader();
				cargador.setLocation(Main.class.getResource("../view/UserSignUpPane.fxml"));
				userSignUpPane = (AnchorPane) cargador.load();
				userSignUpController = cargador.getController();
				userSignUpController.setVentanaPrincipal(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		principalPane.setCenter(userSignUpPane);
	}

	public void cargarUserPane(Cliente miCliente) {
		if (userPane == null) {
			try {
				FXMLLoader cargador = new FXMLLoader();
				cargador.setLocation(Main.class.getResource("../view/UserPane.fxml"));
				userPane = (AnchorPane) cargador.load();
				userPaneController = cargador.getController();
				userPaneController.setVentanaPrincipal(this);
				userPaneController.setMiCliente(miCliente);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		principalPane.setCenter(userPane);
	}

	public void cargarAdminViewPane(Administrador miAdmin) {
		if (adminViewPane == null) {
			try {
				FXMLLoader cargador = new FXMLLoader();
				cargador.setLocation(Main.class.getResource("../view/AdminViewPane.fxml"));
				adminViewPane = (AnchorPane) cargador.load();
				adminViewPaneController = cargador.getController();
				adminViewPaneController.setMiAdmin(miAdmin);
				adminViewPaneController.setVentanaPrincipal(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		principalPane.setCenter(adminViewPane);
	}

	public void cargarActualizarDatosUserPane(Cliente miCliente) {
		if (actualizarDatosUserPane == null) {
			try {
				FXMLLoader cargador = new FXMLLoader();
				cargador.setLocation(Main.class.getResource("../view/ActualizarDatosUserPane.fxml"));
				actualizarDatosUserPane = (AnchorPane) cargador.load();
				actualizarDatosUserPaneController = cargador.getController();
				actualizarDatosUserPaneController.setVentanaPrincipal(this);
				actualizarDatosUserPaneController.setMiCliente(miCliente);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		principalPane.setCenter(actualizarDatosUserPane);
	}

	public void cargarAgregarEspectaculoPane(Administrador adm) {
		if (agregarEspectaculoPane == null) {
			try {
				FXMLLoader cargador = new FXMLLoader();
				cargador.setLocation(Main.class.getResource("../view/AgregarEspectaculoPane.fxml"));
				agregarEspectaculoPane = (AnchorPane) cargador.load();
				agregarEspectaculoController = cargador.getController();
				agregarEspectaculoController.setAdm(adm);
				agregarEspectaculoController.setVentanaPrincipal(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		principalPane.setCenter(agregarEspectaculoPane);
	}

	public void cargarComprarBoletasPane(Espectaculo miEspectaculo, Cliente miCliente) {
		if (comprarBoletasPane == null) {
			try {
				FXMLLoader cargador = new FXMLLoader();
				cargador.setLocation(Main.class.getResource("../view/ComprarBoletasPane.fxml"));
				comprarBoletasPane = (AnchorPane) cargador.load();
				comprarBoletasController = cargador.getController();
				comprarBoletasController.setVentanaPrincipal(this);
				comprarBoletasController.setMiCliente(miCliente);
				comprarBoletasController.setMiEspectaculo(miEspectaculo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		principalPane.setCenter(comprarBoletasPane);
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
		if (elegirGuardar()) {
			try {
				getPrincipal().serializarBoleteria();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.exit(0);
	}

	@FXML
	void handleMenuUser() {
		cargarUserSignUp();
	}

	public void showAlert(String message, String headerText, String title, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setContentText(message);
		alert.setTitle(title);
		alert.initOwner(getPrincipalStage());
		alert.setHeaderText(headerText);
		alert.showAndWait();
	}

	public boolean elegirGuardar() {
		boolean centinela;
		Alert miAlert = new Alert(AlertType.CONFIRMATION);
		miAlert.setTitle("Guardar?");
		miAlert.setContentText("Desea guardar los datos?");
		miAlert.initOwner(getPrincipalStage());
		ButtonType buttonTypeOne = new ButtonType("Si");
		ButtonType buttonTypeTwo = new ButtonType("No");
		miAlert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
		Optional<ButtonType> resultado = miAlert.showAndWait();
		centinela = resultado.get() == buttonTypeOne;
		return centinela;
	}

	public void volverMenuPrincipal() {
		cargarMenu();
	}

	public Stage getPrincipalStage() {
		return principalStage;
	}

	public void setPrincipalStage(Stage principalStage) {
		this.principalStage = principalStage;
	}
	

}