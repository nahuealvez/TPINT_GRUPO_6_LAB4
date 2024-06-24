package datos;

import java.util.List;

import dominio.Cliente;

public interface ClienteDao {

	public boolean insert (Cliente cliente);
	public List<Cliente> listarClientes();
	public boolean update (Cliente cliente);
	public boolean existeDni (String dni);
	public boolean existeDni (String dni, int idUsuario);
}