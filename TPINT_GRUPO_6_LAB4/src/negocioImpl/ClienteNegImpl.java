package negocioImpl;

import datos.ClienteDao;
import datos.UsuarioDao;
import datosImpl.ClienteDaoImpl;
import datosImpl.UsuarioDaoImpl;
import dominio.Cliente;
import dominio.Usuario;
import negocio.ClienteNegocio;

public class ClienteNegImpl implements ClienteNegocio{

	private ClienteDao cDao = new ClienteDaoImpl();
	private UsuarioDao uDao = new UsuarioDaoImpl();
	
	@Override
	public boolean crearCliente(Cliente cliente) {
		
		boolean clienteCreado = false;
		
		if(!cDao.existeDni(cliente.getDni())) {
			Usuario nuevoUsuario = new Usuario();
			nuevoUsuario.setUsuario(cliente.getUsuario());
			nuevoUsuario.setContrasenia(cliente.getContrasenia());
			nuevoUsuario.setTipoUsuario(cliente.getTipoUsuario());
			
			int idUsuario = uDao.crearUsuario(nuevoUsuario);
			
			if(idUsuario != -1) 
			{
				cliente.setId(idUsuario);
				clienteCreado = cDao.insert(cliente);
			}
		}
		return clienteCreado;
	}

	@Override
	public boolean actualizarEstadoCliente(Cliente cliente) {
		boolean actualizacionExitosa = false;
		
		if(cliente.getEstado()) 
		{
			actualizacionExitosa = uDao.actualizarEstadoUsuario(cliente.getId(), false);
		}else {
			actualizacionExitosa = uDao.actualizarEstadoUsuario(cliente.getId(), true);
		}
		
		return actualizacionExitosa;
	}
}
