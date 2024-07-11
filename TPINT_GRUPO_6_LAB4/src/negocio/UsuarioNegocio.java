package negocio;

import Excepciones.ErrorUsuarioDesactivado;
import Excepciones.ErrorUsuarioException;
import dominio.Usuario;

public interface UsuarioNegocio {

	public int crearUsuario(Usuario usuario);
	public Usuario verificarUsuario(String usuario, String contrasenia) throws ErrorUsuarioException, ErrorUsuarioDesactivado ;
	public boolean actualizarContraseniaUsuario (int idUsurio, String contrasenia);
	public boolean actualizarEstadoUsuario(int idUsuario, boolean nuevoEstado);

}
