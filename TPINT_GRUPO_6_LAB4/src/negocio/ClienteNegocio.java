package negocio;

import java.util.List;

import dominio.Cliente;

public interface ClienteNegocio {
	public boolean crearCliente (Cliente cliente);
	public List<Cliente> listarClientes();
	public Cliente listarClienteXId(int idCliente);
	public boolean modificarCliente (Cliente cliente);
	public boolean actualizarEstadoCliente (Cliente cliente);
}