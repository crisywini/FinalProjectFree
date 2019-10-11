package exceptions;

import java.io.IOException;

public class ClienteRepetidoException extends IOException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 public ClienteRepetidoException(String mensajeDeError) {
		super(mensajeDeError);
	}
}
