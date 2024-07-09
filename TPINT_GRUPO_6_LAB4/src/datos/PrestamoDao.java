package datos;

import java.sql.SQLException;
import java.util.ArrayList;

import dominio.Prestamo;

public interface PrestamoDao {

	public boolean insert (Prestamo prestamo)throws SQLException;
	public boolean updateEstado (int idPrestamo, Boolean estadoAprobacion)throws SQLException;
	public ArrayList<Prestamo> listarPrestamosXCliente(int idCliente) throws SQLException;
	public ArrayList<Prestamo> listarSolicitudesPrestamos() throws SQLException;
	public Prestamo obtenerPrestamoPorId (int idPrestamo) throws SQLException;
}
