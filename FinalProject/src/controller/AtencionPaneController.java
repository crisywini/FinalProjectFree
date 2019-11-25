package controller;

import java.io.File;

import java.util.ArrayList;

import exceptions.ClienteNoExistenteException;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import model.Administrador;
import model.Cliente;
import model.Espectaculo;
import util.ApachePoi;

public class AtencionPaneController {
	private PrincipalController ventanaPrincipal;
	private Administrador miAdmin;
	private Espectaculo miEspectaculo;

	@FXML
	private BarChart<String, Number> barChart;

	@FXML
	private CategoryAxis xAxis;

	@FXML
	private NumberAxis yAxis;

	@FXML
	void handleAtrasButton() {
		ventanaPrincipal.cargarEstadisticosPane(miEspectaculo, miAdmin);
	}

	@FXML
	void initialize() {
		assert barChart != null : "fx:id=\"barChart\" was not injected: check your FXML file 'AtencionPane.fxml'.";
		assert xAxis != null : "fx:id=\"xAxis\" was not injected: check your FXML file 'AtencionPane.fxml'.";
		assert yAxis != null : "fx:id=\"yAxis\" was not injected: check your FXML file 'AtencionPane.fxml'.";
	}

	public PrincipalController getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public void setVentanaPrincipal(PrincipalController ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
		initBarchart();
	}

	public Administrador getMiAdmin() {
		return miAdmin;
	}

	public void setMiAdmin(Administrador miAdmin) {
		this.miAdmin = miAdmin;
	}

	public Espectaculo getMiEspectaculo() {
		return miEspectaculo;
	}

	public void setMiEspectaculo(Espectaculo miEspectaculo) {
		this.miEspectaculo = miEspectaculo;
	}

	public void initBarchart() {
		barChart.getData().clear();
		ArrayList<ArrayList<String>> data = ApachePoi.readData(new File("src/util/EspectaculoRespuestasDataset.xlsx"));
		XYChart.Series peopleSeries = new XYChart.Series();
		peopleSeries.setName("Personas");
		int counterVeryGood = 0;
		int counterGood = 0;
		int counterNormal = 0;
		int counterBad = 0;
		int counterVeryBad = 0;
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).get(0).trim().equalsIgnoreCase(miEspectaculo.getNombre())) {
				if (data.get(i).get(7).equalsIgnoreCase("Muy bueno"))
					counterVeryGood++;
				if (data.get(i).get(7).equalsIgnoreCase("Bueno"))
					counterGood++;
				if (data.get(i).get(7).equalsIgnoreCase("Normal"))
					counterNormal++;
				if (data.get(i).get(7).equalsIgnoreCase("Malo"))
					counterBad++;
				if (data.get(i).get(7).equalsIgnoreCase("Muy malo"))
					counterVeryBad++;
			}
		}
		peopleSeries.getData().add(new XYChart.Data("Muy bueno", counterVeryGood));
		peopleSeries.getData().add(new XYChart.Data("Bueno", counterGood));
		peopleSeries.getData().add(new XYChart.Data("Normal", counterNormal));
		peopleSeries.getData().add(new XYChart.Data("Malo", counterBad));
		peopleSeries.getData().add(new XYChart.Data("Muy malo", counterVeryBad));
		barChart.getData().add(peopleSeries);
	}
}
