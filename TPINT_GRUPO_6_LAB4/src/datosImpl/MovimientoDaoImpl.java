package datosImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;

import datos.ClienteDao;
import datos.Conexion;
import datos.MovimientoDao;
import dominio.Cliente;
import dominio.Cuenta;
import dominio.Movimiento;
import dominio.TipoCuenta;
import dominio.TipoMovimiento;

public class MovimientoDaoImpl implements MovimientoDao {
	private PreparedStatement st;
	private ResultSet rs;
	private Connection conexion = Conexion.getConexion().getSQLConexion();
	private static final String listarMovimientosPorCuenta = "SELECT M.id AS idMovimiento, M.fecha AS fechaMovimiento, M.concepto AS conceptoMovimiento, M.importe AS importeMovimiento, TM.id AS idTipoMovimiento, TM.descripcion AS nombreTipoMovimiento , CU.id AS idCuenta, TC.id AS idTipoCuenta, TC.descripcion AS nombreTipoCuenta, CL.id AS idCliente FROM movimientos AS M INNER JOIN tiposMovimientos AS TM ON M.idTipoMovimiento = TM.id INNER JOIN cuentas AS CU ON M.idCuenta = CU.id INNER JOIN tiposcuentas AS TC ON CU.idTipoCuenta = TC.id INNER JOIN clientes AS CL ON CU.idCliente = CL.id WHERE CU.id = ?";
	private static final String agregarMovimiento = "INSERT INTO movimientos (idTipoMovimiento, concepto, idCuenta, importe) VALUES (?, ?, ?, ?)";
	
	@SuppressWarnings("unused")
	private Movimiento getMovimiento(ResultSet rs) throws SQLException {
		Movimiento movimiento = new Movimiento();
		TipoMovimiento tipoMovimiento = new TipoMovimiento();
		TipoCuenta tipoCuenta = new TipoCuenta();
		Cuenta cuenta = new Cuenta();
		Cliente cliente = new Cliente();
		ClienteDao clienteDao = new ClienteDaoImpl();
		
		try {
			movimiento.setId(rs.getInt("idMovimiento"));
			Date sqlDate = rs.getDate("fechaMovimiento");
			LocalDate ld = sqlDate.toLocalDate();
			LocalDateTime ldt = ld.atTime(LocalTime.MIDNIGHT);
			movimiento.setFecha(ldt);
			movimiento.setConcepto(rs.getString("conceptoMovimiento"));
			movimiento.setImporte(rs.getBigDecimal("importeMovimiento"));
			
			tipoMovimiento.setId(rs.getInt("idTipoMovimiento"));
			tipoMovimiento.setDescripcion(rs.getString("nombreTipoMovimiento"));
			movimiento.setTipoMovimiento(tipoMovimiento);
			
			movimiento.setConcepto(rs.getString("conceptoMovimiento"));
			
			tipoCuenta.setId(rs.getInt("idTipoCuenta"));
			tipoCuenta.setDescripcion(rs.getString("nombreTipoCuenta"));
			
			int idCliente = rs.getInt("idCliente");
			
			cliente = clienteDao.listarClienteXId(idCliente);
			
			cuenta.setId(rs.getInt("idCuenta"));
			cuenta.setTipoCuenta(tipoCuenta);
			cuenta.setCliente(cliente);
			
			movimiento.setCuenta(cuenta);
			
			movimiento.setImporte(rs.getBigDecimal("importeMovimiento"));
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
		
		return movimiento;
	}
	
	@Override
	public ArrayList<Movimiento> listarMovimientosPorCuenta(int idCuenta) throws SQLException {
		ArrayList<Movimiento> movimientosPorCuenta = new ArrayList<Movimiento>();
		

		    try {
		        st = conexion.prepareStatement(listarMovimientosPorCuenta);
		        st.setInt(1, idCuenta);
		        rs = st.executeQuery();

		        while (rs.next()) {
		            Movimiento movimiento = new Movimiento(); // Crear un nuevo objeto Movimiento en cada iteraci�n
		            Cuenta cuenta =new Cuenta();
		            
		            movimiento.setId(rs.getInt("idMovimiento"));
		            movimiento.setFecha(rs.getTimestamp("fechaMovimiento").toLocalDateTime()); // Aseg�rate de que el m�todo setFecha espera LocalDateTime
		            movimiento.setConcepto(rs.getString("conceptoMovimiento"));
		            
		            cuenta.setId(rs.getInt("idCuenta"));
		            movimiento.setCuenta(cuenta);		           
		            movimiento.setImporte(rs.getBigDecimal("importeMovimiento"));

		           
		            TipoMovimiento tipoMovimiento = new TipoMovimiento();
		            tipoMovimiento.setId(rs.getInt("idTipoMovimiento"));
		            tipoMovimiento.setDescripcion(rs.getString("nombreTipoMovimiento"));
		            movimiento.setTipoMovimiento(tipoMovimiento);

		            movimientosPorCuenta.add(movimiento);
		        }
		        return movimientosPorCuenta;
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public boolean agregarMovimiento(Movimiento movimiento) throws SQLException {
		boolean agregado = false;
		
		try {
			st = conexion.prepareStatement(agregarMovimiento);
			st.setInt(1, movimiento.getTipoMovimiento().getId());
			st.setString(2, movimiento.getConcepto());
			st.setInt(3, movimiento.getCuenta().getId());
			st.setBigDecimal(4, movimiento.getImporte());
			
			int filasAfectadas = st.executeUpdate();
			
			if (filasAfectadas > 0) {
				conexion.commit();
				agregado = true;
			}
			else {
				conexion.rollback();
			}
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
		
		return agregado;
	}

	@Override
	public int agregarMovimientoConDevolucionDeId(Movimiento movimiento) throws SQLException {
		int idMovimiento = 0;
		
		try {
			st = conexion.prepareStatement(agregarMovimiento, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, movimiento.getTipoMovimiento().getId());
			st.setString(2, movimiento.getConcepto());
			st.setInt(3, movimiento.getCuenta().getId());
			st.setBigDecimal(4, movimiento.getImporte());
			
			int filasAfectadas = st.executeUpdate();
			
			if (filasAfectadas > 0) {
				try (ResultSet idGenerada = st.getGeneratedKeys()) {
					if (idGenerada.next()) {
						idMovimiento = idGenerada.getInt(1);
						conexion.commit();
					}
				}
			}
			else {
				conexion.rollback();
				return 0;
			}
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
		
		return idMovimiento;
	}

}
