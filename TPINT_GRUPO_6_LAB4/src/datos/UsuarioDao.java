package datos;

import dominio.Usuario;

public interface UsuarioDao {

	public boolean crearUsuario(Usuario usuario);
	public Usuario verificarUsuario(String usuario, String pass);
}
