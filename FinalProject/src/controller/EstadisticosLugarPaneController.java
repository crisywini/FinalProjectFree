package controller;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import model.Administrador;
import model.Espectaculo;

public class EstadisticosLugarPaneController {
	private PrincipalController ventanaPrincipal;
	private Administrador miAdministrador;
	private Espectaculo miEspectaculo;

	@FXML
	private ScatterChart<?, ?> scatterChart;

	@FXML
	private CategoryAxis xAxis;

	@FXML
	private NumberAxis yAxis;

	@FXML
	void handleAtrasButton() {
		ventanaPrincipal.cargarEstadisticosPane(miEspectaculo, miAdministrador);
	}

	@FXML
	void initialize() {
		assert scatterChart != null : "fx:id=\"scatterChart\" was not injected: check your FXML file 'EstadisticosLugarPane.fxml'.";
		assert xAxis != null : "fx:id=\"xAxis\" was not injected: check your FXML file 'EstadisticosLugarPane.fxml'.";
		assert yAxis != null : "fx:id=\"yAxis\" was not injected: check your FXML file 'EstadisticosLugarPane.fxml'.";

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
