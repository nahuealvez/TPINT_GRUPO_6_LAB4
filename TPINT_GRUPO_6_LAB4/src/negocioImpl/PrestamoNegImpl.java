package negocioImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import datos.PrestamoDao;
import datosImpl.PrestamoDaoImpl;
import dominio.Movimiento;
import dominio.Prestamo;
import dominio.TipoMovimiento;
import negocio.CuentaNegocio;
import negocio.CuotaNegocio;
import negocio.PrestamoNegocio;

public class PrestamoNegImpl implements PrestamoNegocio{

	private PrestamoDao pDao = new PrestamoDaoImpl();
	private CuotaNegocio cuotaNeg = new CuotaNegImpl();
	private CuentaNegocio cuentaNeg = new CuentaNegocioImpl();
	
	@Override
	public boolean crearPrestamo(Prestamo prestamo) throws SQLException{
		try {
			return pDao.insert(prestamo);
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public boolean actualizarEstadoSolicitud(int idPrestamo, Boolean estadoSolicitud) throws SQLException{
		try {
			return pDao.updateEstado(idPrestamo, estadoSolicitud);
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ArrayList<Prestamo> listarPrestamosXCliente(int idCliente) throws SQLException {
		try {
			return pDao.listarPrestamosXCliente(idCliente);
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ArrayList<Prestamo> listarSolicitudesPrestamos() throws SQLException {
		try {
			return pDao.listarSolicitudesPrestamos();
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public boolean aprobarPrestamo(Prestamo prestamo) throws SQLException {
		try {
			boolean aprobacionExitosa = false;
			// CREACION OBJETOS NECESARIOS
			Movimiento movimiento = new Movimiento();
			TipoMovimiento tipoMovimiento = new TipoMovimiento();
			
			tipoMovimiento.setId(2); // ID MOV ALTA PRESTAMO
			movimiento.setTipoMovimiento(tipoMovimiento);
			movimiento.setConcepto("Acreditación de préstamo aprobado | Préstamo #" + prestamo.getId());
			movimiento.setCuenta(prestamo.getCuenta());
			movimiento.setImporte(prestamo.getImportePedido());
			
			boolean generacionCuotas = cuotaNeg.generarCuotas(prestamo);
			boolean acreditarPrestamo = cuentaNeg.acreditar(prestamo.getCuenta().getId(), movimiento);
			boolean actualizarEstado = pDao.updateEstado(prestamo.getId(), true);
			if(generacionCuotas && actualizarEstado && acreditarPrestamo) {
				aprobacionExitosa = true;
			}
			return aprobacionExitosa;
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public Prestamo obtenerPrestamoPorId(int idPrestamo) throws SQLException {
		try {
			return pDao.obtenerPrestamoPorId(idPrestamo);
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ArrayList<Prestamo> listarTodosLosPrestamos() throws SQLException {
		try {
			return pDao.listarTodosLosPrestamos();
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ArrayList<Prestamo> listarTodosLosPrestamosAprobados() throws SQLException {
		try {
			return pDao.listarTodosLosPrestamosAprobados();
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ArrayList<Prestamo> listarTodosLosPrestamosRechazados() throws SQLException {
		try {
			return pDao.listarTodosLosPrestamosRechazados();
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ArrayList<Prestamo> listarTodosLosPrestamosEnEvaluacio() throws SQLException {
		try {
			return pDao.listarTodosLosPrestamosEnEvaluacio();
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public int contarPrestamosAprobados() throws SQLException {
		try {
			return pDao.contarPrestamosAprobados();
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public int contarPrestamosRechazados() throws SQLException {
		try {
			return pDao.contarPrestamosRechazados();
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public int contarPrestamosEnEvaluacion() throws SQLException {
		try {
			return pDao.contarPrestamosEnEvaluacion();
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public BigDecimal sumarPrestamosAprobados() throws SQLException {
		try {
			return pDao.sumarPrestamosAprobados();
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public BigDecimal sumarPrestamosRechazados() throws SQLException {
		try {
			return pDao.sumarPrestamosRechazados();
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public BigDecimal sumarPrestamosEnEvaluacion() throws SQLException {
		try {
			return pDao.sumarPrestamosEnEvaluacion();
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

}
