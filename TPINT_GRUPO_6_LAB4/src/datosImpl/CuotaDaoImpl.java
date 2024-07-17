package datosImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import datos.Conexion;
import datos.CuotaDao;
import dominio.Cuota;
import dominio.Movimiento;
import dominio.Prestamo;

public class CuotaDaoImpl implements CuotaDao{
	private PreparedStatement st;
	private ResultSet rs;
	private Connection conexion = Conexion.getConexion().getSQLConexion();
	private String insertCuota = "INSERT INTO cuotas (idPrestamo, nroCuota, fechaVencimiento, idMovimiento) VALUES (?, ?, ?, ?)";
	private String listarCuotasPrestamo = "SELECT C.id, C.idPrestamo, C.nroCuota, C.fechaVencimiento, C.fechaPago, C.idMovimiento, P.importeMensual FROM cuotas as C INNER JOIN prestamos AS P ON  C.idPrestamo = P.id WHERE idPrestamo = ?";

	@Override
	public boolean insert(Cuota cuota) throws SQLException {
		boolean agregado = false;
		
		try {
			st = conexion.prepareStatement(insertCuota);
			LocalDate fechaVtoLdt = cuota.getFechaVencimiento();
			Date fechaVtoSql = Date.valueOf(fechaVtoLdt);
			
			st.setInt(1, cuota.getPrestamo().getId());
			st.setInt(2, cuota.getNroCuota());
			st.setDate(3, fechaVtoSql);
			st.setInt(4, -1); // Por defecto las cuotas se ingresan sin pagos registrado -- idMovimiento -1
			
			int filasAfectadas = st.executeUpdate();
			
			if(filasAfectadas > 0) {
				conexion.commit();
				agregado = true;
				System.out.println("DESDE DAO CUOTA INSERT: " + cuota.toString());
			}
			else {
				conexion.rollback();
			}
		}
		catch (SQLException ex) {
			throw ex;
			
		} catch (Exception ex) {
			throw ex;
		}
		return agregado;
	}

	@Override
	public ArrayList<Cuota> listarCuotasPrestamo(int idPrestamo) throws SQLException {
		ArrayList<Cuota> cuotasPorPrestamo = new ArrayList<Cuota>();
		
		try {
			st = conexion.prepareStatement(listarCuotasPrestamo);
			st.setInt(1, idPrestamo);
			rs = st.executeQuery();
			while (rs.next()) {
				cuotasPorPrestamo.add(getCuota(rs));
			}
			return cuotasPorPrestamo;
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}
	
	private Cuota getCuota (ResultSet rs) throws SQLException {
		Cuota cuota = new Cuota();
		Prestamo prestamo = new Prestamo();
		Movimiento movimiento = new Movimiento();
		
		try {
			// CREACION OBJETOS QUE COMPONEN CUOTA
			//PRESTAMO
			prestamo.setId(rs.getInt("idPrestamo"));
			prestamo.setImporteMensual(rs.getBigDecimal("importeMensual"));
			
			//MOVIMIENTO
			movimiento.setId(rs.getInt("idMovimiento"));
			
			cuota.setId(rs.getInt("id"));
			cuota.setPrestamo(prestamo);
			cuota.setNroCuota(rs.getInt("nroCuota"));
			cuota.setFechaVencimiento(rs.getDate("fechaVencimiento").toLocalDate());
			
			//MANEJAR NULL EN FECHA PAGO
			Timestamp fechaPagoTimestamp = rs.getTimestamp("fechaPago");
			
			if(fechaPagoTimestamp != null) {
				LocalDateTime fechaPago = fechaPagoTimestamp.toLocalDateTime();
				cuota.setFechaPago(fechaPago);
			} else {
				cuota.setFechaPago(null);
			}
			cuota.setMovimiento(movimiento);
			
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
		return cuota;
	}

}
