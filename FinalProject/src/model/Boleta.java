package model;

public class Boleta {
	private String id;
	private Puesto miPuesto;
	private Cliente miClienteAsociado;
	private double valor;
	private Reserva miReservaAsociada;

	/**
	 * Metodo constructor vacio de la clase Boleta
	 */
	public Boleta() {
		this("###", new Puesto(), new Cliente());
	}

	/**
	 * Metodo constructor de la clase Boleta
	 * 
	 * @param id                de la boleta
	 * @param miPuesto          de la boleta
	 * @param miClienteAsociado cliente asociado a la boleta
	 */
	public Boleta(String id, Puesto miPuesto, Cliente miClienteAsociado) {
		this.id = id;
		this.miPuesto = miPuesto;
		this.miClienteAsociado = miClienteAsociado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Puesto getMiPuesto() {
		return miPuesto;
	}

	public void setMiPuesto(Puesto miPuesto) {
		this.miPuesto = miPuesto;
	}

	public Cliente getMiClienteAsociado() {
		return miClienteAsociado;
	}

	public void setMiClienteAsociado(Cliente miClienteAsociado) {
		this.miClienteAsociado = miClienteAsociado;
	}

	public double getValor() {
		if (getMiPuesto().getMiSeccionAsociada().getMiTipo() == TipoSeccion.PLATEA_1)
			setValor(380000);
		if (getMiPuesto().getMiSeccionAsociada().getMiTipo() == TipoSeccion.PLATEA_2)
			setValor(340000);
		if (getMiPuesto().getMiSeccionAsociada().getMiTipo() == TipoSeccion.PLATEA_3)
			setValor(350000);
		if (getMiPuesto().getMiSeccionAsociada().getMiTipo() == TipoSeccion.PRIMER_PISO_1)
			setValor(280000);
		if (getMiPuesto().getMiSeccionAsociada().getMiTipo() == TipoSeccion.PRIMER_PISO_2)
			setValor(230000);
		if (getMiPuesto().getMiSeccionAsociada().getMiTipo() == TipoSeccion.PRIMER_PISO_3)
			setValor(190000);
		if (getMiPuesto().getMiSeccionAsociada().getMiTipo() == TipoSeccion.SEGUNDO_PISO_1)
			setValor(150000);
		if (getMiPuesto().getMiSeccionAsociada().getMiTipo() == TipoSeccion.SEGUNDO_PISO_2)
			setValor(130000);
		if (getMiPuesto().getMiSeccionAsociada().getMiTipo() == TipoSeccion.SEGUNDO_PISO_3)
			setValor(102000);
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Reserva getMiReservaAsociada() {
		return miReservaAsociada;
	}

	public void setMiReservaAsociada(Reserva miReservaAsociada) {
		this.miReservaAsociada = miReservaAsociada;
	}

	@Override
	public String toString() {
		String info = "[id: " + id + " valor: " + getValor() + "]";
		return info;
	}
}
