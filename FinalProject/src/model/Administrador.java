package model;

public class Administrador extends Persona
{
	private String email;
	private String contrasenia;
	
	public Administrador() 
	{
		// Un constructor vacio alv
	}
	
	// :v:v:v
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

}
