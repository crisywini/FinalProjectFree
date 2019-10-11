package exceptions;

public class DineroNoValidoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DineroNoValidoException(String mensajeDeError) {
		super(mensajeDeError);
	}

}
