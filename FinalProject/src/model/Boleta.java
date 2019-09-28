package model;

public class Boleta 
{
	private String id;
	private Puesto miPuesto;

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
}
