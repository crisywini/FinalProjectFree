package controller;

import java.awt.peer.PanelPeer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class PrincipalController {

	@FXML
	private BorderPane principalPane;
	private Main principal;
	AnchorPane menuPane;
	MenuPaneController menuController;
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

	public BorderPane getPrincipalPane() {
		return principalPane;
	}

	public void setPrincipalPane(BorderPane principalPane) {
		this.principalPane = principalPane;
	}
	
}
