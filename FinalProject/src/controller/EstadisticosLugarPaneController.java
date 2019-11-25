package controller;

import java.io.File;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import model.Administrador;
import model.Espectaculo;
import util.ApachePoi;

public class EstadisticosLugarPaneController {
	private PrincipalController ventanaPrincipal;
	private Administrador miAdministrador;
	private Espectaculo miEspectaculo;

	@FXML
	private ScatterChart<String, Number> scatterChart;

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
		initScatterChart();
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

	public void initScatterChart() {
		scatterChart.getData().clear();
		XYChart.Series people = new XYChart.Series();
		people.setName("Personas");
		ArrayList<ArrayList<String>> data = ApachePoi.readData(new File("src/util/EspectaculoRespuestasDataset.xlsx"));
		int counterVeryGood = 0;
		int counterGood = 0;
		int counterNormal = 0;
		int counterBad = 0;
		int counterVeryBad = 0;
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).get(0).trim().equalsIgnoreCase(miEspectaculo.getNombre())) {
				if (data.get(i).get(5).equalsIgnoreCase("Muy bueno"))
					counterVeryGood++;
				if (data.get(i).get(5).equalsIgnoreCase("Bueno"))
					counterGood++;
				if (data.get(i).get(5).equalsIgnoreCase("Normal"))
					counterNormal++;
				if (data.get(i).get(5).equalsIgnoreCase("Malo"))
					counterBad++;
				if (data.get(i).get(5).equalsIgnoreCase("Muy malo"))
					counterVeryBad++;
			}
		}
		people.getData().add(new XYChart.Data("Muy bueno", counterVeryGood));
		people.getData().add(new XYChart.Data("Bueno", counterGood));
		people.getData().add(new XYChart.Data("Normal", counterNormal));
		people.getData().add(new XYChart.Data("Malo", counterBad));
		people.getData().add(new XYChart.Data("Muy malo", counterVeryBad));
		scatterChart.getData().add(people);
	}
}
