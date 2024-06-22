package datosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import datos.Conexion;
import datos.UsuarioDao;
import dominio.TipoUsuario;
import dominio.Usuario;

public class UsuarioDaoImpl implements UsuarioDao{

	private static final String insertUsuario = "INSERT INTO usuarios (idTipoUsuario, usuario, contrasenia, estado) values (?,?,?,1)";
	private static final String readUsuario = "SELECT id, usuario, contrasenia, idTipoUsuario, estado FROM usuarios WHERE usuario = ? AND contrasenia = ?";	
	
	
	
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
	        try {
	            if (conexion != null) {
	                conexion.rollback();
	            }
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	    } finally {
	        try {
	            if (generatedKeys != null) {
	                generatedKeys.close();
	            }
	            if (statement != null) {
	                statement.close();
	            }
	            if (conexion != null) {
	                conexion.close();
	            }
	        } catch (SQLException e2) {
	            e2.printStackTrace();
	        }
	    }
	    return idNuevoUsuario;
	}
	
	@Override
	public Usuario verificarUsuario(String usuario, String pass) {
		
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		Usuario user = new Usuario();
		
		try {
			statement = conexion.prepareStatement(readUsuario);
			statement.setString(1, usuario);
			statement.setString(2, pass);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				
				user.setId(rs.getInt("id"));
				user.setUsuario(rs.getString("usuario"));
				user.setContrasenia(rs.getString("contrasenia"));
				
				TipoUsuario tipoUsuario = new TipoUsuario();
				tipoUsuario.setId(rs.getInt("idTipoUsuario"));
				user.setTipoUsuario(tipoUsuario);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return user;
		
	}



	
	
	


}
