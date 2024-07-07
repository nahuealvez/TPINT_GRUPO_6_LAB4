package Excepciones;

public class ErrorMensajeException extends Exception {
	
	public ErrorMensajeException()
	{
	        super("El Cliente no pudo ser agregado!");
	}
	public ErrorMensajeException(String message) 
	{
	        super(message);
	}

}
