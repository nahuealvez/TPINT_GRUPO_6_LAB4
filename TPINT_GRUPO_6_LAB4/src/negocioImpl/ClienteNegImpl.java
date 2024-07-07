package negocioImpl;

import java.sql.SQLException;
import java.util.List;

import datos.ClienteDao;
import datos.UsuarioDao;
import datosImpl.ClienteDaoImpl;
import datosImpl.UsuarioDaoImpl;
import dominio.Cliente;
import dominio.Usuario;
import negocio.ClienteNegocio;
import Excepciones.ErrorMensajeException;

public class ClienteNegImpl implements ClienteNegocio{

	private ClienteDao cDao = new ClienteDaoImpl();
	private UsuarioDao uDao = new UsuarioDaoImpl();
	
	@Override
	public boolean crearCliente(Cliente cliente) throws ErrorMensajeException{
		
		boolean clienteCreado = false;

	    if (cDao.existeDni(cliente.getDni())) {
	        throw new ErrorMensajeException("El DNI: "+cliente.getDni()+", ya existe en la base de datos!.");
	    }

	    Usuario nuevoUsuario = new Usuario();
	    nuevoUsuario.setUsuario(cliente.getUsuario());
	    nuevoUsuario.setContrasenia(cliente.getContrasenia());
	    nuevoUsuario.setTipoUsuario(cliente.getTipoUsuario());

	    int idUsuario = uDao.crearUsuario(nuevoUsuario);

	    if (idUsuario != -1) {
	        cliente.setId(idUsuario);
	        clienteCreado = cDao.insert(cliente);
	    }

	    return clienteCreado;
	}
	
	@Override
	public List<Cliente> listarClientes() {
		return cDao.listarClientes();
	}

	@Override
	public Cliente listarClienteXId(int idCliente) {
		return cDao.listarClienteXId(idCliente);
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

	@Override
	public boolean modificarCliente(Cliente cliente) throws ErrorMensajeException{
		boolean clienteModificado = false;

	    if (!cDao.existeDni(cliente.getDni(), cliente.getId())) {
	        clienteModificado = cDao.update(cliente);
	    } else {
	        throw new ErrorMensajeException("El DNI ya está registrado para otro cliente.");
	    }
	    
	    return clienteModificado;
	}

	@Override
	public Cliente buscarClienteXDNI(String dni) {
		
		return cDao.buscarClienteXDNI(dni);
	}

	@Override
	public Cliente buscarClienteXidUsuario(int idUsuario) throws SQLException {
		try {
			return cDao.buscarClienteXidUsuario(idUsuario);
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}



}
