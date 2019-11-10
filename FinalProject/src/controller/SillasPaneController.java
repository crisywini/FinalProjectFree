package controller;

import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Boleta;
import model.Cliente;
import model.Espectaculo;
import model.EstadoPuesto;
import model.Seccion;

public class SillasPaneController {

	private PrincipalController ventanaPrincipal;
	private Cliente miCliente;
	private Seccion miSeccion;
	private Espectaculo miEspectaculo;
	private Button[][] sillasButton;
	private HashMap<String, Boleta> misBoletas = new HashMap<String, Boleta>();
	final EventHandler<ActionEvent> manejadorSillasButton = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			Button buttonSelected = (Button) event.getSource();
			int i = Integer.parseInt(buttonSelected.getId().charAt(0) + "");
			int j = Integer.parseInt(buttonSelected.getId().charAt(1) + "");
			if (misBoletas.size() <= 5) {
				buttonSelected.setStyle("-fx-background-color: orange;");
				misBoletas.put(buttonSelected.getId() + "",
						new Boleta(buttonSelected.getId() + "", miSeccion.getMisPuestos()[i][j], miCliente));
			} else {
				ventanaPrincipal.showAlert("El maximo de puestos a elejir por reserva es: " + 6, "", "ADVERTENCIA",
						AlertType.WARNING);
			}
		}

	};

	@FXML
	private Label seccionEspectaculoLabel;

	@FXML
	private VBox sillasVBox;

	@FXML
	void handleAtrasButton() {
		ventanaPrincipal.cargarEscenarioPane(miEspectaculo, miCliente);
	}

	@FXML
	void handleComprarButton() {
		if (misBoletas.size() > 0)
			ventanaPrincipal.cargarRegistrarUsuariosPane(miCliente, miSeccion, miEspectaculo, misBoletas);
		else
			ventanaPrincipal.showAlert("Debes seleccionar alguna silla", "", "Informacion", AlertType.INFORMATION);
	}

	@FXML
	void initialize() {

	}

	public void initSillasButton() {
		GridPane sillasGridPane = new GridPane();
		sillasGridPane.setId("grid");
		sillasGridPane.setHgap(10);
		sillasGridPane.setVgap(10);
		char auxiliar = 'A';
		sillasButton = new Button[miSeccion.getMisPuestos().length][miSeccion.getMisPuestos()[0].length];
		for (int i = 0; i < miSeccion.getMisPuestos().length; i++) {
			for (int j = 0; j < miSeccion.getMisPuestos()[i].length; j++) {
				if (miSeccion.getMisPuestos()[i][j] != null) {
					sillasButton[i][j] = new Button(auxiliar + "" + j);
					sillasButton[i][j].setId(i + "" + j);
					if (miSeccion.getMisPuestos()[i][j].getMiEstado() == EstadoPuesto.LIBRE)
						sillasButton[i][j].setStyle("-fx-background-color: #41ec1b;");
					if (miSeccion.getMisPuestos()[i][j].getMiEstado() == EstadoPuesto.OCUPADO) {
						sillasButton[i][j].setStyle("-fx-background-color: red;");
						sillasButton[i][j].setDisable(true);
					}
					if (miSeccion.getMisPuestos()[i][j].getMiEstado() == EstadoPuesto.RESERVADO) {
						sillasButton[i][j].setStyle("-fx-background-color: orange;");
						sillasButton[i][j].setDisable(true);
					}
					sillasButton[i][j].setOnAction(manejadorSillasButton);
					sillasGridPane.add(sillasButton[i][j], j, i);
				}
			}
			auxiliar++;
			if (auxiliar == 'Z')
				auxiliar = 'A';
		}
		sillasGridPane.setAlignment(Pos.CENTER);
		sillasVBox.getChildren().add(sillasGridPane);
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

	public Seccion getMiSeccion() {
		return miSeccion;
	}

	public void setMiSeccion(Seccion miSeccion) {
		this.miSeccion = miSeccion;
		seccionEspectaculoLabel.setText(miSeccion.getMiTipo() + "");
		initSillasButton();
	}

	public Espectaculo getMiEspectaculo() {
		return miEspectaculo;
	}

	public void setMiEspectaculo(Espectaculo miEspectaculo) {
		this.miEspectaculo = miEspectaculo;
	}

	public VBox getSillasVBox() {
		return sillasVBox;
	}

	public void setSillasVBox(VBox sillasVBox) {
		this.sillasVBox = sillasVBox;
	}

	public HashMap<String, Boleta> getMisBoletas() {
		return misBoletas;
	}

	public void setMisBoletas(HashMap<String, Boleta> misBoletas) {
		this.misBoletas = misBoletas;
	}

}
