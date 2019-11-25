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
		ventanaPrincipal.cargarAtencionPane(miEspectaculo, miAdministrador);
	}

	@FXML
	void handleAtraccionButton() {
		ventanaPrincipal.cargarAtraccion(miAdministrador, miEspectaculo);
	}

	@FXML
	void handleAtrasButton() {
		ventanaPrincipal.cargarAdminViewPane(miAdministrador);
	}

	@FXML
	void handleCalidadPrecioButton() {
		ventanaPrincipal.cargarCalidadPrecioPane(miAdministrador, miEspectaculo);
	}

	@FXML
	void handleLugarButton() {
		ventanaPrincipal.cargarLugarPane(miAdministrador, miEspectaculo);
	}

	@FXML
	void handleSistemaButton() {
		ventanaPrincipal.cargarEstadisticosSistemaPane(miAdministrador, miEspectaculo);
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