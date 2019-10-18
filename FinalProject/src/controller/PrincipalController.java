package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class PrincipalController {

	@FXML
	private BorderPane principalPane;
	private Main principal;

	@FXML
	void initialize() {

	}

	public Main getPrincipal() {
		return principal;
	}

	public void setPrincipal(Main principal) {
		this.principal = principal;
	}
}
