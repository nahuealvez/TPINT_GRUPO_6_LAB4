package datosImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import datos.Conexion;
import datos.PrestamoDao;
import dominio.Cliente;
import dominio.Cuenta;
import dominio.Prestamo;

public class PrestamoDaoImpl implements PrestamoDao{
	private PreparedStatement st;
	private ResultSet rs;
	private Connection conexion = Conexion.getConexion().getSQLConexion();
	
	private static final String insert = "INSERT INTO prestamos (idCliente, idCuenta, importeAPagar, plazoDePago, importePedido, cuotas, importeMensual, estadoValidacion, fechaValidacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String updateEstadoSolicitud = "UPDATE prestamos SET estadoValidacion = ? WHERE id = ?";
	private static final String listarPrestamosXCliente = "SELECT P.id, P.idCliente, P.idCuenta, P.fechaSolicitud, P.importeAPagar, P.plazoDePago, P.importePedido, P.cuotas, P.importeMensual,C.dni,C.nombre,C.apellido FROM prestamos AS P INNER JOIN clientes AS C ON  P.idCliente = C.id WHERE idCliente = ?";
	private static final String listarSolicitudes = "SELECT P.id, P.idCliente, P.idCuenta, P.fechaSolicitud, P.importeAPagar, P.plazoDePago, P.importePedido, P.cuotas, P.importeMensual, P.estadoValidacion, P.fechaValidacion, C.dni,C.nombre,C.apellido FROM prestamos AS P INNER JOIN clientes AS C ON  P.idCliente = C.id";
	
	@Override
	public boolean insert(Prestamo prestamo) throws SQLException {
		
		boolean isInsertExitoso = false;
	
		try {
			st = conexion.prepareStatement(insert);
			st.setInt(1, prestamo.getCliente().getIdCliente());
			st.setInt(2, prestamo.getCuenta().getId());
			st.setBigDecimal(3, prestamo.getImporteAPagar());
			st.setInt(4, prestamo.getPlazoDePago());
			st.setBigDecimal(5, prestamo.getImportePedido());
			st.setInt(6, prestamo.getCuotas());
			st.setBigDecimal(7, prestamo.getImporteMensual());
			st.setNull(8, java.sql.Types.BIT);
			st.setNull(9, java.sql.Types.TIMESTAMP);
			
			int filasAfectadas = st.executeUpdate();
			
			if(filasAfectadas > 0) {
				conexion.commit();
				isInsertExitoso = true;
			} else {
				conexion.rollback();
			}

			
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
		
		return isInsertExitoso;
	}
	@Override
	public boolean updateEstado(int idPrestamo, Boolean estadoAprobacion) throws SQLException {
				
		boolean actualizacionEstadoExitosa = false;
		
		try {
			st = conexion.prepareStatement(updateEstadoSolicitud);
			st.setBoolean(1, estadoAprobacion);
			st.setInt(2, idPrestamo);
			
			int filasAfectadas = st.executeUpdate();
			
			if(filasAfectadas > 0) {
				conexion.commit();
				actualizacionEstadoExitosa = true;
				
			} else {
				conexion.rollback();
			}
			
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
		return actualizacionEstadoExitosa;
	}
	
	@Override
	public ArrayList<Prestamo> listarPrestamosXCliente(int idCliente)throws SQLException {
		ArrayList<Prestamo> prestamosPorCliente = new ArrayList<Prestamo>();
		
		try {
			st = conexion.prepareStatement(listarPrestamosXCliente);
			st.setInt(1, idCliente);
			rs = st.executeQuery();
			while(rs.next())
			{
				prestamosPorCliente.add(getPrestamo(rs));
			}
			return prestamosPorCliente;
			
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
		
	}
	
	@Override
	public ArrayList<Prestamo> listarSolicitudesPrestamos() throws SQLException {
		ArrayList<Prestamo> solicitudesPrestamos = new ArrayList<Prestamo>();
		
		try {
			st = conexion.prepareStatement(listarSolicitudes);
			rs = st.executeQuery();
			while(rs.next())
			{
				solicitudesPrestamos.add(getPrestamo(rs));
			}
			return solicitudesPrestamos;
			
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}
	
	private Prestamo getPrestamo(ResultSet resultSet) throws SQLException
	{
		Prestamo prestamo = new Prestamo();
		
		// CREACION OBJETOS QUE COMPONEN PRESTAMOS
		
		//CLIENTE
		Cliente cliente = new Cliente();
		cliente.setIdCliente(resultSet.getInt("idCliente"));
		cliente.setNombre(resultSet.getString("nombre"));
		cliente.setApellido(resultSet.getString("apellido"));
		cliente.setDni(resultSet.getString("dni"));
		
		//CUENTA
		Cuenta cuenta = new Cuenta();
		cuenta.setId(resultSet.getInt("idCuenta"));
		
		prestamo.setId(resultSet.getInt("id"));
		prestamo.setCliente(cliente);
		prestamo.setCuenta(cuenta);
		prestamo.setFechaSolicitud(resultSet.getTimestamp("fechaSolicitud").toLocalDateTime());
		prestamo.setImporteAPagar(resultSet.getBigDecimal("importeAPagar"));
		prestamo.setPlazoDePago(resultSet.getInt("plazoDePago"));
		prestamo.setImportePedido(resultSet.getBigDecimal("importePedido"));
		prestamo.setCuotas(resultSet.getInt("cuotas"));
		prestamo.setImporteMensual(resultSet.getBigDecimal("importeMensual"));
		
		// CON getObject TAMBIEN SE GUARDA NULL EN CASO QUE LA BASE DE DATOS DEVUELVA ESO
		Object estadoValidacionObj = resultSet.getObject("estadoValidacion");
		if (estadoValidacionObj != null) {
		    prestamo.setEstadoValidacion((Boolean) estadoValidacionObj);
		} else {
		    prestamo.setEstadoValidacion(null);
		}
		
        // CHEQUEAR SI FECHA VALIDACION ESTA EN NULL
        Timestamp fechaValidacionTimestamp = resultSet.getTimestamp("fechaValidacion");
        if (fechaValidacionTimestamp != null) {
            prestamo.setFechaValidacion(fechaValidacionTimestamp.toLocalDateTime());
        } else {
            prestamo.setFechaValidacion(null);
        }
				
		return prestamo;
	}
	

}
