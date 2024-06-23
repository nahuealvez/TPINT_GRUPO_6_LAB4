package Excepciones;

public class ErrorUsuarioException extends Exception{
	
	public ErrorUsuarioException() {}

	@Override
	public String getMessage() {
		
		return "Usuario o contraseña incorrectos";
	}
	
}
