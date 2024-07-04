package datos;

import java.sql.SQLException;
import java.util.List;

import dominio.Cliente;

public interface ClienteDao {

	public boolean insert (Cliente cliente);
	public List<Cliente> listarClientes();
	public Cliente listarClienteXId(int idCliente);
	public boolean update (Cliente cliente);
	public boolean existeDni (String dni);
	public boolean existeDni (String dni, int idUsuario);
	public Cliente buscarClienteXDNI(String dni);
	public Cliente buscarClienteXidUsuario(int idUsuario) throws SQLException;
}