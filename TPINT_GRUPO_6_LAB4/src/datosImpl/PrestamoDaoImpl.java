package datosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import datos.Conexion;
import datos.PrestamoDao;
import dominio.Prestamo;

public class PrestamoDaoImpl implements PrestamoDao{

	private static final String insert = "INSERT INTO prestamos (idCliente, idCuenta, importeAPagar, plazoDePago, importePedido, cuotas, importeMensual, estadoValidacion, fechaValidacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String updateEstadoSolicitud = "UPDATE prestamos SET estadoValidacion = ? WHERE id = ?";
	
	@Override
	public boolean insert(Prestamo prestamo) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		
		boolean isInsertExitoso = false;
	
		try {
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, prestamo.getCliente().getIdCliente());
			statement.setInt(2, prestamo.getCuenta().getId());
			statement.setBigDecimal(3, prestamo.getImporteAPagar());
			statement.setInt(4, prestamo.getPlazoDePago());
			statement.setBigDecimal(5, prestamo.getImportePedido());
			statement.setInt(6, prestamo.getCuotas());
			statement.setBigDecimal(7, prestamo.getImporteMensual());
			statement.setNull(8, java.sql.Types.BIT);
			statement.setNull(9, java.sql.Types.TIMESTAMP);
			
			int filasAfectadas = statement.executeUpdate();
			
			if(filasAfectadas > 0) {
				conexion.commit();
				isInsertExitoso = true;
			} else {
				conexion.rollback();
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return isInsertExitoso;
	}
	@Override
	public boolean updateEstado(int idPrestamo, boolean estadoAprobacion) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
				
		boolean actualizacionEstadoExitosa = false;
		
		try {
			statement = conexion.prepareStatement(updateEstadoSolicitud);
			statement.setBoolean(1, estadoAprobacion);
			statement.setInt(2, idPrestamo);
			
			int filasAfectadas = statement.executeUpdate();
			
			if(filasAfectadas > 0) {
				conexion.commit();
				actualizacionEstadoExitosa = true;
				
			} else {
				conexion.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actualizacionEstadoExitosa;
	}
	

}
