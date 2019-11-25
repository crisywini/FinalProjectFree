package controller;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import model.Administrador;
import model.Espectaculo;

public class AtraccionPaneController {
	private PrincipalController ventanaPrincipal;
	private Administrador miAdministrador;
	private Espectaculo miEspectaculo;

	@FXML
	private PieChart pieChart;

	@FXML
	void handleAtrásButton() {
		ventanaPrincipal.cargarEstadisticosPane(miEspectaculo, miAdministrador);
	}

	@FXML
	void initialize() {
		assert pieChart != null : "fx:id=\"pieChart\" was not injected: check your FXML file 'AtraccionPane.fxml'.";
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
