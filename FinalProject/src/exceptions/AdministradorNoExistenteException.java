package exceptions;

public class AdministradorNoExistenteException extends Exception {

	private static final long serialVersionUID = 1L;

	public AdministradorNoExistenteException(String mensajeDeError) {
		super(mensajeDeError);
	}
}
