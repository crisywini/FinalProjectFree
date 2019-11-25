package exceptions;

import java.io.IOException;

public class ReservaRepetidaException extends IOException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ReservaRepetidaException(String mensajeDeError) {
		super(mensajeDeError);
	}

}
