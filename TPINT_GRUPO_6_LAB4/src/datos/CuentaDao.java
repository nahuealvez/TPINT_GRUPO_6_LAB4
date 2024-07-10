package datos;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Cuenta;
import dominio.Movimiento;

public interface CuentaDao {

	public boolean insert (Cuenta cuenta)throws SQLException;
	public List<Cuenta> cuentasXCliente (int idCliente)throws SQLException;
	public boolean actualizarEstado(int idCuenta, boolean estado)throws SQLException;
	public boolean verificarCbu (String cbu)throws SQLException;
	public String nuevoCbu()throws SQLException;
	public boolean verificarEstado (int idCuenta)throws SQLException;
	public int contarCuentasActivas (List<Cuenta> listaCuentas)throws SQLException;
	public List<Cuenta> cuentasPorClienteActivas(int idCliente) throws SQLException;
	public List<Cuenta> obtenerTodasLasCuentas() throws SQLException;
	public List<Cuenta> obtenerTodasLasCuentasCorrientes() throws SQLException;
	public List<Cuenta> obtenerTodasLasCuentasAhorro() throws SQLException;
	public List<Cuenta> cuentasActivas(int idCliente)throws SQLException;
	public boolean verificarSaldo(Cuenta cuenta, BigDecimal importe);
	public boolean afectarSaldo(int idCuenta, BigDecimal importe) throws SQLException;
	public List<Cuenta> CuentasxClienteYEstado(int idCliente, boolean estado)throws SQLException;
}
