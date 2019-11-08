package controller;

import java.util.ArrayList;
import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import model.Administrador;
import model.Espectaculo;

public class AdminViewController {

	private ObservableList<Espectaculo> listEspectaculos;
	private PrincipalController ventanaPrincipal;
	private Administrador miAdmin;
	
    @FXML
    private Label adminLabel;

    @FXML
    private Button btnEstadisticas;

    @FXML
    private TableView<?> tablaEventos;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnVer;
    
    @FXML
    void handleAgregarEspectaculo() 
    {
    	ventanaPrincipal.cargarAgregarEspectaculoPane(miAdmin);
    }

    @FXML
    void handleEliminarEspectaculo(ActionEvent event) {
    	
    }

    @FXML
    void handleVerEspectaculo() {

    }

	public PrincipalController getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public void setVentanaPrincipal(PrincipalController ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	public Administrador getMiAdmin() {
		return miAdmin;
	}

	public void setMiAdmin(Administrador miAdmin) {
		this.miAdmin = miAdmin;
	}
	
	public void initTableEspectaculos() {
		nombreTableColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
		// ImageView se crea un ImageView en Espectaculo el cual se le pone la ruta
		// dependiendo el tipo
		tipoTableColumn.setCellValueFactory(cellData -> cellData.getValue().tipoProperty());

		ObservableList<Espectaculo> misEspectaculos = FXCollections
				.observableArrayList(ventanaPrincipal.getPrincipal().obtenerListaEspectaculos());
		espectaculosTableView.setItems(misEspectaculos);
	}
    
    

}
