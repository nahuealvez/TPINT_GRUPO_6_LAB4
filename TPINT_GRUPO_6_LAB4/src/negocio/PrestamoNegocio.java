package negocio;

import dominio.Prestamo;

public interface PrestamoNegocio {
	public boolean crearPrestamo (Prestamo prestamo);
	public boolean actualizarEstadoSolicitud (int idPrestamo, boolean estadoSolicitud);
}
