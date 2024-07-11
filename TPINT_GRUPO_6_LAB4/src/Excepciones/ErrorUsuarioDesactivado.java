package Excepciones;

public class ErrorUsuarioDesactivado extends Exception{
	

	private static final long serialVersionUID = 1L;

	public ErrorUsuarioDesactivado() {};
	
	@Override
	public String getMessage() {
		
		return "Usuario desactivado";
	}
}
