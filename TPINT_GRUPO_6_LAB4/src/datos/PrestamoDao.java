package datos;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import dominio.Prestamo;

public interface PrestamoDao {

	public boolean insert (Prestamo prestamo)throws SQLException;
	public boolean updateEstado (int idPrestamo, Boolean estadoAprobacion)throws SQLException;
	public ArrayList<Prestamo> listarPrestamosXCliente(int idCliente) throws SQLException;
	public ArrayList<Prestamo> listarSolicitudesPrestamos() throws SQLException;
	public Prestamo obtenerPrestamoPorId (int idPrestamo) throws SQLException;
	public ArrayList<Prestamo> listarTodosLosPrestamos() throws SQLException;
	public ArrayList<Prestamo> listarTodosLosPrestamosAprobados() throws SQLException;
	public ArrayList<Prestamo> listarTodosLosPrestamosRechazados() throws SQLException;
	public ArrayList<Prestamo> listarTodosLosPrestamosEnEvaluacio() throws SQLException;
	public int contarPrestamosAprobados() throws SQLException;
	public int contarPrestamosRechazados() throws SQLException;
	public int contarPrestamosEnEvaluacion() throws SQLException ;
	public BigDecimal sumarPrestamosAprobados()throws SQLException;
	public BigDecimal sumarPrestamosRechazados()throws SQLException;
	public BigDecimal sumarPrestamosEnEvaluacion()throws SQLException;
}
