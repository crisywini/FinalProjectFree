package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.EspectaculoRepetidoException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Administrador;
import model.Date;
import model.TipoEspectaculo;

public class AgregarEspectaculoController {
	private PrincipalController ventanaPrincipal;

	private AdminViewController vistaAdmin;

	private Administrador adm;

	private ObservableList<TipoEspectaculo> tiposEspectaculos;
	@FXML
	private AnchorPane pane;

	@FXML
	private TextField txtNombre;

	@FXML
	private DatePicker dtaFecha1;

	@FXML
	private DatePicker dtaFecha2;

	@FXML
	private ComboBox<TipoEspectaculo> boxTipo;

	@FXML
	private Button btnAceptar;

	@FXML
	private Button btnCancelar;

	@FXML
	void handleAceptarButton() {
		if (isInputValid()) {

			try {
				LocalDate localF1 = dtaFecha1.getValue();
				LocalDate localF2 = dtaFecha2.getValue();
				Date f1 = new Date(localF1.getDayOfMonth(), localF1.getMonthValue(), localF1.getYear());
				Date f2 = new Date(localF2.getDayOfMonth(), localF2.getMonthValue(), localF2.getYear());

				ArrayList<Date> fechas = new ArrayList<Date>();
				fechas.add(f1);
				fechas.add(f2);
				ventanaPrincipal.getPrincipal().agregarEspectaculo(txtNombre.getText(),
						boxTipo.getSelectionModel().getSelectedItem(), fechas);
				ventanaPrincipal.showAlert("Espectaculo agregado con exito", "", "", AlertType.INFORMATION);

				ventanaPrincipal.actualizarListaEspectaculos();

				ventanaPrincipal.cargarAdminViewPane(adm);
				dtaFecha1.setValue(null);
				dtaFecha2.setValue(null);
				txtNombre.setText("");
				boxTipo.setValue(null);
			} catch (EspectaculoRepetidoException e) {
				ventanaPrincipal.showAlert(e.getMessage(), "", "ERROR", AlertType.ERROR);
			}
		}
	}

	@FXML
	void handleCancelarButton() {
		ventanaPrincipal.cargarAdminViewPane(adm);
	}

	@FXML
	void initialize() {
		assert txtNombre != null : "fx:id=\"txtNombre\" was not injected: check your FXML file 'AgregarEspectaculoPane.fxml'.";
		assert dtaFecha1 != null : "fx:id=\"dtaFecha1\" was not injected: check your FXML file 'AgregarEspectaculoPane.fxml'.";
		assert dtaFecha2 != null : "fx:id=\"dtaFecha2\" was not injected: check your FXML file 'AgregarEspectaculoPane.fxml'.";
		assert boxTipo != null : "fx:id=\"boxTipo\" was not injected: check your FXML file 'AgregarEspectaculoPane.fxml'.";
		assert btnAceptar != null : "fx:id=\"btnAceptar\" was not injected: check your FXML file 'AgregarEspectaculoPane.fxml'.";
		assert btnCancelar != null : "fx:id=\"btnCancelar\" was not injected: check your FXML file 'AgregarEspectaculoPane.fxml'.";
		pane.setStyle("-fx-background-image: url(\"file:src/images/FondoAgregarEspectaculoMedium.jpg\")");
	}

	public PrincipalController getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public void setVentanaPrincipal(PrincipalController ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
		tiposEspectaculos = FXCollections
				.observableArrayList(ventanaPrincipal.getPrincipal().obtenerListadoTipoEspectaculo());
		boxTipo.setItems(tiposEspectaculos);
	}

	public void setAdm(Administrador adm) {
		this.adm = adm;
	}

	public void setAdminView(AdminViewController vistaAdmin) {
		this.vistaAdmin = vistaAdmin;
	}

	public void actualizarTable() {
		vistaAdmin.initTableEspectaculos();
	}

	public boolean isInputValid() {
		boolean isValid = false;
		String errorMessage = "";
		if (txtNombre.getText() == null || txtNombre.getText().length() == 0)
			errorMessage += "Debe ingresar el nombre del espectaculo\n";
		if (dtaFecha1.getValue() == null || dtaFecha2.getValue() == null)
			errorMessage += "Debe ingresar alguna de las fechas\n";
		if (boxTipo.getSelectionModel().getSelectedItem() == null)
			errorMessage += "Debes seleccionar el tipo de Espectaculo\n";
		if (errorMessage.length() == 0)
			isValid = true;
		else
			ventanaPrincipal.showAlert(errorMessage, "", "ADVERTENCIA", AlertType.WARNING);
		return isValid;
	}
}
