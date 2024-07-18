package datosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Excepciones.ErrorUsuarioDesactivado;
import Excepciones.ErrorUsuarioException;
import datos.Conexion;
import datos.UsuarioDao;
import dominio.TipoUsuario;
import dominio.Usuario;


public class UsuarioDaoImpl implements UsuarioDao{

	private static final String insertUsuario = "INSERT INTO usuarios (idTipoUsuario, usuario, contrasenia, estado) values (?,?,?,1)";
	private static final String actualizarEstadoUsuario = "UPDATE usuarios SET estado = ? WHERE id = ?";
	private static final String readUsuario = "SELECT id, usuario, contrasenia, idTipoUsuario, estado FROM usuarios WHERE usuario = ? AND contrasenia = ?";	
	private static final String actualizarContraseniaUsuario = "UPDATE usuarios SET contrasenia = ? WHERE id = ?";
	private static final String actualizarNombreUsuario = "UPDATE usuarios SET usuario = ? WHERE id = ?";

	
	
	@Override
	public int crearUsuario(Usuario usuario) {
		PreparedStatement statement = null;
	    Connection conexion = Conexion.getConexion().getSQLConexion();
	    ResultSet generatedKeys = null;
	    int idNuevoUsuario = -1;

	    try {
	        
	        statement = conexion.prepareStatement(insertUsuario, PreparedStatement.RETURN_GENERATED_KEYS); // Se utiliza RETURN_GENERATED_KEYS para que devuelva el id generado por la BBDD
	        statement.setInt(1, usuario.getTipoUsuario().getId());
	        statement.setString(2, usuario.getUsuario());
	        statement.setString(3, usuario.getContrasenia());

	        int affectedRows = statement.executeUpdate();

	        if (affectedRows > 0) {
	            conexion.commit();
	            generatedKeys = statement.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                idNuevoUsuario = generatedKeys.getInt(1);
	            }
	        } else {
	            conexion.rollback();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();

	    }
	    System.out.println(idNuevoUsuario);
	    return idNuevoUsuario;
	}
	
	@Override
	public Usuario verificarUsuario(String usuario, String contrasenia) throws ErrorUsuarioException, ErrorUsuarioDesactivado  {
		
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		Usuario user = null;
		
		try {
			statement = conexion.prepareStatement(readUsuario);
			statement.setString(1, usuario);
			statement.setString(2, contrasenia);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				user = new Usuario();
				user.setId(rs.getInt("id"));
				user.setUsuario(rs.getString("usuario"));
				user.setContrasenia(rs.getString("contrasenia"));
				
				TipoUsuario tipoUsuario = new TipoUsuario();
				tipoUsuario.setId(rs.getInt("idTipoUsuario"));
				user.setTipoUsuario(tipoUsuario);
				
				boolean estado = rs.getBoolean("estado");
				user.setEstado(estado);
				
				if(estado == false) {
					throw new ErrorUsuarioDesactivado();
				}
				
			}
			if(user==null) {
				throw new ErrorUsuarioException();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			throw e;
		}
		
		return user;
		
	}

	@Override
	public boolean actualizarEstadoUsuario(int idUsuario, boolean nuevoEstado) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean actualizacionExitosa = false;
		
		try 
		{
			statement = conexion.prepareStatement(actualizarEstadoUsuario);
			statement.setBoolean(1, nuevoEstado);
			statement.setInt(2, idUsuario);
			
			int registrosActualizados = statement.executeUpdate();
			
			if(registrosActualizados > 0)
			{
				conexion.commit();
				actualizacionExitosa = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return actualizacionExitosa;
		
	}

	@Override
	public boolean actualizarContraseniaUsuario(int idUsurio, String contrasenia) {
		
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean actualizacionExitosa = false;
		
		try 
		{
			statement = conexion.prepareStatement(actualizarContraseniaUsuario);
			statement.setString(1, contrasenia);
			statement.setInt(2, idUsurio);
			
			int registrosActualizados = statement.executeUpdate();
			
			if(registrosActualizados > 0)
			{
				conexion.commit();
				actualizacionExitosa = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return actualizacionExitosa;
		
	}

	@Override
	public boolean actualizarNombreUsuario(int idUsurio, String usuario) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean actualizacionExitosa = false;
		
		try 
		{
			statement = conexion.prepareStatement(actualizarNombreUsuario);
			statement.setString(1, usuario);
			statement.setInt(2, idUsurio);
			
			int registrosActualizados = statement.executeUpdate();
			
			if(registrosActualizados > 0)
			{
				conexion.commit();
				actualizacionExitosa = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return actualizacionExitosa;
	}


}
