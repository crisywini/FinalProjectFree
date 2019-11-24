package controller;

import javafx.fxml.FXML;
import model.Administrador;
import model.Espectaculo;

public class EstadisticosPaneController {
	private PrincipalController ventanaPrincipal;
	private Administrador miAdministrador;
	private Espectaculo miEspectaculo;

	@FXML
	void handleAtencionButton() {

	}

	@FXML
	void handleAtraccionButton() {

	}

	@FXML
	void handleAtrasButton() {

	}

	@FXML
	void handleCalidadPrecioButton() {

	}

	@FXML
	void handleLugarButton() {

	}

	@FXML
	void handleSistemaButton() {

	}

	@FXML
	void initialize() {

	}

	public PrincipalController getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public void setVentanaPrincipal(PrincipalController ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	public Administrador getMiAdministrador() {
		return miAdministrador;
	}

	public void setMiAdministrador(Administrador miAdministrador) {
		this.miAdministrador = miAdministrador;
	}

	public Espectaculo getMiEspectaculo() {
		return miEspectaculo;
	}

	public void setMiEspectaculo(Espectaculo miEspectaculo) {
		this.miEspectaculo = miEspectaculo;
	}
}
