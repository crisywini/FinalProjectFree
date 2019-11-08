package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.EspectaculoRepetidoException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Administrador;
import model.Date;
import model.Espectaculo;
import model.TipoEspectaculo;

public class AgregarEspectaculoController {
	private PrincipalController ventanaPrincipal;

	private AdminViewController vistaAdmin;
	
	private Administrador adm;
	
	private ObservableList<TipoEspectaculo> tiposEspectaculos;
	
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
    void handleAceptarButton() throws EspectaculoRepetidoException 
    {
    	Espectaculo e = new Espectaculo(txtNombre.getText(), boxTipo.getSelectionModel().getSelectedItem());
    	LocalDate localF1 = dtaFecha1.getValue();
    	LocalDate localF2 = dtaFecha2.getValue();
    	Date f1 = new Date(localF1.getDayOfMonth(), localF1.getMonthValue(), localF1.getYear());
    	Date f2 = new Date(localF2.getDayOfMonth(), localF2.getMonthValue(), localF2.getYear());
    	
    	ArrayList<Date> fechas = new ArrayList<Date>();
    	fechas.add(f1);
    	fechas.add(f2);
    	e.setFechas(fechas);
    	
    	ventanaPrincipal.getPrincipal().agregarEspectaculo(e);
    	
    	ventanaPrincipal.showAlert("Espectaculo agregado con exito", "", "", AlertType.INFORMATION);
    	
    	ventanaPrincipal.cargarAdminViewPane(adm);
    }

    @FXML
    void handleCancelarButton() 
    {

    }

    @FXML
    void initialize() {
        assert txtNombre != null : "fx:id=\"txtNombre\" was not injected: check your FXML file 'AgregarEspectaculoPane.fxml'.";
        assert dtaFecha1 != null : "fx:id=\"dtaFecha1\" was not injected: check your FXML file 'AgregarEspectaculoPane.fxml'.";
        assert dtaFecha2 != null : "fx:id=\"dtaFecha2\" was not injected: check your FXML file 'AgregarEspectaculoPane.fxml'.";
        assert boxTipo != null : "fx:id=\"boxTipo\" was not injected: check your FXML file 'AgregarEspectaculoPane.fxml'.";
        assert btnAceptar != null : "fx:id=\"btnAceptar\" was not injected: check your FXML file 'AgregarEspectaculoPane.fxml'.";
        assert btnCancelar != null : "fx:id=\"btnCancelar\" was not injected: check your FXML file 'AgregarEspectaculoPane.fxml'.";

    }

	public PrincipalController getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public void setVentanaPrincipal(PrincipalController ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
		tiposEspectaculos = FXCollections.observableArrayList(ventanaPrincipal.getPrincipal().obtenerListadoTipoEspectaculo());
		boxTipo.setItems(tiposEspectaculos);
	}

	public void setAdm(Administrador adm) {
		this.adm = adm;
	}
	
	
	
}
