package exceptions;

import java.io.IOException;

public class ReservaNoExisteException extends IOException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ReservaNoExisteException(String mensajeDeError) {
		super(mensajeDeError);
	}

}
