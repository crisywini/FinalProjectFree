package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class MenuPaneController {
	private PrincipalController ventanaPrincipal;
 
    @FXML
    private RadioButton radioAdmin;

    @FXML
    private ToggleGroup grupoRadio;

    @FXML
    private RadioButton radioUser;

    @FXML
    void handleRadios(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert radioAdmin != null : "fx:id=\"radioAdmin\" was not injected: check your FXML file 'MenuPane.fxml'.";
        assert grupoRadio != null : "fx:id=\"grupoRadio\" was not injected: check your FXML file 'MenuPane.fxml'.";
        assert radioUser != null : "fx:id=\"radioUser\" was not injected: check your FXML file 'MenuPane.fxml'.";
    }

	public PrincipalController getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public void setVentanaPrincipal(PrincipalController ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}
}