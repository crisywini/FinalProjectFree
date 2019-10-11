package proobs;

import exceptions.AdministradorRepetidoException;
import model.Espectaculo;
import model.Genero;

public class Main {

	public static void main(String[] args) {

		Espectaculo espe = new Espectaculo();
		try {
			espe.agregarAdministrador("tati", "mora", "1007687701", Genero.MUJER, "tati@gmail.com", "tatiaa");
		} catch (AdministradorRepetidoException e) {
			e.printStackTrace();
		}
	}

}
