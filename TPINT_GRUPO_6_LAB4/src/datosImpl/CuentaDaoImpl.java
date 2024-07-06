package datosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import datos.Conexion;
import datos.CuentaDao;
import dominio.Cliente;
import dominio.Cuenta;
import dominio.TipoCuenta;

public class CuentaDaoImpl implements CuentaDao {

	private static final String insert = "INSERT INTO cuentas (idCliente, fechaCreacion, idTipoCuenta, cbu, saldo, estado) VALUES (?,?,?,?, ?, ?)";
	private static final String cuentasxCliente = "  SELECT c.id, c.idCliente, c.fechaCreacion, tc.descripcion as 'tipoCuenta', c.cbu, c.saldo, c.estado from Cuentas c  inner join tiposCuentas tc on tc.id = c.idTipoCuenta where idCliente = ?";
	private static final String actualizarEstado = "UPDATE cuentas set estado=? WHERE id=?";
	private static final String verificarCbu = "SELECT 1 FROM cuentas where cbu=?";
	private static final String verificarEstado= "SELECT estado FROM cuentas WHERE id=?";
	
	@Override
	public boolean insert(Cuenta cuenta) throws SQLException{
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
		catch (Exception ex) {
			throw ex;
		}
		
		return cuentaCreada;
	}

	@Override
	public List<Cuenta> cuentasXCliente(int idCliente) throws SQLException{
		
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
		catch (Exception ex) {
			throw ex;
		}
		return listaCuentas;
	}

	@Override
	public boolean actualizarEstado(int idCuenta, boolean estado)throws SQLException {
		
		PreparedStatement statement;
		  Connection conexion= Conexion.getConexion().getSQLConexion();
		  
		  try {
		   statement= conexion.prepareStatement(actualizarEstado);
		   statement.setInt(2, idCuenta);
		   statement.setBoolean(1, estado);
		   
		   int filasAfectadas= statement.executeUpdate();
		   if(filasAfectadas > 0) {
		    conexion.commit();
		    return true;
		   }
		   
		  }catch(SQLException e) {
		   e.printStackTrace();
		  }
		  catch (Exception ex) {
				throw ex;
			}
		  
		  return false;
		
	}

	@Override
	public boolean verificarCbu(String cbu)throws SQLException {
		
		PreparedStatement statement;
		ResultSet rs;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		
		try {
			 statement = conexion.prepareStatement(verificarCbu);
		        statement.setString(1, cbu);
		        rs = statement.executeQuery();
		        
		        return rs.next(); 
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		catch (Exception ex) {
			throw ex;
		}
		return false;
	}

	@Override
	public String nuevoCbu() throws SQLException{
		String cbu;
		try {
		CuentaDao cuentaDao = new CuentaDaoImpl();
		do {
			Random rd = new Random();
			StringBuilder cbuRandom = new StringBuilder();
			for(int x = 0; x < 22; x++ ) {
				cbuRandom.append(rd.nextInt(10));
			}
			cbu = cbuRandom.toString();
		}while(cuentaDao.verificarCbu(cbu));
		
		}catch(SQLException ex) {
			throw ex;
		}catch (Exception ex) {
			throw ex;
		}
		return cbu;
	}

	@Override
	public boolean verificarEstado(int idCuenta)throws SQLException {
		
		PreparedStatement statement;
		ResultSet rs;
		try {
		Connection conexion= Conexion.getConexion().getSQLConexion();
		statement= conexion.prepareStatement(verificarEstado);
		statement.setInt(1, idCuenta);
		rs= statement.executeQuery();
		if(rs.next()) {
			return rs.getBoolean("estado");
			
		}
		}catch(SQLException e) {
			e.printStackTrace();
			
		}catch (Exception ex) {
			throw ex;
		}
		return false;
		
	}

	@Override
	public int contarCuentasActivas(List<Cuenta> listaCuentas)throws SQLException{
		
		CuentaDao cuentaux=new CuentaDaoImpl();
		  int aux = 0;
		  try {
		    for (Cuenta cuenta : listaCuentas) {
		        if ( cuentaux.verificarEstado(cuenta.getId())==true) {
		            aux++;
		        }
		    }
		  }catch(SQLException ex) {
			  throw ex;
		  }
		  catch (Exception ex) {
				throw ex;
			}
		return aux;
	}

	@Override
	public List<Cuenta> cuentasPorClienteActivas(int idCliente) throws SQLException {
		PreparedStatement statement;
		ResultSet rs;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta> (); 
		
		try {
			statement= conexion.prepareStatement(cuentasxCliente);
			statement.setInt(1, idCliente);
			rs=statement.executeQuery();
			
			while(rs.next()) {
				Cuenta cuenta = new Cuenta();
				Cliente cliente = new Cliente();
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
				
				if (cuenta.isEstado() == true) {
					listaCuentas.add(cuenta);
				}
			}
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
			
		return listaCuentas;
	}
}
