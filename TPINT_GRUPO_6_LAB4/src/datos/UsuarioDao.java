package datos;

import dominio.Usuario;

public interface UsuarioDao {

	public Usuario verificarUsuario(String usuario, String pass);
}
