package model;

import java.io.Serializable;

public class Cliente extends Persona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String direccion;
	private String email;
	private Cuenta miCuentaAsociada;
	private Date miFechaDeNacimiento;
	private String ciudadDeResidencia;
	private String contrasenia;
	private EstratoSocioeconomico miEstrato;
	private EstadoCivil miEstadoCivil;
	private NivelDeEstudio miNivelDeEstudio;
	private Boleta miBoletaAsociada;

	/**
	 * Constructor vacio
	 */
	public Cliente() {
		super();
	}

	/**
	 * Constructor simple
	 * 
	 * @param nombre
	 * @param apellido
	 * @param id
	 * @param miGenero
	 */
	public Cliente(String nombre, String apellido, String id, Genero miGenero) {
		super(nombre, apellido, id, miGenero);
	}

	/**
	 * Constructor completo de la clase Cliente
	 * 
	 * @param nombre              del cliente
	 * @param apellido            del cliente
	 * @param id                  del cliente
	 * @param miGenero            Genero del cliente
	 * @param direccion           del cliente
	 * @param email               del cliente
	 * @param miCuentaAsociada    Cuenta del cliente
	 * @param miFechaDeNacimiento fecha de nacimiento del cliente
	 * @param ciudadDeResidencia  del cliente
	 * @param miEstrato           Estrato socioeconomico del cliente
	 * @param miEstadoCivil       estado civil del cliente
	 * @param miNivelDeEstudio    Nivel de estudio del cliente
	 */
	public Cliente(String nombre, String apellido, String id, Genero miGenero, String direccion, String email,
			Cuenta miCuentaAsociada, Boleta miBoletaAsociada,Date miFechaDeNacimiento, String ciudadDeResidencia,
			EstratoSocioeconomico miEstrato, EstadoCivil miEstadoCivil, NivelDeEstudio miNivelDeEstudio, String contrasenia) {
		super(nombre, apellido, id, miGenero);
		this.direccion = direccion;
		this.email = email;
		this.miCuentaAsociada = miCuentaAsociada;
		this.miFechaDeNacimiento = miFechaDeNacimiento;
		this.ciudadDeResidencia = ciudadDeResidencia;
		this.miEstrato = miEstrato;
		this.miEstadoCivil = miEstadoCivil;
		this.miNivelDeEstudio = miNivelDeEstudio;
		this.contrasenia = contrasenia;
		this.miBoletaAsociada = miBoletaAsociada;
		getMiCuentaAsociada().setMiClienteAsociado(this);
		getMiBoletaAsociada().setMiClienteAsociado(this);
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Cuenta getMiCuentaAsociada() {
		return miCuentaAsociada;
	}

	public void setMiCuentaAsociada(Cuenta miCuentaAsociada) {
		this.miCuentaAsociada = miCuentaAsociada;
	}

	public Date getMiFechaDeNacimiento() {
		return miFechaDeNacimiento;
	}

	public void setMiFechaDeNacimiento(Date miFechaDeNacimiento) {
		this.miFechaDeNacimiento = miFechaDeNacimiento;
	}

	public String getCiudadDeResidencia() {
		return ciudadDeResidencia;
	}

	public void setCiudadDeResidencia(String ciudadDeResidencia) {
		this.ciudadDeResidencia = ciudadDeResidencia;
	}

	public EstratoSocioeconomico getMiEstrato() {
		return miEstrato;
	}

	public void setMiEstrato(EstratoSocioeconomico miEstrato) {
		this.miEstrato = miEstrato;
	}

	public NivelDeEstudio getMiNivelDeEstudio() {
		return miNivelDeEstudio;
	}

	public void setMiNivelDeEstudio(NivelDeEstudio miNivelDeEstudio) {
		this.miNivelDeEstudio = miNivelDeEstudio;
	}

	public EstadoCivil getMiEstadoCivil() {
		return miEstadoCivil;
	}

	public void setMiEstadoCivil(EstadoCivil miEstadoCivil) {
		this.miEstadoCivil = miEstadoCivil;
	}
	@Override
	public String toString() {
		String info = "["+getNombre()+" "+getMiCuentaAsociada().getId()+" "+miBoletaAsociada.getId()+"]";
		return info;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Boleta getMiBoletaAsociada() {
		return miBoletaAsociada;
	}

	public void setMiBoletaAsociada(Boleta miBoletaAsociada) {
		this.miBoletaAsociada = miBoletaAsociada;
	}
}