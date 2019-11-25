package exceptions;

import java.io.IOException;

public class ClienteNoExistenteException extends IOException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ClienteNoExistenteException(String mensajeDeError) {
		super(mensajeDeError);
	}

}
