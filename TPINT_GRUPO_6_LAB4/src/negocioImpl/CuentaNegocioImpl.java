package negocioImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Excepciones.SinSaldoException;
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
	public boolean verificarSaldo(int idCuenta, BigDecimal importe) throws SQLException {
		try {
			Cuenta cuenta = obtenerCuentaPorId(idCuenta);
			BigDecimal resta = cuenta.getSaldo().subtract(importe);
			
			if (resta.compareTo(BigDecimal.ZERO) >= 0) {
				return true;
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
	public boolean acreditar(int idCuenta, Movimiento movimiento) throws SQLException {
		MovimientoNegocio movimientoNegocio = new MovimientoNegImpl();
		try {
			if (movimientoNegocio.agregarMovimiento(movimiento)) {
				if (cuentaDao.afectarSaldo(idCuenta, movimiento.getImporte())) {
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
	public boolean debitar(int idCuenta, Movimiento movimiento) throws Exception {
		MovimientoNegocio movimientoNegocio = new MovimientoNegImpl();
		try {
			if (verificarSaldo(idCuenta, movimiento.getImporte())) {
				Movimiento aux = new Movimiento();
				aux = movimiento;
				aux.setImporte(movimiento.getImporte().negate());
				if (movimientoNegocio.agregarMovimiento(aux)) {
					if (cuentaDao.afectarSaldo(idCuenta, aux.getImporte())) {
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
				throw new SinSaldoException();
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

	@Override
	public Cuenta obtenerCuentaPorId(int idCuenta) throws SQLException {
		try {
			Cuenta cuenta = cuentaDao.obtenerCuentaPorId(idCuenta);
			return cuenta;
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}
}