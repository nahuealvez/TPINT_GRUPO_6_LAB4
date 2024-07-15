package Excepciones;

public class SinSaldoException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "No hay saldo disponible en la cuenta";
	}
	
}
