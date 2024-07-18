package negocio;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import dominio.Cuota;
import dominio.Prestamo;

public interface CuotaNegocio {
	public ArrayList<Cuota> listarCuotasPorPrestamo (int idPrestamo) throws SQLException;
	public boolean generarCuotas (Prestamo prestamo) throws SQLException;
	public Cuota obtenerCuotaPorId (int idCuota) throws SQLException;
	public boolean registrarPago(int idCuenta, Cuota cuota) throws Exception, SQLException;
	
	public ArrayList<Cuota> listarCuotasPagadas(int idPrestamo)throws SQLException;
	public ArrayList<Cuota> listarCuotasPendientes(int idPrestamo)throws SQLException;
	public int contarCuotasPagadas(int idPrestamo)throws SQLException;
	public int contarCuotasPendientes(int idPrestamo)throws SQLException;
	public int contarCuotas(int idPrestamo)throws SQLException;

	public BigDecimal sumarCuotasPagadas(int idPrestamo)throws SQLException;
	public BigDecimal sumarCuotasPendientes(int idPrestamo)throws SQLException;
	public BigDecimal sumarCuotas(int idPrestamo)throws SQLException;
}
