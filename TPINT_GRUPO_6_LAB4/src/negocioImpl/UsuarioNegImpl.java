package negocioImpl;

import Excepciones.ErrorUsuarioException;
import datos.UsuarioDao;
import datosImpl.UsuarioDaoImpl;
import dominio.Usuario;
import negocio.UsuarioNegocio;

public class UsuarioNegImpl implements UsuarioNegocio {

	private UsuarioDao usuarioDao = new UsuarioDaoImpl();
	
	@Override
	public int crearUsuario(Usuario usuario) {
		return usuarioDao.crearUsuario(usuario);
	}
	
	@Override
	public Usuario verificarUsuario(String usuario, String pass) throws ErrorUsuarioException {
		
		return usuarioDao.verificarUsuario(usuario, pass);
	}


}
