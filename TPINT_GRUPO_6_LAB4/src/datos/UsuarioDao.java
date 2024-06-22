package datos;

import dominio.Usuario;

public interface UsuarioDao {

	public int crearUsuario(Usuario usuario);
	public Usuario verificarUsuario(String usuario, String contrasenia);
}
