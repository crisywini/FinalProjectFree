package controller;

import java.io.File;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import model.Administrador;
import model.Espectaculo;
import util.ApachePoi;

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
		initPieChart();
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

	public void initPieChart() {
		pieChart.getData().clear();
		ArrayList<ArrayList<String>> data = ApachePoi.readData(new File("src/util/EspectaculoRespuestasDataset.xlsx"));
		int counterVeryGood = 0;
		int counterGood = 0;
		int counterNormal = 0;
		int counterBad = 0;
		int counterVeryBad = 0;
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).get(0).trim().equalsIgnoreCase(miEspectaculo.getNombre())) {
				if (data.get(i).get(4).equalsIgnoreCase("Muy bueno"))
					counterVeryGood++;
				if (data.get(i).get(4).equalsIgnoreCase("Bueno"))
					counterGood++;
				if (data.get(i).get(4).equalsIgnoreCase("Normal"))
					counterNormal++;
				if (data.get(i).get(4).equalsIgnoreCase("Malo"))
					counterBad++;
				if (data.get(i).get(4).equalsIgnoreCase("Muy malo"))
					counterVeryBad++;
			}
		}
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Muy bueno", counterVeryGood), new PieChart.Data("Bueno", counterGood),
				new PieChart.Data("Normal", counterNormal), new PieChart.Data("Malo", counterBad),
				new PieChart.Data("Muy malo", counterVeryBad));
		pieChart.getData().addAll(pieChartData);
	}
}
