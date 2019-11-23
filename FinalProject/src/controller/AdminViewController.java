package controller;

import exceptions.EspectaculoNullException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Administrador;
import model.Espectaculo;

public class AdminViewController {

	private PrincipalController ventanaPrincipal;
	private Administrador miAdmin;
	
    @FXML
    private Label adminLabel;

    @FXML
    private Button btnEstadisticas;

    @FXML
    private TableView<Espectaculo> tablaEventos;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnVer;
    
    @FXML
    private TableColumn<Espectaculo, String> columnaEvento;

    @FXML
    private TableColumn<Espectaculo, String> columnaTipo;

    @FXML
    private TableColumn<Espectaculo, String> columnaFecha1;

    @FXML
    private TableColumn<Espectaculo, String> columnaFecha2;
    
    @FXML
    void handleAgregarEspectaculo() 
    {
    	ventanaPrincipal.cargarAgregarEspectaculoPane(miAdmin, this);
    }

    @FXML
    void handleEliminarEspectaculo(ActionEvent event) throws EspectaculoNullException {
    	if(isSelectedEspectaculo())
    	{
    		Espectaculo e = tablaEventos.getSelectionModel().getSelectedItem();
    		ventanaPrincipal.eliminarEspectaculo(e);
    	}
    }

    @FXML
    void handleVerEspectaculo() {
    	
    	
    }
    

	public PrincipalController getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public void setVentanaPrincipal(PrincipalController ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
		initTableEspectaculos();
	}

	public Administrador getMiAdmin() {
		return miAdmin;
	}

	public void setMiAdmin(Administrador miAdmin) {
		this.miAdmin = miAdmin;
	}
	
	public void initTableEspectaculos() {
		columnaEvento.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
		columnaTipo.setCellValueFactory(cellData -> cellData.getValue().tipoProperty());
		columnaFecha1.setCellValueFactory(cellData -> cellData.getValue().fechasProperty());
		tablaEventos.setItems(Main.espectaculosData);
	}
	
	public boolean isSelectedEspectaculo() {
		return !tablaEventos.getSelectionModel().isEmpty();
	}
    
}
