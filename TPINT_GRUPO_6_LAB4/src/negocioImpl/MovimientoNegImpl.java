package negocioImpl;

import java.sql.SQLException;
import java.util.ArrayList;

import datos.MovimientoDao;
import datosImpl.MovimientoDaoImpl;
import dominio.Movimiento;
import negocio.MovimientoNegocio;

public class MovimientoNegImpl implements MovimientoNegocio {
	private MovimientoDao movimientoDao = new MovimientoDaoImpl();

	@Override
	public ArrayList<Movimiento> listarMovimientosPorCuenta(int idCuenta) throws SQLException {
		try {
			return movimientoDao.listarMovimientosPorCuenta(idCuenta);
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public boolean agregarMovimiento(Movimiento movimiento) throws SQLException {
		try {
			return movimientoDao.agregarMovimiento(movimiento);
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}
	
	public int agregarMovimientoConDevolucionDeId(Movimiento movimiento) throws SQLException {
		try {
			return movimientoDao.agregarMovimientoConDevolucionDeId(movimiento);
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}
	
}
