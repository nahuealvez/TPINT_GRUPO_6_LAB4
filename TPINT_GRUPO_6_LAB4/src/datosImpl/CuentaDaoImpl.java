package datosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import datos.Conexion;
import datos.CuentaDao;
import dominio.Cliente;
import dominio.Cuenta;
import dominio.TipoCuenta;

public class CuentaDaoImpl implements CuentaDao {

	private static final String insert = "INSERT INTO cuentas (idCliente, fechaCreacion, idTipoCuenta, cbu, saldo, estado) VALUES (?,?,?,?, ?, ?)";
	private static final String cuentasxCliente = "  SELECT c.id, c.idCliente, c.fechaCreacion, tc.descripcion as 'tipoCuenta', c.cbu, c.saldo, c.estado from Cuentas c  inner join tiposCuentas tc on tc.id = c.idTipoCuenta where idCliente = ?";
	
	@Override
	public boolean insert(Cuenta cuenta) {
		PreparedStatement statement;
		Connection conexion= Conexion.getConexion().getSQLConexion();
		
		boolean cuentaCreada= false;
		
		try {
			statement= conexion.prepareStatement(insert);
			
			TipoCuenta tipocuentacreada= new TipoCuenta();
			tipocuentacreada.setId(cuenta.getTipoCuenta().getId());
			
			statement.setInt(1,cuenta.getCliente().getIdCliente());
			statement.setTimestamp(2, Timestamp.valueOf(cuenta.getFechaCreacion()));
			statement.setInt(3,tipocuentacreada.getId());
			statement.setString(4, cuenta.getCbu());
			statement.setBigDecimal(5, cuenta.getSaldo());
			statement.setBoolean(6, cuenta.isEstado());
			
			int filasAfectadas = statement.executeUpdate();
			
			if(filasAfectadas >0) {
				conexion.commit();
				cuentaCreada=true;
			}else {
				conexion.rollback();
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return cuentaCreada;
	}

	@Override
	public List<Cuenta> cuentasXCliente(int idCliente) {
		
		PreparedStatement statement;
		ResultSet rs;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta> (); 
		
		try {
			statement= conexion.prepareStatement(cuentasxCliente);
			statement.setInt(1, idCliente);
			rs=statement.executeQuery();
			
			while(rs.next()) {
				Cuenta cuenta= new Cuenta();
				
				Cliente cliente= new Cliente();
				cliente.setIdCliente(rs.getInt("idCliente"));
				
				TipoCuenta tipoCuenta= new TipoCuenta();
				tipoCuenta.setDescripcion(rs.getString("tipoCuenta"));
				
				cuenta.setId(rs.getInt("id"));
				cuenta.setCliente(cliente);
				cuenta.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				cuenta.setTipoCuenta(tipoCuenta);
				cuenta.setCbu(rs.getString("cbu"));
				cuenta.setSaldo(rs.getBigDecimal("saldo"));
				cuenta.setEstado(rs.getBoolean("estado"));
				
				listaCuentas.add(cuenta);
				
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return listaCuentas;
	}

}
