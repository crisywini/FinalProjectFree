package exceptions;

import java.io.IOException;

public class LimiteExcedidoException extends IOException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public LimiteExcedidoException(String mensajeDeError) {
		super(mensajeDeError);
	}

}
