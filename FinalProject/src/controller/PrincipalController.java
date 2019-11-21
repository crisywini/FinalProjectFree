package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import exceptions.EspectaculoNullException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Administrador;
import model.Boleta;
import model.Cliente;
import model.Espectaculo;
import model.Seccion;

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
	AnchorPane escenarioPane;
	AnchorPane sillasPane;
	AnchorPane registrarUsuariosPane;
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
	EscenarioPaneController escenarioController;
	SillasPaneController sillasController;
	RegistrarUsuariosPaneController registrarUsuariosController;
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

	public void cargarUserSignUp(boolean isOnRegistrarUsuariosPane, Cliente miCliente, Espectaculo miEspectaculo,
			Seccion miSeccion, HashMap<String, Boleta> misBoletas) {
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
		userSignUpController.setOnRegistrarUsuariosPane(isOnRegistrarUsuariosPane);
		if (isOnRegistrarUsuariosPane) {
			userSignUpController.setMiCliente(miCliente);
			userSignUpController.setMiEspectaculo(miEspectaculo);
			userSignUpController.setMisBoletas(misBoletas);
			userSignUpController.setMiSeccion(miSeccion);
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

	public void cargarAgregarEspectaculoPane(Administrador adm, AdminViewController adminView) {
		if (agregarEspectaculoPane == null) {
			try {
				FXMLLoader cargador = new FXMLLoader();
				cargador.setLocation(Main.class.getResource("../view/AgregarEspectaculoPane.fxml"));
				agregarEspectaculoPane = (AnchorPane) cargador.load();
				agregarEspectaculoController = cargador.getController();
				agregarEspectaculoController.setAdm(adm);
				agregarEspectaculoController.setVentanaPrincipal(this);
				agregarEspectaculoController.setAdminView(adminView);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		principalPane.setCenter(agregarEspectaculoPane);
	}

	public void cargarEscenarioPane(Espectaculo miEspectaculo, Cliente miCliente) {
		if (escenarioPane == null) {
			try {
				FXMLLoader cargador = new FXMLLoader();
				cargador.setLocation(Main.class.getResource("../view/EscenarioPane.fxml"));
				escenarioPane = (AnchorPane) cargador.load();
				escenarioController = cargador.getController();
				escenarioController.setVentanaPrincipal(this);
				escenarioController.setMiCliente(miCliente);
				escenarioController.setMiEspectaculo(miEspectaculo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		principalPane.setCenter(escenarioPane);
	}

	public void cargarSillasPane(Cliente miCliente, Seccion miSeccion, Espectaculo miEspectaculo) {
		if (sillasPane == null) {
			try {
				FXMLLoader cargador = new FXMLLoader();
				cargador.setLocation(Main.class.getResource("../view/SillasPane.fxml"));
				sillasPane = (AnchorPane) cargador.load();
				sillasController = cargador.getController();
				sillasController.setMiSeccion(miSeccion);
				sillasController.setMiCliente(miCliente);
				sillasController.setMiEspectaculo(miEspectaculo);
				sillasController.setVentanaPrincipal(this);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		sillasController.getSillasVBox().getChildren().remove(0);
		sillasController.setMiSeccion(miSeccion);
		principalPane.setCenter(sillasPane);
	}

	public void cargarRegistrarUsuariosPane(Cliente miCliente, Seccion miSeccion, Espectaculo miEspectaculo,
			HashMap<String, Boleta> misBoletas) {
		if (registrarUsuariosPane == null) {
			try {
				FXMLLoader cargador = new FXMLLoader();
				cargador.setLocation(Main.class.getResource("../view/RegistrarUsuariosPane.fxml"));
				registrarUsuariosPane = (AnchorPane) cargador.load();
				registrarUsuariosController = cargador.getController();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		registrarUsuariosController.setMiCliente(miCliente);
		registrarUsuariosController.setMiEspectaculo(miEspectaculo);
		registrarUsuariosController.setMisBoletas(misBoletas);
		registrarUsuariosController.setMiSeccion(miSeccion);
		registrarUsuariosController.setVentanaPrincipal(this);
		principalPane.setCenter(registrarUsuariosPane);
	}

	public void cargarComprarBoletasPane(Cliente miCliente, Seccion miSeccion, Espectaculo miEspectaculo,
			HashMap<String, Cliente> misClientes, HashMap<String, Boleta> misBoletas) {
		if (comprarBoletasPane == null) {
			try {
				FXMLLoader cargador = new FXMLLoader();
				cargador.setLocation(Main.class.getResource("../view/ComprarBoletasPane.fxml"));
				comprarBoletasPane = (AnchorPane) cargador.load();
				comprarBoletasController = cargador.getController();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		comprarBoletasController.setMiCliente(miCliente);
		comprarBoletasController.setMiEspectaculo(miEspectaculo);
		comprarBoletasController.setMisBoletas(misBoletas);
		comprarBoletasController.setMisClientes(misClientes);
		comprarBoletasController.setMiSeccion(miSeccion);
		comprarBoletasController.setVentanaPrincipal(this);
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
				getPrincipal().guardarDatos();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.exit(0);
	}

	@FXML
	void handleMenuUser() {
		cargarUserSignUp(false, null, null, null, null);
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

	public boolean elegir(String mensaje) {
		boolean centinela;
		Alert miAlert = new Alert(AlertType.CONFIRMATION);
		miAlert.setContentText(mensaje);
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
	
	public void actualizarListaEspectaculos()
	{
		principal.actualizarListaEspectaculos();
	}
	
	public void eliminarEspectaculo(Espectaculo e) throws EspectaculoNullException
	{
		principal.eliminarEspectaculo(e);
	}

}