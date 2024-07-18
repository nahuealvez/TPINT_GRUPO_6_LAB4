package datos;

import java.sql.SQLException;
import java.util.ArrayList;

import dominio.Cuota;

public interface CuotaDao {

	public boolean insert (Cuota cuota) throws SQLException;
	public ArrayList<Cuota> listarCuotasPrestamo (int idPrestamo) throws SQLException;
	public Cuota obtenerCuotaPorId (int idCuota) throws SQLException;
	public boolean registrarPago(Cuota cuota) throws SQLException;
}
