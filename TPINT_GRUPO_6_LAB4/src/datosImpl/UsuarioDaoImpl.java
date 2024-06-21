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

	private static final String readUsuario = "SELECT id, usuario, contrasenia, idTipoUsuario, estado FROM usuarios WHERE usuario = ? AND contrasenia = ?";	
	
	
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