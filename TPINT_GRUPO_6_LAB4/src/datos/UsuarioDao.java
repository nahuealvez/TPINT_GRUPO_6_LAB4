package datos;

import Excepciones.ErrorUsuarioDesactivado;
import Excepciones.ErrorUsuarioException;
import dominio.Usuario;


public interface UsuarioDao {

	public int crearUsuario(Usuario usuario);
	public boolean actualizarEstadoUsuario (int idUsuario, boolean nuevoEstado);
	public Usuario verificarUsuario(String usuario, String contrasenia) throws ErrorUsuarioException, ErrorUsuarioDesactivado ;
	public boolean actualizarContraseniaUsuario (int idUsurio, String contrasenia);

}
