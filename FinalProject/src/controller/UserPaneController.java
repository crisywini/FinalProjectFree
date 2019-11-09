package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import model.Cliente;
import model.Espectaculo;

public class UserPaneController {
	private PrincipalController ventanaPrincipal;
	private Cliente miCliente;

	@FXML
	private Label userNameLabel;

	@FXML
	private TableView<Espectaculo> espectaculosTableView;

	@FXML
	private TableColumn<Espectaculo, String> nombreTableColumn;

	@FXML
	private TableColumn<Espectaculo, String> tipoTableColumn;
	@FXML
	private TableColumn<Espectaculo, String> fechasTableColumn;

	@FXML
	void handleActualizarDatosButton() {
		ventanaPrincipal.cargarActualizarDatosUserPane(getMiCliente());
	}

	@FXML
	void handleVolverButton() {
		ventanaPrincipal.showAlert("Hasta luego: " + miCliente.getNombre(), "", "Adios", AlertType.INFORMATION);
		ventanaPrincipal.cargarUserSingIn();
	}

	@FXML
	void handleComprarBoletasButton() {
		if (isSelectedEspectaculo())
			ventanaPrincipal.cargarComprarBoletasPane(espectaculosTableView.getSelectionModel().getSelectedItem(),
					miCliente);
		else
			ventanaPrincipal.showAlert("Debe seleccionar un espectaculo!", "", "ADVERTENCIA", AlertType.WARNING);
	}

	@FXML
	void initialize() {
		assert userNameLabel != null : "fx:id=\"userNameLabel\" was not injected: check your FXML file 'UserPane.fxml'.";
		assert espectaculosTableView != null : "fx:id=\"espectaculosTableView\" was not injected: check your FXML file 'UserPane.fxml'.";
		assert nombreTableColumn != null : "fx:id=\"nombreTableColumn\" was not injected: check your FXML file 'UserPane.fxml'.";
		assert tipoTableColumn != null : "fx:id=\"tipoTableColumn\" was not injected: check your FXML file 'UserPane.fxml'.";

	}

	public PrincipalController getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public void setVentanaPrincipal(PrincipalController ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
		initTableEspectaculos();
	}

	public Cliente getMiCliente() {
		return miCliente;
	}

	public void setMiCliente(Cliente miCliente) {
		this.miCliente = miCliente;
		userNameLabel.setText(miCliente.getNombre());
	}

	public void initTableEspectaculos() {
		nombreTableColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
		tipoTableColumn.setCellValueFactory(cellData -> cellData.getValue().tipoProperty());
		fechasTableColumn.setCellValueFactory(cellData -> cellData.getValue().fechasProperty());
		espectaculosTableView.setItems(Main.espectaculosData);
	}

	public boolean isSelectedEspectaculo() {
		return !espectaculosTableView.getSelectionModel().isEmpty();
	}
}