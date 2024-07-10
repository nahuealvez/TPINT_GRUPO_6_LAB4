package negocioImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datos.CuentaDao;
import datosImpl.CuentaDaoImpl;
import dominio.Cuenta;
import dominio.Movimiento;
import negocio.CuentaNegocio;
import negocio.MovimientoNegocio;

public class CuentaNegocioImpl implements CuentaNegocio  {
	

	private CuentaDao cuentaDao= new CuentaDaoImpl();
	
	@Override
	public boolean insert(Cuenta cuenta) throws SQLException {
		
		return cuentaDao.insert(cuenta);
	}

	@Override
	public List<Cuenta> cuentasXCliente(int idCliente) throws SQLException {
		
		return cuentaDao.cuentasXCliente(idCliente);
	}

	@Override
	public boolean actualizarEstado(int idCuenta, boolean estado)  throws SQLException{
		
		return cuentaDao.actualizarEstado(idCuenta, estado);
	}

	@Override
	public boolean verificarCbu(String cbu) throws SQLException {
		
		return cuentaDao.verificarCbu(cbu);
	}

	@Override
	public String nuevoCbu() throws SQLException {
		
		return cuentaDao.nuevoCbu();
	}

	@Override
	public boolean verificarEstado(int idCuenta) throws SQLException {
		
		return cuentaDao.verificarEstado(idCuenta);
	}

	@Override
	public int contarCuentasActivas(List<Cuenta> listaCuentas) throws SQLException {
		
		return cuentaDao.contarCuentasActivas(listaCuentas);
	}

	@Override
	public List<Cuenta> cuentasPorClienteActivas(int idCliente) throws SQLException {
		return cuentaDao.cuentasPorClienteActivas(idCliente);
	}

	@Override
	public List<Cuenta> obtenerTodasLasCuentas() throws SQLException {
		return cuentaDao.obtenerTodasLasCuentas();
	}

	@Override
	public List<Cuenta> obtenerTodasLasCuentasCorrientes() throws SQLException {
		return cuentaDao.obtenerTodasLasCuentasCorrientes();
	}

	@Override
	public List<Cuenta> obtenerTodasLasCuentasAhorro() throws SQLException {
		return cuentaDao.obtenerTodasLasCuentasAhorro();
	}

	@Override
	public List<Cuenta> cuentasActivas(int idCliente) throws SQLException {
		
		return cuentaDao.cuentasActivas(idCliente);
	}

	@Override
	public boolean acreditar(Cuenta cuenta, Movimiento movimiento) throws SQLException {
		MovimientoNegocio movimientoNegocio = new MovimientoNegImpl();
		try {
			if (movimientoNegocio.agregarMovimiento(movimiento)) {
				if (cuentaDao.afectarSaldo(cuenta.getId(), movimiento.getImporte())) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public boolean debitar(Cuenta cuenta, Movimiento movimiento) throws SQLException {
		MovimientoNegocio movimientoNegocio = new MovimientoNegImpl();
		try {
			if (cuentaDao.verificarSaldo(cuenta, movimiento.getImporte())) {
				if (movimientoNegocio.agregarMovimiento(movimiento)) {
					if (cuentaDao.afectarSaldo(cuenta.getId(), movimiento.getImporte().negate())) {
						return true;
					}
					else {
						return false;
					}
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}
	
	@Override
	public List<Cuenta> CuentasxClienteYEstado(int idCliente, boolean estado) throws SQLException {

		return cuentaDao.CuentasxClienteYEstado(idCliente, estado);
	}
}