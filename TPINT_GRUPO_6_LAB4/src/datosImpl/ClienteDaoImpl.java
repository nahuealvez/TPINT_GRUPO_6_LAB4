package datosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import datos.ClienteDao;
import datos.Conexion;
import dominio.Cliente;

public class ClienteDaoImpl implements ClienteDao{
	
	private static final String insert = "INSERT INTO clientes (`dni`, `cuil`, `nombre`, `apellido`, `sexo`, `nacionalidad`, `fechaNacimiento`, `idProvincia`, `idLocalidad`, `direccion`, `email`, `telefono`, `idUsuario`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String checkDni = "SELECT COUNT(*) from clientes where dni = ?";
	
	@Override
	public boolean insert(Cliente cliente) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		
		boolean isInsertExitoso = false;
		
		try {
			statement = conexion.prepareStatement(insert);
			statement.setString(1, cliente.getDni());
			statement.setString(2, cliente.getCuil());
			statement.setString(3, cliente.getNombre());
			statement.setString(4, cliente.getApellido());
			statement.setString(5, String.valueOf(cliente.getSexo()));
			statement.setString(6, cliente.getNacionalidad());
			statement.setDate(7, java.sql.Date.valueOf(cliente.getFechaNacimiento()));
			statement.setInt(8, cliente.getProvincia().getId());
			statement.setInt(9, cliente.getLocalidad().getId());
			statement.setString(10, cliente.getDireccion());
			statement.setString(11, cliente.getEmail());
			statement.setString(12, cliente.getTelefono());
			statement.setInt(13, cliente.getId());
			
			int filasAfectadas = statement.executeUpdate();
			
			if(filasAfectadas > 0) {
				conexion.commit();
				isInsertExitoso = true;
			} else {
	            conexion.rollback();
	        }
	    } 
		catch (SQLException e) {
	        e.printStackTrace();

	    }		
		return isInsertExitoso;
	}

	@Override
	public boolean existeDni(String dni) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = conexion.prepareStatement(checkDni);
			statement.setString(1, dni);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				return resultSet.getInt(1) > 0;
			}
		}
			catch(SQLException e){
				e.printStackTrace();
			}
		return false;
	}

}
