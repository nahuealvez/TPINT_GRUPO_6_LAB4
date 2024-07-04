package Excepciones;

public class ErrorUsuarioException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public ErrorUsuarioException() {}

	@Override
	public String getMessage() {
		
		return "Usuario o contraseña incorrectos";
	}
	
}
