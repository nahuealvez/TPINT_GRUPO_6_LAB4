package negocio;

import Excepciones.ErrorUsuarioException;
import dominio.Usuario;

public interface UsuarioNegocio {

	public int crearUsuario(Usuario usuario);
	public Usuario verificarUsuario(String usuario, String contrasenia) throws ErrorUsuarioException ;
	public boolean actualizarContraseniaUsuario (int idUsurio, String contrasenia);
	public boolean actualizarEstadoUsuario(int idUsuario, boolean nuevoEstado);

}
