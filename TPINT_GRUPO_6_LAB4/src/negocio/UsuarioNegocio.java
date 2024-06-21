package negocio;

import dominio.Usuario;

public interface UsuarioNegocio {

	public Usuario verificarUsuario(String usuario, String pass);
}
