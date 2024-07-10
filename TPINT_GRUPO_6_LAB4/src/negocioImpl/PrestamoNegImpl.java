package negocioImpl;

import java.sql.SQLException;
import java.util.ArrayList;

import datos.PrestamoDao;
import datosImpl.PrestamoDaoImpl;
import dominio.Prestamo;
import negocio.CuotaNegocio;
import negocio.PrestamoNegocio;

public class PrestamoNegImpl implements PrestamoNegocio{

	private PrestamoDao pDao = new PrestamoDaoImpl();
	private CuotaNegocio cuotaNeg = new CuotaNegImpl();
	
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
			boolean generacionCuotas = cuotaNeg.generarCuotas(prestamo);
			boolean actualizarEstado = pDao.updateEstado(prestamo.getId(), true);
			if(generacionCuotas && actualizarEstado) {
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
