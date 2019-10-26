package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import model.Espectaculo;

public class AdminViewController {

	private ObservableList<Espectaculo> espectaculos;

	@FXML
	private Label adminLabel;

	@FXML
	private Button btnEstadisticas;

	@FXML
	private TableView<?> tablaEventos;

	@FXML
	private Button btnAgregar;

	@FXML
	private Button btnEliminar;

	@FXML
	private Button btnVer;

	public void agregarEspectaculo() {
		Espectaculo nuevo = new Espectaculo();
	}

}
