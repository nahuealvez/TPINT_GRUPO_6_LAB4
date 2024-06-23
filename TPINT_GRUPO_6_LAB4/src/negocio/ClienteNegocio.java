package negocio;

import dominio.Cliente;

public interface ClienteNegocio {
	public boolean crearCliente (Cliente cliente);
	public boolean actualizarEstadoCliente (Cliente cliente);
}
