package exceptions;

public class AdministradorRepetidoException extends Exception {

	private static final long serialVersionUID = 1L;

	public AdministradorRepetidoException(String mensajeDeError) {
		super(mensajeDeError);
	}

}
