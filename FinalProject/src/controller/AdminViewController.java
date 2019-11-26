package controller;

import exceptions.EspectaculoNullException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Administrador;
import model.Espectaculo;

public class AdminViewController {

	private PrincipalController ventanaPrincipal;
	private Administrador miAdmin;
	@FXML
	private AnchorPane pane;
	@FXML
	private ImageView image;
	@FXML
	private Label nombreAdminLabel;
	@FXML
	private Label adminLabel;

	@FXML
	private TableView<Espectaculo> tablaEventos;

	@FXML
	private Button btnAgregar;

	@FXML
	private Button btnEliminar;

	@FXML
	private TableColumn<Espectaculo, String> columnaEvento;

	@FXML
	private TableColumn<Espectaculo, String> columnaTipo;

	@FXML
	private TableColumn<Espectaculo, String> columnaFecha1;

	@FXML
	private TableColumn<Espectaculo, String> columnaFecha2;

	@FXML
	void handleAgregarEspectaculo() {
		ventanaPrincipal.cargarAgregarEspectaculoPane(miAdmin, this);
	}

	@FXML
	void handleEliminarEspectaculo() {
		if (isSelectedEspectaculo()) {
			Espectaculo e = tablaEventos.getSelectionModel().getSelectedItem();
			try {
				ventanaPrincipal.eliminarEspectaculo(e);
			} catch (EspectaculoNullException e1) {
				ventanaPrincipal.showAlert(e1.getMessage(), "", "ERROR", AlertType.ERROR);
			}
		} else
			ventanaPrincipal.showAlert("Debes seleccionar un espectaculo!", "", "ADVERTENCIA", AlertType.WARNING);
	}

	@FXML
	void handleVerEstadisticosButton() {
		if (isSelectedEspectaculo()) {
			Espectaculo miEspectaculo = tablaEventos.getSelectionModel().getSelectedItem();
			ventanaPrincipal.cargarEstadisticosPane(miEspectaculo, miAdmin);
		} else
			ventanaPrincipal.showAlert("Debes seleccionar un espectaculo", "", "ADVERTENCIA", AlertType.WARNING);
	}

	@FXML
	void handleVolverButton() {
		ventanaPrincipal.cargarMenu();
	}

	public PrincipalController getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public void setVentanaPrincipal(PrincipalController ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
		initTableEspectaculos();
	}

	public Administrador getMiAdmin() {
		return miAdmin;
	}

	public void setMiAdmin(Administrador miAdmin) {
		this.miAdmin = miAdmin;
		nombreAdminLabel.setText(miAdmin.getNombre() + " " + miAdmin.getApellido());
	}

	public void initTableEspectaculos() {
		columnaEvento.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
		columnaTipo.setCellValueFactory(cellData -> cellData.getValue().tipoProperty());
		columnaFecha1.setCellValueFactory(cellData -> cellData.getValue().fechas1Property());
		columnaFecha2.setCellValueFactory(cellData -> cellData.getValue().fechas2Property());
		tablaEventos.setItems(Main.espectaculosData);
	}

	public boolean isSelectedEspectaculo() {
		int pos = tablaEventos.getSelectionModel().getSelectedIndex();
		return pos != -1;
	}

	@FXML
	void initialize() {
		assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'AdminViewPane.fxml'.";
		assert adminLabel != null : "fx:id=\"adminLabel\" was not injected: check your FXML file 'AdminViewPane.fxml'.";
		assert nombreAdminLabel != null : "fx:id=\"nombreAdminLabel\" was not injected: check your FXML file 'AdminViewPane.fxml'.";
		assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'AdminViewPane.fxml'.";
		assert tablaEventos != null : "fx:id=\"tablaEventos\" was not injected: check your FXML file 'AdminViewPane.fxml'.";
		assert columnaEvento != null : "fx:id=\"columnaEvento\" was not injected: check your FXML file 'AdminViewPane.fxml'.";
		assert columnaTipo != null : "fx:id=\"columnaTipo\" was not injected: check your FXML file 'AdminViewPane.fxml'.";
		assert columnaFecha1 != null : "fx:id=\"columnaFecha1\" was not injected: check your FXML file 'AdminViewPane.fxml'.";
		assert columnaFecha2 != null : "fx:id=\"columnaFecha2\" was not injected: check your FXML file 'AdminViewPane.fxml'.";
		assert btnAgregar != null : "fx:id=\"btnAgregar\" was not injected: check your FXML file 'AdminViewPane.fxml'.";
		assert btnEliminar != null : "fx:id=\"btnEliminar\" was not injected: check your FXML file 'AdminViewPane.fxml'.";
		pane.setStyle("-fx-background-image: url(\"file:src/images/FondoAdminPane.jpg\")");
		image.setImage(new Image("file:src/images/AdministradorAnalisis1.png"));
	}

}
