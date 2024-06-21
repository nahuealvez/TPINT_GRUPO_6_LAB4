package negocio;

import dominio.Usuario;

public interface UsuarioNegocio {

	public boolean crearUsuario(Usuario usuario);
	public Usuario verificarUsuario(String usuario, String pass);
}
