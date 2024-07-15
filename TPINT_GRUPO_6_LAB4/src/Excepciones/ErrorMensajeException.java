package Excepciones;

public class ErrorMensajeException extends Exception {

	private static final long serialVersionUID = 1L;
	public ErrorMensajeException()
	{
	        super("El Cliente no pudo ser agregado!");
	}
	public ErrorMensajeException(String message) 
	{
	        super(message);
	}

}
