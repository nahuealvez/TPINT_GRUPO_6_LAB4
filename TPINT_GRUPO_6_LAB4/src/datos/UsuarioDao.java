package datos;

import Excepciones.ErrorUsuarioException;
import dominio.Usuario;


public interface UsuarioDao {

	public int crearUsuario(Usuario usuario);
	public boolean actualizarEstadoUsuario (int idUsuario, boolean nuevoEstado);
	public Usuario verificarUsuario(String usuario, String contrasenia) throws ErrorUsuarioException ;
}
