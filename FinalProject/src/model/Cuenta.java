package model;

public class Cuenta 
{
	private double dineroTotal;
	private Cliente miClienteAsociado;

	public double getDineroTotal() {
		return dineroTotal;
	}

	public void setDineroTotal(double dineroTotal) {
		this.dineroTotal = dineroTotal;
	}

	public Cliente getMiClienteAsociado() {
		return miClienteAsociado;
	}

	public void setMiClienteAsociado(Cliente miClienteAsociado) {
		this.miClienteAsociado = miClienteAsociado;
	}

}
