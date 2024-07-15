package datosImpl;

import java.math.BigDecimal;
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
import dominio.Localidad;
import dominio.Provincia;
import dominio.TipoCuenta;

public class CuentaDaoImpl implements CuentaDao {

	private static final String insert = "INSERT INTO cuentas (idCliente, fechaCreacion, idTipoCuenta, cbu, saldo, estado) VALUES (?,?,?,?, ?, ?)";
	private static final String cuentasxCliente = "  SELECT c.id, c.idCliente, c.fechaCreacion, tc.descripcion as 'tipoCuenta', c.cbu, c.saldo, c.estado from Cuentas c  inner join tiposCuentas tc on tc.id = c.idTipoCuenta where idCliente = ?";
	private static final String actualizarEstado = "UPDATE cuentas set estado=? WHERE id=?";
	private static final String verificarCbu = "SELECT 1 FROM cuentas where cbu=?";
	private static final String verificarEstado= "SELECT estado FROM cuentas WHERE id=?";
	private static final String obtenerNroCuentaActivaPorCBU = "SELECT id FROM cuentas WHERE cbu = ? AND estado = 1";
	private static final String obtenerCuentaPorId = "SELECT id, idCliente, fechaCreacion, idTipoCuenta, cbu, saldo, estado FROM cuentas WHERE id = ?";
	private static final String obtenerCuentas = "SELECT c.id AS cuentaId, cli.id AS clienteId, cli.dni, cli.cuil, cli.nombre AS clienteNombre, cli.apellido AS clienteApellido, cli.sexo, cli.nacionalidad, cli.fechaNacimiento, prov.nombre AS provinciaNombre, loc.nombre AS localidadNombre, cli.direccion, cli.email, cli.telefono, u.id AS usuarioId, u.usuario AS usuarioNombre, u.contrasenia, u.estado AS usuarioEstado, c.fechaCreacion, tc.descripcion AS tipoCuentaDescripcion, c.cbu, c.saldo, c.estado AS cuentaEstado FROM cuentas c JOIN clientes cli ON c.idCliente = cli.id JOIN usuarios u ON cli.idUsuario = u.id JOIN provincias prov ON cli.idProvincia = prov.id JOIN localidades loc ON cli.idLocalidad = loc.id JOIN tiposCuentas tc ON c.idTipoCuenta = tc.id";
	private static final String obtenerCuentasCorrientes = "SELECT c.id AS cuentaId, cli.id AS clienteId, cli.dni, cli.cuil, cli.nombre AS clienteNombre, cli.apellido AS clienteApellido, cli.sexo, cli.nacionalidad, cli.fechaNacimiento, prov.nombre AS provinciaNombre, loc.nombre AS localidadNombre, cli.direccion, cli.email, cli.telefono, u.id AS usuarioId, u.usuario AS usuarioNombre, u.contrasenia, u.estado AS usuarioEstado, c.fechaCreacion, tc.descripcion AS tipoCuentaDescripcion, c.cbu, c.saldo, c.estado AS cuentaEstado FROM cuentas c JOIN clientes cli ON c.idCliente = cli.id JOIN usuarios u ON cli.idUsuario = u.id JOIN provincias prov ON cli.idProvincia = prov.id JOIN localidades loc ON cli.idLocalidad = loc.id JOIN tiposCuentas tc ON c.idTipoCuenta = tc.id WHERE c.idTipoCuenta = 2";
	private static final String obtenerCuentasAhorro = "SELECT c.id AS cuentaId, cli.id AS clienteId, cli.dni, cli.cuil, cli.nombre AS clienteNombre, cli.apellido AS clienteApellido, cli.sexo, cli.nacionalidad, cli.fechaNacimiento, prov.nombre AS provinciaNombre, loc.nombre AS localidadNombre, cli.direccion, cli.email, cli.telefono, u.id AS usuarioId, u.usuario AS usuarioNombre, u.contrasenia, u.estado AS usuarioEstado, c.fechaCreacion, tc.descripcion AS tipoCuentaDescripcion, c.cbu, c.saldo, c.estado AS cuentaEstado FROM cuentas c JOIN clientes cli ON c.idCliente = cli.id JOIN usuarios u ON cli.idUsuario = u.id JOIN provincias prov ON cli.idProvincia = prov.id JOIN localidades loc ON cli.idLocalidad = loc.id JOIN tiposCuentas tc ON c.idTipoCuenta = tc.id WHERE c.idTipoCuenta = 1";
	private static final String listasActivas= "SELECT c.id, c.idCliente, c.fechaCreacion, tc.descripcion as 'tipoCuenta', c.cbu, c.saldo, c.estado from Cuentas c  inner join tiposCuentas tc on tc.id = c.idTipoCuenta where idCliente = ? and estado=true";
	private static final String afectarSaldo = "UPDATE cuentas SET saldo =  saldo + ? WHERE id = ?";
	private static final String cuentasxEstado= "SELECT c.id, c.idCliente, c.fechaCreacion, tc.descripcion as 'tipoCuenta', c.cbu, c.saldo, c.estado from Cuentas c  inner join tiposCuentas tc on tc.id = c.idTipoCuenta where idCliente = ? and estado=?";
	private static final String obtenerUltimaCuenta = "SELECT c.id, c.idCliente, c.fechaCreacion, c.idTipoCuenta, c.cbu, c.saldo, c.estado from Cuentas c where idCliente = ?";
	
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
		}catch (Exception ex) {
			throw ex;
		}
		
		return cuentaCreada;
	}

	@Override
	public ArrayList<Cuenta> cuentasXCliente(int idCliente) throws SQLException{
		
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
	
	public int obtenerNroCuentaActivaPorCBU(String cbu) throws SQLException {
		PreparedStatement statement;
		ResultSet rs;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		
		try {
			 statement = conexion.prepareStatement(obtenerNroCuentaActivaPorCBU);
		     statement.setString(1, cbu);
		     rs = statement.executeQuery();
		     if (rs.next()) {
		    	 return rs.getInt("id"); 
		     }
		     return 0;
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
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

	@Override
	public List<Cuenta> obtenerTodasLasCuentas() throws SQLException {
		PreparedStatement statement;
		 ResultSet rs;
		 Connection conexion = Conexion.getConexion().getSQLConexion();
		 ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
		    
	    try {
	        statement = conexion.prepareStatement(obtenerCuentas);
	        rs = statement.executeQuery();
	        while (rs.next()) {
	            Cuenta cuenta = new Cuenta();

	            Cliente cliente = new Cliente();
	            cliente.setIdCliente(rs.getInt("clienteId"));
	            cliente.setDni(rs.getString("dni"));
	            cliente.setCuil(rs.getString("cuil"));
	            cliente.setNombre(rs.getString("clienteNombre"));
	            cliente.setApellido(rs.getString("clienteApellido"));
	            cliente.setSexo(rs.getString("sexo").charAt(0));
	            cliente.setNacionalidad(rs.getString("nacionalidad"));
	            cliente.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
	            cliente.setDireccion(rs.getString("direccion"));
	            cliente.setEmail(rs.getString("email"));
	            cliente.setTelefono(rs.getString("telefono"));

	            
	            cliente.setId(rs.getInt("usuarioId"));
	            cliente.setUsuario(rs.getString("usuarioNombre"));
	            cliente.setContrasenia(rs.getString("contrasenia"));
	            cliente.setEstado(rs.getBoolean("usuarioEstado"));
	            
	            Provincia provincia = new Provincia();
	            provincia.setNombre(rs.getString("provinciaNombre"));
	            cliente.setProvincia(provincia);

	            Localidad localidad = new Localidad();
	            localidad.setNombre(rs.getString("localidadNombre"));
	            cliente.setLocalidad(localidad); 

	            TipoCuenta tipoCuenta = new TipoCuenta();
	            tipoCuenta.setDescripcion(rs.getString("tipoCuentaDescripcion"));

	            cuenta.setId(rs.getInt("cuentaId"));
	            cuenta.setCliente(cliente);
	            cuenta.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
	            cuenta.setTipoCuenta(tipoCuenta);
	            cuenta.setCbu(rs.getString("cbu"));
	            cuenta.setSaldo(rs.getBigDecimal("saldo"));
	            cuenta.setEstado(rs.getBoolean("cuentaEstado"));

	            listaCuentas.add(cuenta);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }catch (Exception ex) {
			throw ex;
		}
	    return listaCuentas;
	}

	@Override
	public List<Cuenta> obtenerTodasLasCuentasCorrientes() throws SQLException {
		PreparedStatement statement;
		 ResultSet rs;
		 Connection conexion = Conexion.getConexion().getSQLConexion();
		 ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
		    
	    try {
	        statement = conexion.prepareStatement(obtenerCuentasCorrientes);
	        rs = statement.executeQuery();
	        while (rs.next()) {
	            Cuenta cuenta = new Cuenta();

	            Cliente cliente = new Cliente();
	            cliente.setIdCliente(rs.getInt("clienteId"));
	            cliente.setDni(rs.getString("dni"));
	            cliente.setCuil(rs.getString("cuil"));
	            cliente.setNombre(rs.getString("clienteNombre"));
	            cliente.setApellido(rs.getString("clienteApellido"));
	            cliente.setSexo(rs.getString("sexo").charAt(0));
	            cliente.setNacionalidad(rs.getString("nacionalidad"));
	            cliente.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
	            cliente.setDireccion(rs.getString("direccion"));
	            cliente.setEmail(rs.getString("email"));
	            cliente.setTelefono(rs.getString("telefono"));

	            
	            cliente.setId(rs.getInt("usuarioId"));
	            cliente.setUsuario(rs.getString("usuarioNombre"));
	            cliente.setContrasenia(rs.getString("contrasenia"));
	            cliente.setEstado(rs.getBoolean("usuarioEstado"));
	            
	            Provincia provincia = new Provincia();
	            provincia.setNombre(rs.getString("provinciaNombre"));
	            cliente.setProvincia(provincia);

	            Localidad localidad = new Localidad();
	            localidad.setNombre(rs.getString("localidadNombre"));
	            cliente.setLocalidad(localidad); 

	            TipoCuenta tipoCuenta = new TipoCuenta();
	            tipoCuenta.setDescripcion(rs.getString("tipoCuentaDescripcion"));

	            cuenta.setId(rs.getInt("cuentaId"));
	            cuenta.setCliente(cliente);
	            cuenta.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
	            cuenta.setTipoCuenta(tipoCuenta);
	            cuenta.setCbu(rs.getString("cbu"));
	            cuenta.setSaldo(rs.getBigDecimal("saldo"));
	            cuenta.setEstado(rs.getBoolean("cuentaEstado"));

	            listaCuentas.add(cuenta);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }catch (Exception ex) {
			throw ex;
		}
	    return listaCuentas;
	}

	@Override
	public List<Cuenta> obtenerTodasLasCuentasAhorro() throws SQLException {
		PreparedStatement statement;
		 ResultSet rs;
		 Connection conexion = Conexion.getConexion().getSQLConexion();
		 ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
		    
	    try {
	        statement = conexion.prepareStatement(obtenerCuentasAhorro);
	        rs = statement.executeQuery();
	        while (rs.next()) {
	            Cuenta cuenta = new Cuenta();

	            Cliente cliente = new Cliente();
	            cliente.setIdCliente(rs.getInt("clienteId"));
	            cliente.setDni(rs.getString("dni"));
	            cliente.setCuil(rs.getString("cuil"));
	            cliente.setNombre(rs.getString("clienteNombre"));
	            cliente.setApellido(rs.getString("clienteApellido"));
	            cliente.setSexo(rs.getString("sexo").charAt(0));
	            cliente.setNacionalidad(rs.getString("nacionalidad"));
	            cliente.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
	            cliente.setDireccion(rs.getString("direccion"));
	            cliente.setEmail(rs.getString("email"));
	            cliente.setTelefono(rs.getString("telefono"));

	            
	            cliente.setId(rs.getInt("usuarioId"));
	            cliente.setUsuario(rs.getString("usuarioNombre"));
	            cliente.setContrasenia(rs.getString("contrasenia"));
	            cliente.setEstado(rs.getBoolean("usuarioEstado"));
	            
	            Provincia provincia = new Provincia();
	            provincia.setNombre(rs.getString("provinciaNombre"));
	            cliente.setProvincia(provincia);

	            Localidad localidad = new Localidad();
	            localidad.setNombre(rs.getString("localidadNombre"));
	            cliente.setLocalidad(localidad); 

	            TipoCuenta tipoCuenta = new TipoCuenta();
	            tipoCuenta.setDescripcion(rs.getString("tipoCuentaDescripcion"));

	            cuenta.setId(rs.getInt("cuentaId"));
	            cuenta.setCliente(cliente);
	            cuenta.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
	            cuenta.setTipoCuenta(tipoCuenta);
	            cuenta.setCbu(rs.getString("cbu"));
	            cuenta.setSaldo(rs.getBigDecimal("saldo"));
	            cuenta.setEstado(rs.getBoolean("cuentaEstado"));

	            listaCuentas.add(cuenta);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }catch (Exception ex) {
			throw ex;
		}
	    return listaCuentas;
	}

	@Override
	public List<Cuenta> cuentasActivas(int idCliente) throws SQLException {
		PreparedStatement statement;
		ResultSet rs;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta> (); 
		
		try {
			statement= conexion.prepareStatement(listasActivas);
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
	public boolean afectarSaldo(int idCuenta, BigDecimal importe) throws SQLException {
		PreparedStatement statement;
		Connection conexion= Conexion.getConexion().getSQLConexion();
		  
		try {
			statement= conexion.prepareStatement(afectarSaldo);
			statement.setInt(2, idCuenta);
			statement.setBigDecimal(1, importe);
		   
		    int filasAfectadas= statement.executeUpdate();
		    if(filasAfectadas > 0) {
			    conexion.commit();
			    return true;
		    }
		    else {
		    	conexion.rollback();
		    	return false;
		    }
		}
		catch(SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}
	
	@Override
	public ArrayList<Cuenta> CuentasxClienteYEstado(int idCliente, boolean estado) throws SQLException {

		PreparedStatement statement;
		ResultSet rs;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		ArrayList<Cuenta> listaCuentasxEstado = new ArrayList<Cuenta> (); 

		try {
			statement= conexion.prepareStatement(cuentasxEstado);
			statement.setInt(1, idCliente);
			statement.setBoolean(2, estado);
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

				listaCuentasxEstado.add(cuenta);


			}

		}catch(SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
		return listaCuentasxEstado;
	}

	@Override
	public Cuenta obtenerCuentaPorId(int idCuenta) throws SQLException {
		Cuenta cuenta = new Cuenta();
		PreparedStatement statement;
		ResultSet rs;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = conexion.prepareStatement(obtenerCuentaPorId);
			statement.setInt(1, idCuenta);
			rs = statement.executeQuery();
			
			while (rs.next()) {				
				TipoCuenta tipoCuenta= new TipoCuenta();
				tipoCuenta.setId(rs.getInt("idTipoCuenta"));
				
				Cliente cliente = new Cliente();
				cliente.setIdCliente(rs.getInt("idCliente"));
				
				cuenta.setId(rs.getInt("id"));
				cuenta.setCliente(cliente);
				cuenta.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				cuenta.setTipoCuenta(tipoCuenta);
				cuenta.setCbu(rs.getString("cbu"));
				cuenta.setSaldo(rs.getBigDecimal("saldo"));
				cuenta.setEstado(rs.getBoolean("estado"));
			}
			
			return cuenta;
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public Cuenta obtenerUltimaCuenta(int idCliente) throws SQLException {
		PreparedStatement statement;
		ResultSet rs;
		Connection conexion = Conexion.getConexion().getSQLConexion();

		try {
			statement = conexion.prepareStatement(obtenerUltimaCuenta);
			statement.setInt(1, idCliente);
			rs = statement.executeQuery();
			Cuenta cuenta = new Cuenta();

			while (rs.next()) {

				TipoCuenta tipoCuenta = new TipoCuenta();
				tipoCuenta.setId(rs.getInt("idTipoCuenta"));

				Cliente cliente = new Cliente();
				cliente.setIdCliente(rs.getInt("idCliente"));

				cuenta.setId(rs.getInt("id"));
				cuenta.setCliente(cliente);
				cuenta.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				cuenta.setTipoCuenta(tipoCuenta);
				cuenta.setCbu(rs.getString("cbu"));
				cuenta.setSaldo(rs.getBigDecimal("saldo"));
				cuenta.setEstado(rs.getBoolean("estado"));

			}

			return cuenta;
		} catch (SQLException ex) {
			throw ex;
		} catch (Exception ex) {
			throw ex;
		}
	}
}
