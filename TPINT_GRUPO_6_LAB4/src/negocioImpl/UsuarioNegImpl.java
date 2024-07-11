package negocioImpl;

import Excepciones.ErrorUsuarioDesactivado;
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
	public Usuario verificarUsuario(String usuario, String pass) throws ErrorUsuarioException, ErrorUsuarioDesactivado {
		
		return usuarioDao.verificarUsuario(usuario, pass);
	}

	@Override
	public boolean actualizarContraseniaUsuario(int idUsurio, String contrasenia) {
		return usuarioDao.actualizarContraseniaUsuario(idUsurio, contrasenia);

	}

	@Override
	public boolean actualizarEstadoUsuario(int idUsuario, boolean nuevoEstado) {
		return usuarioDao.actualizarEstadoUsuario(idUsuario, nuevoEstado);
	}

}
