package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Cliente;
import model.Escenario;
import model.Espectaculo;
import model.Seccion;
import model.TipoSeccion;

public class EscenarioPaneController {

	private PrincipalController ventanaPrincipal;
	private Cliente miCliente;
	private Espectaculo miEspectaculo;
	@FXML
	private AnchorPane pane;
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
		Escenario miEscenario = miEspectaculo.getMisEscenarios().get(0);
		Seccion miSeccion = miEscenario.getMisSecciones().get(0);
		miSeccion.setMiTipo(TipoSeccion.PLATEA_1);
		ventanaPrincipal.cargarSillasPane(miCliente, miSeccion, miEspectaculo);
	}

	@FXML
	void handlePlatea2Button() {
		Escenario miEscenario = miEspectaculo.getMisEscenarios().get(0);
		Seccion miSeccion = miEscenario.getMisSecciones().get(1);
		miSeccion.setMiTipo(TipoSeccion.PLATEA_2);

		ventanaPrincipal.cargarSillasPane(miCliente, miSeccion, miEspectaculo);

	}

	@FXML
	void handlePlatea3Button() {
		Escenario miEscenario = miEspectaculo.getMisEscenarios().get(0);
		Seccion miSeccion = miEscenario.getMisSecciones().get(2);
		miSeccion.setMiTipo(TipoSeccion.PLATEA_3);

		ventanaPrincipal.cargarSillasPane(miCliente, miSeccion, miEspectaculo);

	}

	@FXML
	void handlePrimerPiso1Button() {
		Escenario miEscenario = miEspectaculo.getMisEscenarios().get(0);
		Seccion miSeccion = miEscenario.getMisSecciones().get(3);
		miSeccion.setMiTipo(TipoSeccion.PRIMER_PISO_1);

		ventanaPrincipal.cargarSillasPane(miCliente, miSeccion, miEspectaculo);

	}

	@FXML
	void handlePrimerPiso2Button() {
		Escenario miEscenario = miEspectaculo.getMisEscenarios().get(0);
		Seccion miSeccion = miEscenario.getMisSecciones().get(4);
		miSeccion.setMiTipo(TipoSeccion.PRIMER_PISO_2);

		ventanaPrincipal.cargarSillasPane(miCliente, miSeccion, miEspectaculo);

	}

	@FXML
	void handlePrimerPiso3Button() {
		Escenario miEscenario = miEspectaculo.getMisEscenarios().get(0);
		Seccion miSeccion = miEscenario.getMisSecciones().get(5);
		miSeccion.setMiTipo(TipoSeccion.PRIMER_PISO_3);

		ventanaPrincipal.cargarSillasPane(miCliente, miSeccion, miEspectaculo);

	}

	@FXML
	void handleSegundoPiso1Button() {
		Escenario miEscenario = miEspectaculo.getMisEscenarios().get(0);
		Seccion miSeccion = miEscenario.getMisSecciones().get(6);
		miSeccion.setMiTipo(TipoSeccion.SEGUNDO_PISO_1);

		ventanaPrincipal.cargarSillasPane(miCliente, miSeccion, miEspectaculo);

	}

	@FXML
	void handleSegundoPiso2Button() {
		Escenario miEscenario = miEspectaculo.getMisEscenarios().get(0);
		Seccion miSeccion = miEscenario.getMisSecciones().get(7);
		miSeccion.setMiTipo(TipoSeccion.SEGUNDO_PISO_2);

		ventanaPrincipal.cargarSillasPane(miCliente, miSeccion, miEspectaculo);

	}

	@FXML
	void handleSegundoPiso3Button() {
		Escenario miEscenario = miEspectaculo.getMisEscenarios().get(0);
		Seccion miSeccion = miEscenario.getMisSecciones().get(8);
		miSeccion.setMiTipo(TipoSeccion.SEGUNDO_PISO_3);

		ventanaPrincipal.cargarSillasPane(miCliente, miSeccion, miEspectaculo);
	}

	@FXML
	void initialize() {
		assert nombreEspectaculoLabel != null : "fx:id=\"nombreEspectaculoLabel\" was not injected: check your FXML file 'ComprarBoletasPane.fxml'.";
		assert tipoEspectaculoLabel != null : "fx:id=\"tipoEspectaculoLabel\" was not injected: check your FXML file 'ComprarBoletasPane.fxml'.";
		pane.setStyle("-fx-background-image: url(\"file:src/images/FondoEscenario(2).jpg\")");
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
		nombreEspectaculoLabel.setText(miEspectaculo.getNombre());
		tipoEspectaculoLabel.setText(miEspectaculo.getMiTipo() + "");
	}

}
