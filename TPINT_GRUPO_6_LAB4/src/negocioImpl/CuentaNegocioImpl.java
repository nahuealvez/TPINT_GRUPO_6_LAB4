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
		
		try {
			return cuentaDao.insert(cuenta);
			
		}catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ArrayList<Cuenta> cuentasXCliente(int idCliente) throws SQLException {
		
		try {
			return cuentaDao.cuentasXCliente(idCliente);
			
		}catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public boolean actualizarEstado(int idCuenta, boolean estado)  throws SQLException{
		
		try {
		
			return cuentaDao.actualizarEstado(idCuenta, estado);
			
		}catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public boolean verificarCbu(String cbu) throws SQLException {
		try {
			return cuentaDao.verificarCbu(cbu);
			
		}catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public String nuevoCbu() throws SQLException {
		
		try {
			return cuentaDao.nuevoCbu();
			
		}catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public boolean verificarEstado(int idCuenta) throws SQLException {
		
		try {
			return cuentaDao.verificarEstado(idCuenta);
			
		}catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public int contarCuentasActivas(List<Cuenta> listaCuentas) throws SQLException {
		
		try {
		
			return cuentaDao.contarCuentasActivas(listaCuentas);
		
		}catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public List<Cuenta> cuentasPorClienteActivas(int idCliente) throws SQLException {
		
		try {
			return cuentaDao.cuentasPorClienteActivas(idCliente);
			
		}catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public List<Cuenta> obtenerTodasLasCuentas() throws SQLException {
		
		try {
			return cuentaDao.obtenerTodasLasCuentas();
			
		}catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public List<Cuenta> obtenerTodasLasCuentasCorrientes() throws SQLException {
		
		try {
			return cuentaDao.obtenerTodasLasCuentasCorrientes();
			
		}catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public List<Cuenta> obtenerTodasLasCuentasAhorro() throws SQLException {
		
		try {
			return cuentaDao.obtenerTodasLasCuentasAhorro();
			
		}catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public List<Cuenta> cuentasActivas(int idCliente) throws SQLException {
		
		try {
			return cuentaDao.cuentasActivas(idCliente);
			
		}catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
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
		movimiento.setImporte(movimiento.getImporte());
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
				
				aux.setTipoMovimiento(movimiento.getTipoMovimiento());
	            aux.setCuenta(movimiento.getCuenta());
	            aux.setConcepto(movimiento.getConcepto());
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

	@Override
	public int obtenerNroCuentaActivaPorCBU(String cbu) throws SQLException {
		try {
			return cuentaDao.obtenerNroCuentaActivaPorCBU(cbu);
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public Cuenta obtenerUltimaCuenta(int idCliente) throws SQLException {
		try {
			Cuenta cuenta = cuentaDao.obtenerUltimaCuenta(idCliente);
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