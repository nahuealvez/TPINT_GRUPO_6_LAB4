package datosImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

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
	private static final String listarPrestamosXCliente = "SELECT P.id, P.idCliente, P.idCuenta, P.fechaSolicitud, P.importeAPagar, P.plazoDePago, P.importePedido, P.cuotas, P.importeMensual, P.estadoValidacion, P.fechaValidacion, C.dni,C.nombre,C.apellido FROM prestamos AS P INNER JOIN clientes AS C ON  P.idCliente = C.id WHERE idCliente = ?";
	private static final String listarSolicitudes = "SELECT P.id, P.idCliente, P.idCuenta, P.fechaSolicitud, P.importeAPagar, P.plazoDePago, P.importePedido, P.cuotas, P.importeMensual, P.estadoValidacion, P.fechaValidacion, C.dni,C.nombre,C.apellido FROM prestamos AS P INNER JOIN clientes AS C ON  P.idCliente = C.id";
	private static final String obtenerPrestamoPorId = "SELECT P.id, P.idCliente, P.idCuenta, P.fechaSolicitud, P.importeAPagar, P.plazoDePago, P.importePedido, P.cuotas, P.importeMensual, P.estadoValidacion, P.fechaValidacion, C.dni, C.nombre, C.apellido FROM prestamos AS P INNER JOIN clientes AS C ON  P.idCliente = C.id WHERE P.id = ?";
	private static final String listarTodosLosPrestamos = "SELECT P.id, P.idCliente, P.idCuenta, P.fechaSolicitud, P.importeAPagar, P.plazoDePago, P.importePedido, P.cuotas, P.importeMensual, P.estadoValidacion, P.fechaValidacion, C.dni, C.nombre, C.apellido FROM prestamos AS P INNER JOIN clientes AS C ON P.idCliente = C.id";
	private static final String listarTodosLosPrestamosAprobados ="	SELECT P.id, P.idCliente, P.idCuenta, P.fechaSolicitud, P.importeAPagar, P.plazoDePago, P.importePedido, P.cuotas, P.importeMensual, P.estadoValidacion, P.fechaValidacion, C.dni, C.nombre, C.apellido FROM prestamos AS P INNER JOIN clientes AS C ON P.idCliente = C.id WHERE estadoValidacion = 1";  
	private static final String listarTodosLosPrestamosRechazados ="	SELECT P.id, P.idCliente, P.idCuenta, P.fechaSolicitud, P.importeAPagar, P.plazoDePago, P.importePedido, P.cuotas, P.importeMensual, P.estadoValidacion, P.fechaValidacion, C.dni, C.nombre, C.apellido FROM prestamos AS P INNER JOIN clientes AS C ON P.idCliente = C.id WHERE estadoValidacion = 0";  
	private static final String listarTodosLosPrestamosEnEvaluacion ="	SELECT P.id, P.idCliente, P.idCuenta, P.fechaSolicitud, P.importeAPagar, P.plazoDePago, P.importePedido, P.cuotas, P.importeMensual, P.estadoValidacion, P.fechaValidacion, C.dni, C.nombre, C.apellido FROM prestamos AS P INNER JOIN clientes AS C ON P.idCliente = C.id WHERE estadoValidacion is null";  
	private static final String contarAprobados = "SELECT CASE WHEN COUNT(*) IS NULL THEN 0 ELSE COUNT(*) END AS count FROM prestamos WHERE estadoValidacion = 1";
	private static final String contarRechazados= "SELECT CASE WHEN COUNT(*) IS NULL THEN 0 ELSE COUNT(*) END AS count FROM prestamos WHERE estadoValidacion = 0";
	private static final String contarEvaluacion= "SELECT CASE WHEN COUNT(*) IS NULL THEN 0 ELSE COUNT(*) END AS count FROM prestamos WHERE estadoValidacion IS NULL";
	private static final String sumarValorAprobados = "SELECT CASE WHEN sum(importePedido) IS NULL THEN 0 ELSE sum(importePedido) END AS sum FROM prestamos WHERE estadoValidacion = 1";
	private static final String sumarValorRechazados= "SELECT CASE WHEN sum(importePedido) IS NULL THEN 0 ELSE sum(importePedido) END AS sum FROM prestamos WHERE estadoValidacion = 0";
	private static final String sumarValorEvaluacion= "SELECT CASE WHEN sum(importePedido) IS NULL THEN 0 ELSE sum(importePedido) END AS sum FROM prestamos WHERE estadoValidacion is null";
	
	
	
	
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
	
	@Override
	public Prestamo obtenerPrestamoPorId(int idPrestamo) throws SQLException {
		Prestamo prestamo = new Prestamo();
		
		try {
			st = conexion.prepareStatement(obtenerPrestamoPorId);
			st.setInt(1, idPrestamo);
			rs = st.executeQuery();
			 if (rs.next()) {
		          prestamo = getPrestamo(rs);
		     } else {
		    	 throw new SQLException("No se encontr� ning�n pr�stamo con el ID especificado: " + idPrestamo);
		     }
			return prestamo;
			
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
	@Override
	public ArrayList<Prestamo> listarTodosLosPrestamos() throws SQLException {
		ArrayList<Prestamo> todosLosPrestamos = new ArrayList<>();

	    try {
	        st = conexion.prepareStatement(listarTodosLosPrestamos);
	        rs = st.executeQuery();
	        while (rs.next()) {
	            todosLosPrestamos.add(getPrestamo(rs));
	        }
	        return todosLosPrestamos;
	        
	    } catch (SQLException ex) {
	        throw ex;
	    } catch (Exception ex) {
	        throw ex;
	    } 
	}
	@Override
	public ArrayList<Prestamo> listarTodosLosPrestamosAprobados() throws SQLException {
		ArrayList<Prestamo> todosLosPrestamos = new ArrayList<>();

	    try {
	        st = conexion.prepareStatement(listarTodosLosPrestamosAprobados);
	        rs = st.executeQuery();
	        while (rs.next()) {
	            todosLosPrestamos.add(getPrestamo(rs));
	        }
	        return todosLosPrestamos;
	        
	    } catch (SQLException ex) {
	        throw ex;
	    } catch (Exception ex) {
	        throw ex;
	    } 
	}
	@Override
	public ArrayList<Prestamo> listarTodosLosPrestamosRechazados() throws SQLException {
		ArrayList<Prestamo> todosLosPrestamos = new ArrayList<>();

	    try {
	        st = conexion.prepareStatement(listarTodosLosPrestamosRechazados);
	        rs = st.executeQuery();
	        while (rs.next()) {
	            todosLosPrestamos.add(getPrestamo(rs));
	        }
	        return todosLosPrestamos;
	        
	    } catch (SQLException ex) {
	        throw ex;
	    } catch (Exception ex) {
	        throw ex;
	    } 
	}
	@Override
	public ArrayList<Prestamo> listarTodosLosPrestamosEnEvaluacio() throws SQLException {
		ArrayList<Prestamo> todosLosPrestamos = new ArrayList<>();

	    try {
	        st = conexion.prepareStatement(listarTodosLosPrestamosEnEvaluacion);
	        rs = st.executeQuery();
	        while (rs.next()) {
	            todosLosPrestamos.add(getPrestamo(rs));
	        }
	        return todosLosPrestamos;
	        
	    } catch (SQLException ex) {
	        throw ex;
	    } catch (Exception ex) {
	        throw ex;
	    } 
	}
	
	@Override
	public int contarPrestamosAprobados() throws SQLException {
		try (PreparedStatement st = conexion.prepareStatement(contarAprobados);
		         ResultSet rs = st.executeQuery()) {
		        if (rs.next()) {
		            return rs.getInt("count");
		        }
		    } catch (SQLException ex) {
		        throw ex;
		    }
		    return 0;
	}
	@Override
	public int contarPrestamosRechazados() throws SQLException {
		 try (PreparedStatement st = conexion.prepareStatement(contarRechazados);
		         ResultSet rs = st.executeQuery()) {
		        if (rs.next()) {
		            return rs.getInt("count");
		        }
		    } catch (SQLException ex) {
		        throw ex;
		    }
		    return 0;
	}
	@Override
	public int contarPrestamosEnEvaluacion() throws SQLException {
		 try (PreparedStatement st = conexion.prepareStatement(contarEvaluacion);
		         ResultSet rs = st.executeQuery()) {
		        if (rs.next()) {
		            return rs.getInt("count");
		        }
		    } catch (SQLException ex) {
		        throw ex;
		    }
		    return 0;
	}
	@Override
	public BigDecimal sumarPrestamosAprobados() throws SQLException {
		try (PreparedStatement st = conexion.prepareStatement(sumarValorAprobados);
		         ResultSet rs = st.executeQuery()) {
		        if (rs.next()) {
		            return rs.getBigDecimal("sum");
		        }
		    } catch (SQLException ex) {
		        throw ex;
		    }
		    return BigDecimal.ZERO;
	}
	@Override
	public BigDecimal sumarPrestamosRechazados() throws SQLException {
		try (PreparedStatement st = conexion.prepareStatement(sumarValorRechazados);
		         ResultSet rs = st.executeQuery()) {
		        if (rs.next()) {
		            return rs.getBigDecimal("sum");
		        }
		    } catch (SQLException ex) {
		        throw ex;
		    }
		    return BigDecimal.ZERO;
	}
	@Override
	public BigDecimal sumarPrestamosEnEvaluacion() throws SQLException {
		try (PreparedStatement st = conexion.prepareStatement(sumarValorEvaluacion);
		         ResultSet rs = st.executeQuery()) {
		        if (rs.next()) {
		            return rs.getBigDecimal("sum");
		        }
		    } catch (SQLException ex) {
		        throw ex;
		    }
		    return BigDecimal.ZERO;
	}
	
}
