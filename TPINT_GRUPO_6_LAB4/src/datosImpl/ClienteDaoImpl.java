package datosImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datos.ClienteDao;
import datos.Conexion;
import dominio.Cliente;
import dominio.Localidad;
import dominio.Provincia;
import dominio.TipoUsuario;

public class ClienteDaoImpl implements ClienteDao{
	
	private static final String insert = "INSERT INTO clientes (`dni`, `cuil`, `nombre`, `apellido`, `sexo`, `nacionalidad`, `fechaNacimiento`, `idProvincia`, `idLocalidad`, `direccion`, `email`, `telefono`, `idUsuario`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String listarActivos = "SELECT c.id AS Id, c.dni AS DNI, c.cuil AS CUIL, c.nombre AS Nombre, c.apellido AS Apellido, c.sexo AS Sexo, c.nacionalidad AS Nacionalidad, c.fechaNacimiento AS FechaNacimiento, c.idProvincia AS IdProvincia, p.nombre AS NombreProvincia, c.idLocalidad AS IdLocalidad, l.nombre AS NombreLocalidad, c.direccion AS Direccion, c.email AS Email, c.telefono AS Telefono, c.idUsuario AS IdUsuario, u.usuario AS NombreUsuario, u.idTipoUsuario AS IdTipoUsuario, t.descripcion AS DescripcionTipoUsuario, u.estado AS Estado FROM clientes c INNER JOIN usuarios u ON c.idUsuario = u.id INNER JOIN tiposUsuarios t ON u.idTipoUsuario = t.id INNER JOIN localidades l ON c.idLocalidad = l.id INNER JOIN provincias p ON c.idProvincia = p.id WHERE u.estado = 1 AND u.idTipoUsuario = 2";
	private static final String actualizarRegistro = "UPDATE clientes SET dni = ?, cuil = ?, nombre = ?, apellido = ?, sexo = ?, nacionalidad = ?, fechaNacimiento = ?, idProvincia = ?, idLocalidad = ?, direccion = ?, email = ?, telefono = ? WHERE idUsuario = ?";
	private static final String existeDniConsulta = "SELECT COUNT(*) from clientes where dni = ?";
	private static final String existeDniConsultaModificar = "SELECT COUNT(*) FROM clientes WHERE dni = ? AND idUsuario != ?";
	
	
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
			statement.setDate(7, Date.valueOf(cliente.getFechaNacimiento()));
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
			statement = conexion.prepareStatement(existeDniConsulta);
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
	
	public boolean existeDni(String dni, int idUsuario) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = conexion.prepareStatement(existeDniConsultaModificar);
			statement.setString(1, dni);
			statement.setInt(2, idUsuario);
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

	@Override
	public List<Cliente> listarClientes() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(listarActivos);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				clientes.add(getCliente(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return clientes;
	}
	
	private Cliente getCliente(ResultSet resultSet) throws SQLException
	{
		Cliente cliente = new Cliente();
		
		//SE CREAN LOS OBJETOS QUE COMPONEN AL CLIENTE
		
		// TIPO USUARIO
		TipoUsuario tipoUsuario = new TipoUsuario();
		tipoUsuario.setId(resultSet.getInt("IdTipoUsuario"));
		tipoUsuario.setDescripcion(resultSet.getString("DescripcionTipoUsuario"));
		
		//PROVINCIA
		Provincia provincia = new Provincia();
		provincia.setId(resultSet.getInt("IdProvincia"));
		provincia.setNombre(resultSet.getString("NombreProvincia"));
		
		//LOCALIDAD
		Localidad localidad = new Localidad();
		localidad.setId(resultSet.getInt("IdLocalidad"));
		localidad.setNombre(resultSet.getString("NombreLocalidad"));
		localidad.setProvincia(provincia);
		
		cliente.setIdCliente(resultSet.getInt("Id"));
		cliente.setDni(resultSet.getString("DNI"));
		cliente.setCuil(resultSet.getString("CUIL"));
		cliente.setNombre(resultSet.getString("Nombre"));
		cliente.setApellido(resultSet.getString("Apellido"));
		cliente.setSexo(resultSet.getString("Sexo").charAt(0));
		cliente.setNacionalidad(resultSet.getString("Nacionalidad"));
		cliente.setFechaNacimiento(resultSet.getDate("FechaNacimiento").toLocalDate());
		cliente.setProvincia(provincia);
		cliente.setLocalidad(localidad);
		cliente.setDireccion(resultSet.getString("Direccion"));
		cliente.setEmail(resultSet.getString("Email"));
		cliente.setTelefono(resultSet.getString("Telefono"));
		cliente.setId(resultSet.getInt("IdUsuario"));
		cliente.setUsuario(resultSet.getString("NombreUsuario"));
		cliente.setTipoUsuario(tipoUsuario);
		cliente.setEstado(resultSet.getBoolean("Estado"));
		
		return cliente;
				
	}

	@Override
	public boolean update(Cliente cliente) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean modificarExitoso = false;
		
		try {
			statement = conexion.prepareStatement(actualizarRegistro);
			statement.setString(1, cliente.getDni());
			statement.setString(2, cliente.getCuil());
			statement.setString(3, cliente.getNombre());
			statement.setString(4, cliente.getApellido());
			statement.setString(5, String.valueOf(cliente.getSexo()));
			statement.setString(6, cliente.getNacionalidad());
			statement.setDate(7, Date.valueOf(cliente.getFechaNacimiento()));
	        statement.setInt(8, cliente.getProvincia().getId());
	        statement.setInt(9, cliente.getLocalidad().getId());
	        statement.setString(10, cliente.getDireccion());
	        statement.setString(11, cliente.getEmail());
	        statement.setString(12, cliente.getTelefono());
	        statement.setInt(13, cliente.getId());
			if(statement.executeUpdate() > 0) 
			{
				conexion.commit();
				modificarExitoso = true;
			}else {
	            conexion.rollback();
	        }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modificarExitoso;
	}

}