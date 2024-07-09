package negocio;

import java.sql.SQLException;
import java.util.ArrayList;

import dominio.Prestamo;

public interface PrestamoNegocio {
	public boolean crearPrestamo (Prestamo prestamo)throws SQLException;
	public boolean actualizarEstadoSolicitud (int idPrestamo, Boolean estadoSolicitud)throws SQLException;
	public ArrayList<Prestamo> listarPrestamosXCliente(int idCliente) throws SQLException;
	public ArrayList<Prestamo> listarSolicitudesPrestamos() throws SQLException;
	public Prestamo obtenerPrestamoPorId (int idPrestamo) throws SQLException;
	public boolean aprobarPrestamo (Prestamo prestamo) throws SQLException;
}
