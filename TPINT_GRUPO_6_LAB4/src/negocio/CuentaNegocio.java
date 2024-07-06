package negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Cuenta;

public interface CuentaNegocio {

	public boolean insert (Cuenta cuenta);
	public List<Cuenta> cuentasXCliente (int idCliente);
	public boolean actualizarEstado(int idCuenta, boolean estado);
	public boolean verificarCbu (String cbu);
	public String nuevoCbu();
	public boolean verificarEstado (int idCuenta);
	public int contarCuentasActivas(List<Cuenta> listaCuentas);
	public List<Cuenta> cuentasPorClienteActivas(int idCliente) throws SQLException;
}
