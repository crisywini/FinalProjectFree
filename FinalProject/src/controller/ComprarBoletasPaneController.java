package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Cliente;
import model.Espectaculo;

public class ComprarBoletasPaneController {

	private PrincipalController ventanaPrincipal;
	private Cliente miCliente;
	private Espectaculo miEspectaculo;
	@FXML
	private Label nombreEspectaculoLabel;

	@FXML
	private Label tipoEspectaculoLabel;

	@FXML
	void handleAtrasButton() {
		ventanaPrincipal.cargarUserPane(miCliente);
	}

	@FXML
	void handlePlatea1Button() {

	}

	@FXML
	void handlePlatea2Button() {

	}

	@FXML
	void handlePlatea3Button() {

	}

	@FXML
	void handlePrimerPiso1Button() {

	}

	@FXML
	void handlePrimerPiso2Button() {

	}

	@FXML
	void handlePrimerPiso3Button() {

	}

	@FXML
	void handleSegundoPiso1Button() {

	}

	@FXML
	void handleSegundoPiso2Button() {

	}

	@FXML
	void handleSegundoPiso3Button() {

	}

	@FXML
	void initialize() {
		assert nombreEspectaculoLabel != null : "fx:id=\"nombreEspectaculoLabel\" was not injected: check your FXML file 'ComprarBoletasPane.fxml'.";
		assert tipoEspectaculoLabel != null : "fx:id=\"tipoEspectaculoLabel\" was not injected: check your FXML file 'ComprarBoletasPane.fxml'.";

	}

	public PrincipalController getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public void setVentanaPrincipal(PrincipalController ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	public Cliente getMiCliente() {
		return miCliente;
	}

	public void setMiCliente(Cliente miCliente) {
		this.miCliente = miCliente;
	}

	public Espectaculo getMiEspectaculo() {
		return miEspectaculo;
	}

	public void setMiEspectaculo(Espectaculo miEspectaculo) {
		this.miEspectaculo = miEspectaculo;
	}
}
