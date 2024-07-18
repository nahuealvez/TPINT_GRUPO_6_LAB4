package datos;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import dominio.Cuota;

public interface CuotaDao {

	public boolean insert (Cuota cuota) throws SQLException;
	public ArrayList<Cuota> listarCuotasPrestamo (int idPrestamo) throws SQLException;
	public Cuota obtenerCuotaPorId (int idCuota) throws SQLException;
	public boolean registrarPago(Cuota cuota) throws SQLException;
	
	public ArrayList<Cuota> listarCuotasPagadas(int idPrestamo)throws SQLException;
	public ArrayList<Cuota> listarCuotasPendientes(int idPrestamo)throws SQLException;
	public int contarCuotasPagadas(int idPrestamo)throws SQLException;
	public int contarCuotasPendientes(int idPrestamo)throws SQLException;
	public int contarCuotas(int idPrestamo)throws SQLException;

	public BigDecimal sumarCuotasPagadas(int idPrestamo)throws SQLException;
	public BigDecimal sumarCuotasPendientes(int idPrestamo)throws SQLException;
	public BigDecimal sumarCuotas(int idPrestamo)throws SQLException;
}
