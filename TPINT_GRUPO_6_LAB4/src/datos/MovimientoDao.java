package datos;

import java.sql.SQLException;
import java.util.ArrayList;

import dominio.Movimiento;

public interface MovimientoDao {
	public ArrayList<Movimiento> listarMovimientosPorCuenta(int idCuenta) throws SQLException;
	public boolean agregarMovimiento(Movimiento movimiento) throws SQLException;
}
