package datos;

import java.util.List;

import dominio.Cuenta;

public interface CuentaDao {

	public boolean insert (Cuenta cuenta);
	public List<Cuenta> cuentasXCliente (int idCliente);
}
