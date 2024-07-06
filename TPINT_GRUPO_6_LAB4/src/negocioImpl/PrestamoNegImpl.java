package negocioImpl;

import datos.PrestamoDao;
import datosImpl.PrestamoDaoImpl;
import dominio.Prestamo;
import negocio.PrestamoNegocio;

public class PrestamoNegImpl implements PrestamoNegocio{

	private PrestamoDao pDao = new PrestamoDaoImpl();
	
	@Override
	public boolean crearPrestamo(Prestamo prestamo) {
		return pDao.insert(prestamo);
	}

	@Override
	public boolean actualizarEstadoSolicitud(int idPrestamo, boolean estadoSolicitud) {
		return pDao.updateEstado(idPrestamo, estadoSolicitud);
	}

}
