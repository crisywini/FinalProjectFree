package exceptions;

import java.io.IOException;

public class BoletaNoExisteException extends IOException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BoletaNoExisteException(String mensajeDeError) {
		super(mensajeDeError);
	}

}
