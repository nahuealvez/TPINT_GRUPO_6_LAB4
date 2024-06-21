package negocioImpl;

import datos.UsuarioDao;
import datosImpl.UsuarioDaoImpl;
import dominio.Usuario;
import negocio.UsuarioNegocio;

public class UsuarioNegImpl implements UsuarioNegocio {

	private UsuarioDao usuarioDao = new UsuarioDaoImpl();
	
	@Override
	public boolean crearUsuario(Usuario usuario) {
		return usuarioDao.crearUsuario(usuario);
	}
	
	@Override
	public Usuario verificarUsuario(String usuario, String pass) {
		
		return usuarioDao.verificarUsuario(usuario, pass);
	}


}
