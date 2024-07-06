package datos;

import dominio.Prestamo;

public interface PrestamoDao {

	public boolean insert (Prestamo prestamo);
	public boolean updateEstado (int idPrestamo, boolean estadoAprobacion);
}
