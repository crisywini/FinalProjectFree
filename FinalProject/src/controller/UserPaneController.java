package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
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
	private TableColumn<Espectaculo, ImageView> imageTableColumn;

	@FXML
	private TableColumn<Espectaculo, String> nombreTableColumn;

	@FXML
	private TableColumn<Espectaculo, String> tipoTableColumn;

	@FXML
	void handleActualizarDatosButton() {
		System.out.println(miCliente);
		ventanaPrincipal.cargarActualizarDatosUserPane(getMiCliente());
	}

	@FXML
	void handleVolverButton() {
		ventanaPrincipal.showAlert("Hasta luego: " + miCliente.getNombre(), "", "Adios", AlertType.INFORMATION);
		ventanaPrincipal.cargarUserSingIn();
	}

	@FXML
	void initialize() {
		assert userNameLabel != null : "fx:id=\"userNameLabel\" was not injected: check your FXML file 'UserPane.fxml'.";
		assert espectaculosTableView != null : "fx:id=\"espectaculosTableView\" was not injected: check your FXML file 'UserPane.fxml'.";
		assert imageTableColumn != null : "fx:id=\"imageTableColumn\" was not injected: check your FXML file 'UserPane.fxml'.";
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
		// ImageView se crea un ImageView en Espectaculo el cual se le pone la ruta
		// dependiendo el tipo
		tipoTableColumn.setCellValueFactory(cellData -> cellData.getValue().tipoProperty());

		ObservableList<Espectaculo> misEspectaculos = FXCollections
				.observableArrayList(ventanaPrincipal.getPrincipal().obtenerListaEspectaculos());
		espectaculosTableView.setItems(misEspectaculos);
	}
}