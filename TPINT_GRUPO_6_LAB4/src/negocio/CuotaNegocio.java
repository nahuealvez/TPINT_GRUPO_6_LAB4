package negocio;

import java.sql.SQLException;
import java.util.ArrayList;

import dominio.Cuota;
import dominio.Prestamo;

public interface CuotaNegocio {
	public ArrayList<Cuota> listarCuotasPorPrestamo (int idPrestamo) throws SQLException;
	public boolean generarCuotas (Prestamo prestamo) throws SQLException;
	public Cuota obtenerCuotaPorId (int idCuota) throws SQLException;
}
