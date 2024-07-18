package negocio;

import java.sql.SQLException;
import java.util.ArrayList;

import dominio.Movimiento;

public interface MovimientoNegocio {
	public ArrayList<Movimiento> listarMovimientosPorCuenta(int idCuenta) throws SQLException;
	public boolean agregarMovimiento(Movimiento movimiento) throws SQLException;
	public int agregarMovimientoConDevolucionDeId(Movimiento movimiento) throws SQLException;
}
