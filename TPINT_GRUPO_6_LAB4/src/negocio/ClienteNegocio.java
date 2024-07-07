package negocio;

import java.sql.SQLException;
import java.util.List;

import Excepciones.ErrorMensajeException;
import dominio.Cliente;

public interface ClienteNegocio {
	public boolean crearCliente (Cliente cliente)throws ErrorMensajeException;
	public List<Cliente> listarClientes();
	public Cliente listarClienteXId(int idCliente);
	public boolean modificarCliente (Cliente cliente)throws ErrorMensajeException;
	public boolean actualizarEstadoCliente (Cliente cliente);
	public Cliente buscarClienteXDNI(String dni);
	public Cliente buscarClienteXidUsuario(int idUsuario) throws SQLException;
}