package controller;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Cliente;
import model.EstadoCivil;
import model.EstratoSocioeconomico;
import model.Genero;
import model.NivelDeEstudio;

public class ActualizarDatosUserPaneController {

	private PrincipalController ventanaPrincipal;
	@FXML
	private TextField nombreField;

	@FXML
	private TextField apellidoField;

	@FXML
	private TextField idField;

	@FXML
	private TextField correoField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private ComboBox<Genero> generoComboBox;

	@FXML
	private DatePicker datePicker;

	@FXML
	private TextField direccionField;

	@FXML
	private TextField cuentaField;

	@FXML
	private TextField ciudadDeResidenciaField;

	@FXML
	private ComboBox<EstratoSocioeconomico> estratoComboBox;

	@FXML
	private ComboBox<EstadoCivil> estadoCivilComboBox;

	@FXML
	private ComboBox<NivelDeEstudio> nivelDeEstudioComboBox;

	@FXML
	private TextField cupoField;
	private Cliente miCliente;

	@FXML
	void initialize() {

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
		nombreField.setText(miCliente.getNombre());
		apellidoField.setText(miCliente.getApellido());
		idField.setText(miCliente.getId());
		correoField.setText(miCliente.getEmail());
		LocalDate fecha = LocalDate.of(miCliente.getMiFechaDeNacimiento().getYear(),
				miCliente.getMiFechaDeNacimiento().getMonth(), miCliente.getMiFechaDeNacimiento().getDay());
		datePicker.setValue(fecha);
		generoComboBox.setValue(miCliente.getMiGenero());
		direccionField.setText(miCliente.getDireccion());
		cupoField.setText("$" + miCliente.getMiCuentaAsociada().getDineroTotal() + "");
		ciudadDeResidenciaField.setText(miCliente.getCiudadDeResidencia());
		estratoComboBox.setValue(miCliente.getMiEstrato());
		estadoCivilComboBox.setValue(miCliente.getMiEstadoCivil());
		nivelDeEstudioComboBox.setValue(miCliente.getMiNivelDeEstudio());
	}
}
