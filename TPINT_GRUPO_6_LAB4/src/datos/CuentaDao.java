package datos;

import java.util.List;

import dominio.Cuenta;

public interface CuentaDao {

	public boolean insert (Cuenta cuenta);
	public List<Cuenta> cuentasXCliente (int idCliente);
	public boolean actualizarEstado(int idCuenta, boolean estado);
	public boolean verificarCbu (String cbu);
	public String nuevoCbu();
}
