package model;

public class Cliente extends Persona
{
	private String direccion;
	private String email;
	private Cuenta miCuentaAsociada;
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
}