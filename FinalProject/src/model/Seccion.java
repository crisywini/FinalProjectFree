package model;

public class Seccion 
{
	private Puesto[][] misPuestos;
	private Escenario miEscenarioAsociado;

	public Puesto[][] getMisPuestos() {
		return misPuestos;
	}

	public void setMisPuestos(Puesto[][] misPuestos) {
		this.misPuestos = misPuestos;
	}

	public Escenario getMiEscenarioAsociado() {
		return miEscenarioAsociado;
	}

	public void setMiEscenarioAsociado(Escenario miEscenarioAsociado) {
		this.miEscenarioAsociado = miEscenarioAsociado;
	}

}
