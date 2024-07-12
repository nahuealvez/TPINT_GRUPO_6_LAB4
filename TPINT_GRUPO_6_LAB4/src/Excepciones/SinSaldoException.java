package Excepciones;

public class SinSaldoException extends Exception {

	@Override
	public String getMessage() {
		return "No hay saldo disponible en la cuenta";
	}
	
}
