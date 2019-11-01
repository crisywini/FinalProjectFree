package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Espectaculo;
import model.TipoEspectaculo;

public class AgregarEspectaculoController {
	private PrincipalController ventanaPrincipal;

	private AdminViewController vistaAdmin;
	
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
    void handleAceptarButton(ActionEvent event) 
    {
    	
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
}
