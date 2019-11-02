package exceptions;

public class FechaExistenteException extends Exception {

	private static final long serialVersionUID = 1L;

	public FechaExistenteException(String mensaje) {
		super(mensaje);
	}
}
