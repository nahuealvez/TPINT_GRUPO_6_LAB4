package negocio;

import java.util.List;

import dominio.Cuenta;

public interface CuentaNegocio {

	public boolean insert (Cuenta cuenta);
	public List<Cuenta> cuentasXCliente (int idCliente);
	public boolean actualizarEstado(int idCuenta, boolean estado);
	public boolean verificarCbu (String cbu);
	public String nuevoCbu();
	public boolean verificarEstado (int idCuenta);
	public int contarCuentas(List<Cuenta> listaCuentas);
}
