package controller;

import java.util.HashMap;
import java.util.Iterator;

import exceptions.BoletaNoExisteException;
import exceptions.BoletaRepetidaException;
import exceptions.LimiteExcedidoException;
import exceptions.ReservaRepetidaException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import model.Boleta;
import model.Cliente;
import model.Espectaculo;
import model.EstadoPuesto;
import model.Puesto;
import model.Reserva;
import model.Seccion;
import util.Correo;

public class ComprarBoletasPaneController {
	private PrincipalController ventanaPrincipal;
	private Cliente miCliente;
	private Seccion miSeccion;
	private Espectaculo miEspectaculo;
	private HashMap<String, Cliente> misClientes;
	private HashMap<String, Boleta> misBoletas;
	private Reserva miReserva;
	private boolean isCoberturaSelected;

	@FXML
	private Label idLabel;

	@FXML
	private Label puestosLabel;

	@FXML
	private Label costoTotalLabel;

	@FXML
	void handleAtrasButton() {
		ventanaPrincipal.cargarRegistrarUsuariosPane(miCliente, miSeccion, miEspectaculo, misBoletas);
	}

	@FXML
	void handleComprarButton() {
		try {
			miCliente.setMiReservaAsociada(miReserva);
			miReserva.setMiClienteAsociado(miCliente);
			miEspectaculo.agregarReserva(miReserva.getId());

			String asunto = "Encuesta de satisfaccion";
			String cuerpo = "Gracias por realizar la compra de las boletas: \n" + miReserva.toString()
					+ "\nValor total: " + miReserva.getValorTotalReserva()
					+ "\nPor favor realiza la encuesta en el siguiente link. "
					+ "\nhttps://docs.google.com/forms/d/e/1FAIpQLScNMt2zkr6j1cJTkREpSlLAELN9LrGXr72ro8V3XH90WjUXLQ/viewform?usp=sf_link";
			Correo.enviarConGMail(miCliente.getEmail(), asunto, cuerpo);
			ventanaPrincipal.showAlert(
					"Compra realizada\nEsperamos que te agrade el espectaculo\n Por favor verifica tu correo electronico para que realices nuestra encuesta de satisfacción",
					"", "Informacion", AlertType.INFORMATION);
			ventanaPrincipal.cargarUserPane(miCliente);
		} catch (ReservaRepetidaException e) {
			ventanaPrincipal.showAlert(e.getMessage(), "", "ERROR", AlertType.ERROR);
		}
	}

	@FXML
	void handleSeguroDeCoberturaButton() {
		String mensaje = "¿Deseas pagar un seguro de cobertura?\n\nEl valor se incrementara un 0.12% al total de la compra.";
		if (ventanaPrincipal.elegir(mensaje)) {
			setCoberturaSelected(true);
			ventanaPrincipal.showAlert("Has elegido pagar un seguro de cobertura", "", "Informacion",
					AlertType.INFORMATION);
		} else
			setCoberturaSelected(false);

	}

	@FXML
	void handleCalcularCostoButton() {
		miReserva.calcularPrecioTotal(isCoberturaSelected);
		costoTotalLabel.setText("$" + miReserva.getValorTotalReserva());
	}

	@FXML
	void handleEnvioDeTiquetesButton() {
		String mensaje = "¿Desea que sus boletas se envien en fisico?\n\nEl valor se incrementara un 0.8% al total de la compra";
		if (ventanaPrincipal.elegir(mensaje)) {
			miReserva.setUbicacionEntregaBoletas("Si");
			ventanaPrincipal.showAlert("Las boletas llegarán a: " + miCliente.getDireccion(), "", "Informacion",
					AlertType.INFORMATION);
		} else
			ventanaPrincipal.showAlert("Las boletas llegarán a: " + miCliente.getEmail(), "", "Informacion",
					AlertType.INFORMATION);
	}

	@FXML
	void initialize() {
		assert idLabel != null : "fx:id=\"idLabel\" was not injected: check your FXML file 'ComprarBoletasPane.fxml'.";
		assert puestosLabel != null : "fx:id=\"puestosLabel\" was not injected: check your FXML file 'ComprarBoletasPane.fxml'.";
		assert costoTotalLabel != null : "fx:id=\"costoTotalLabel\" was not injected: check your FXML file 'ComprarBoletasPane.fxml'.";
	}

	public PrincipalController getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public void setVentanaPrincipal(PrincipalController ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
		fillReserva();
	}

	public Cliente getMiCliente() {
		return miCliente;
	}

	public void setMiCliente(Cliente miCliente) {
		this.miCliente = miCliente;
		miReserva = new Reserva(
				miCliente.getApellido() + "//" + miCliente.getId() + "<>" + miCliente.getId() + Math.random());
		idLabel.setText(miReserva.getId());
		costoTotalLabel.setText("#Selecciona calcular costo \n#Para saber el valor total de la reserva");
	}

	public Seccion getMiSeccion() {
		return miSeccion;
	}

	public void setMiSeccion(Seccion miSeccion) {
		this.miSeccion = miSeccion;
	}

	public Espectaculo getMiEspectaculo() {
		return miEspectaculo;
	}

	public void setMiEspectaculo(Espectaculo miEspectaculo) {
		this.miEspectaculo = miEspectaculo;
	}

	public HashMap<String, Cliente> getMisClientes() {
		return misClientes;
	}

	public void setMisClientes(HashMap<String, Cliente> misClientes) {
		this.misClientes = misClientes;
	}

	public HashMap<String, Boleta> getMisBoletas() {
		return misBoletas;
	}

	public void setMisBoletas(HashMap<String, Boleta> misBoletas) {
		this.misBoletas = misBoletas;
		puestosLabel.setText(misBoletas.size() + "");
	}

	public Reserva getMiReserva() {
		return miReserva;
	}

	public void setMiReserva(Reserva miReserva) {
		this.miReserva = miReserva;
	}

	public boolean isCoberturaSelected() {
		return isCoberturaSelected;
	}

	public void setCoberturaSelected(boolean isCoberturaSelected) {
		this.isCoberturaSelected = isCoberturaSelected;
	}

	public void fillReserva() {
		Iterator<String> iteratorBoletas = misBoletas.keySet().iterator();
		Iterator<String> iteratorClientes = misClientes.keySet().iterator();
		String idCliente = "";
		String idBoleta = "";
		Puesto miPuesto;
		Cliente miClienteAsociado;
		Boleta miBoleta;
		while (iteratorBoletas.hasNext()) {
			idBoleta = iteratorBoletas.next();
			miPuesto = misBoletas.get(idBoleta).getMiPuesto();
			miPuesto.setMiEstado(EstadoPuesto.RESERVADO);
			miPuesto.setMiSeccionAsociada(miSeccion);
			idCliente = iteratorClientes.next();
			miClienteAsociado = misClientes.get(idCliente);
			miBoleta = misBoletas.get(idBoleta);
			miBoleta.setMiClienteAsociado(miCliente);
			miBoleta.setMiPuesto(miPuesto);
			miBoleta.setMiReservaAsociada(miReserva);
			try {
				miReserva.agregarBoleta(idBoleta, miPuesto, miClienteAsociado);
			} catch (BoletaRepetidaException e) {
				e.printStackTrace();
			} catch (BoletaNoExisteException e) {
				e.printStackTrace();
			} catch (LimiteExcedidoException e) {
				e.printStackTrace();
			}
		}
	}
}