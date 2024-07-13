package negocioImpl;

import java.sql.SQLException;
import java.util.ArrayList;

import datos.PrestamoDao;
import datosImpl.PrestamoDaoImpl;
import dominio.Cuenta;
import dominio.Movimiento;
import dominio.Prestamo;
import dominio.TipoMovimiento;
import negocio.CuentaNegocio;
import negocio.CuotaNegocio;
import negocio.MovimientoNegocio;
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
			movimiento.setConcepto("ID" + prestamo.getId());
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

}
