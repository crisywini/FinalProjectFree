package exceptions;

public class FechaExistente extends Exception {

	private static final long serialVersionUID = 1L;

	public FechaExistente(String mensaje) {
		super(mensaje);
	}
}
