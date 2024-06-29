package negocio;

import java.util.List;

import dominio.Cuenta;

public interface CuentaNegocio {

	public boolean insert (Cuenta cuenta);
	public List<Cuenta> cuentasXCliente (int idCliente);
}
