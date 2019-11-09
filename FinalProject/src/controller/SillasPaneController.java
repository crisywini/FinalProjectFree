package controller;

import exceptions.BoletaNoExisteException;
import exceptions.BoletaRepetidaException;
import exceptions.LimiteExcedidoException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
	final EventHandler<ActionEvent> manejadorSillasButton = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			Button buttonSelected = (Button) event.getSource();
			for (int i = 0; i < sillasButton.length; i++) {
				for (int j = 0; j < sillasButton[i].length; j++) {
					if (buttonSelected.getId().equals(sillasButton[i][j].getId())) {
						buttonSelected.setStyle("-fx-background-color: #ec711b;");
						try {
							System.out.println(miCliente);
							System.out.println(miCliente.getMiReservaAsociada());
							miCliente.getMiReservaAsociada().agregarBoleta((i + j) + "",
									miSeccion.getMisPuestos()[i][j], miCliente);
						} catch (BoletaRepetidaException e) {
							ventanaPrincipal.showAlert(e.getMessage(), "", "ERROR", AlertType.ERROR);
						} catch (BoletaNoExisteException e) {
							ventanaPrincipal.showAlert(e.getMessage(), "", "ERROR", AlertType.ERROR);
						} catch (LimiteExcedidoException e) {
							ventanaPrincipal.showAlert(e.getMessage(), "", "ERROR", AlertType.ERROR);
						}
						if (buttonSelected.getStyle().equals("-fx-background-color: #ec711b;")) {

							try {
								miCliente.getMiReservaAsociada().eliminarBoleta((i + j) + "");
								buttonSelected.setStyle("-fx-background-color: none;");
							} catch (BoletaNoExisteException e) {
								ventanaPrincipal.showAlert(e.getMessage(), "", "ERROR", AlertType.ERROR);
							}
						}
					}
				}
			}

		}
	};

	@FXML
	private Label seccionEspectaculoLabel;

	@FXML
	private VBox sillasVBox;

	@FXML
	void handleAtrasButton() {
		ventanaPrincipal.cargarComprarBoletasPane(miEspectaculo, miCliente);

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
						sillasButton[i][j].setStyle("-fx-background-color: #ec711b;");
						sillasButton[i][j].setDisable(true);
					}
					sillasButton[i][j].setOnAction(manejadorSillasButton);
					sillasGridPane.add(sillasButton[i][j], j, i);
				}
			}
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

}
