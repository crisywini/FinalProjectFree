package controller;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MenuPaneController {
	private PrincipalController ventanaPrincipal;
	@FXML
	private ImageView image;

	@FXML
	private AnchorPane pane;

	@FXML
	private RadioButton radioAdmin;

	@FXML
	private ToggleGroup grupoRadio;

	@FXML
	private RadioButton radioUser;

	@FXML
	void handleIngresarButton() {
		if (radioAdmin.isSelected()) {
			ventanaPrincipal.cargarAdminSign();
		}
		if (radioUser.isSelected()) {
			ventanaPrincipal.cargarUserSingIn();
		}
	}

	@FXML
	void initialize() {
		assert radioAdmin != null : "fx:id=\"radioAdmin\" was not injected: check your FXML file 'MenuPane.fxml'.";
		assert grupoRadio != null : "fx:id=\"grupoRadio\" was not injected: check your FXML file 'MenuPane.fxml'.";
		assert radioUser != null : "fx:id=\"radioUser\" was not injected: check your FXML file 'MenuPane.fxml'.";
		pane.setStyle("-fx-background-image: url(\"file:src/images/FondoInicio.jpg\")");
		image.setImage(new Image("file:src/images/LogoBoleteria(Final).png"));
	}

	public PrincipalController getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public void setVentanaPrincipal(PrincipalController ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}
}