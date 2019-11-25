package exceptions;

import java.io.IOException;

public class BoletaRepetidaException extends IOException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BoletaRepetidaException(String mensajeDeError) {
		super(mensajeDeError);
	}

}
