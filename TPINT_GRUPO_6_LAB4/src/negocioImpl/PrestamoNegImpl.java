package negocioImpl;

import java.sql.SQLException;
import java.util.ArrayList;

import datos.PrestamoDao;
import datosImpl.PrestamoDaoImpl;
import dominio.Prestamo;
import negocio.PrestamoNegocio;

public class PrestamoNegImpl implements PrestamoNegocio{

	private PrestamoDao pDao = new PrestamoDaoImpl();
	
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

}
