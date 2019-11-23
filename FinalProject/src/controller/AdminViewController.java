package controller;

import exceptions.EspectaculoNullException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Administrador;
import model.Espectaculo;

public class AdminViewController {

	private PrincipalController ventanaPrincipal;
	private Administrador miAdmin;
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
	private Button btnVer;

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
	void handleEliminarEspectaculo(ActionEvent event) throws EspectaculoNullException {
		if (isSelectedEspectaculo()) {
			Espectaculo e = tablaEventos.getSelectionModel().getSelectedItem();
			ventanaPrincipal.eliminarEspectaculo(e);
		} else
			ventanaPrincipal.showAlert("Debes seleccionar un espectaculo", "", "ADVERTENCIA", AlertType.WARNING);
	}

	@FXML
	void handleVerEspectaculo() {
		// REalizar ver espectaculo
	}

	@FXML
	void handleVerEstadisticosButton() {
		// REalizar Estadisticos

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
		columnaFecha1.setCellValueFactory(cellData -> cellData.getValue().fechasProperty());
		tablaEventos.setItems(Main.espectaculosData);
	}

	public boolean isSelectedEspectaculo() {
		int pos = tablaEventos.getSelectionModel().getSelectedIndex();
		return pos != -1;
	}

}
