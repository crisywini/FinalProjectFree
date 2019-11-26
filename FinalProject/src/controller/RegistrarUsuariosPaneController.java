package controller;

import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import model.Boleta;
import model.Cliente;
import model.Espectaculo;
import model.Seccion;

public class RegistrarUsuariosPaneController {

	private PrincipalController ventanaPrincipal;
	private Cliente miCliente;
	private Seccion miSeccion;
	private Espectaculo miEspectaculo;
	private HashMap<String, Cliente> misClientes = new HashMap<String, Cliente>();
	private HashMap<String, Boleta> misBoletas;

	@FXML
	private AnchorPane pane;
	@FXML
	private TableView<Cliente> clienteTableView;

	@FXML
	private TableColumn<Cliente, String> nombreTableColumn;

	@FXML
	private TableColumn<Cliente, String> apellidoTableColumn;

	@FXML
	private TableColumn<Cliente, String> idTableColumn;

	@FXML
	void handleComprarBoletasButton() {
		if (misClientes.size() == misBoletas.size()) {
			ventanaPrincipal.cargarComprarBoletasPane(miCliente, miSeccion, miEspectaculo, misClientes, misBoletas);
		} else
			ventanaPrincipal.showAlert("Debes seleccionar algun cliente", "", "ADVERTENCIA", AlertType.WARNING);
	}

	@FXML
	void handleRegistrarButton() {
		ventanaPrincipal.cargarUserSignUp(true, miCliente, miEspectaculo, miSeccion, misBoletas);
	}

	@FXML
	void handleSeleccionarButton() {
		if (isSelected()) {
			if (misClientes.size() < misBoletas.size()) {
				if (!misClientes.containsKey(clienteTableView.getSelectionModel().getSelectedItem().getId())) {
					misClientes.put(clienteTableView.getSelectionModel().getSelectedItem().getId(),
							clienteTableView.getSelectionModel().getSelectedItem());
					ventanaPrincipal
							.showAlert(
									"El cliente: " + clienteTableView.getSelectionModel().getSelectedItem().getNombre()
											+ " va a acompañarte al espectaculo.",
									"", "Informacion", AlertType.INFORMATION);
				} else
					ventanaPrincipal
							.showAlert("El cliente: " + clienteTableView.getSelectionModel().getSelectedItem().getId()
									+ " ya se encuentra registrado", "", "ADVERTENCIA", AlertType.WARNING);
			} else
				ventanaPrincipal.showAlert("Solo seleccionaste: " + misBoletas.size() + " puestos", "", "ADVERTENCIA",
						AlertType.WARNING);
		} else
			ventanaPrincipal.showAlert("Debes seleccionar algun cliente", "", "Advertencia", AlertType.WARNING);
	}

	@FXML
	void handleAtrasButton() {
		misBoletas.clear();
		misClientes.clear();
		ventanaPrincipal.cargarEscenarioPane(miEspectaculo, miCliente);
	}

	@FXML
	void initialize() {
		assert clienteTableView != null : "fx:id=\"clienteTableView\" was not injected: check your FXML file 'RegistrarUsuariosPane.fxml'.";
		assert nombreTableColumn != null : "fx:id=\"nombreTableColumn\" was not injected: check your FXML file 'RegistrarUsuariosPane.fxml'.";
		assert apellidoTableColumn != null : "fx:id=\"apellidoTableColumn\" was not injected: check your FXML file 'RegistrarUsuariosPane.fxml'.";
		assert idTableColumn != null : "fx:id=\"idTableColumn\" was not injected: check your FXML file 'RegistrarUsuariosPane.fxml'.";
		pane.setStyle("-fx-background-image: url(\"file:src/images/FondoCliente (2).jpg\")");
	}

	public void initTable() {
		nombreTableColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
		apellidoTableColumn.setCellValueFactory(cellData -> cellData.getValue().apellidoProperty());
		idTableColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		clienteTableView.setItems(Main.clienteData);
	}

	public boolean isSelected() {
		return !clienteTableView.getSelectionModel().isEmpty();
	}

	public PrincipalController getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public void setVentanaPrincipal(PrincipalController ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
		initTable();
	}

	public Seccion getMiSeccion() {
		return miSeccion;
	}

	public void setMiSeccion(Seccion miSeccion) {
		this.miSeccion = miSeccion;
	}

	public Espectaculo getMiEspectaculo() {
		return miEspectaculo;
	}

	public void setMiEspectaculo(Espectaculo miEspectaculo) {
		this.miEspectaculo = miEspectaculo;
	}

	public Cliente getMiCliente() {
		return miCliente;
	}

	public void setMiCliente(Cliente miCliente) {
		this.miCliente = miCliente;
	}

	public HashMap<String, Boleta> getMisBoletas() {
		return misBoletas;
	}

	public void setMisBoletas(HashMap<String, Boleta> misBoletas) {
		this.misBoletas = misBoletas;
	}

	public HashMap<String, Cliente> getMisClientes() {
		return misClientes;
	}

	public void setMisClientes(HashMap<String, Cliente> misClientes) {
		this.misClientes = misClientes;
	}
}