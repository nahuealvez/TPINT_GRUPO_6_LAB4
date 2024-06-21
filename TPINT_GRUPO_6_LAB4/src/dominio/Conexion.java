package dominio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	public static Conexion instancia;
	private Connection connection;
	
	private String bd = "jdbc:mysql://localhost:3306/piggybank";
	private String usuario = "admin";
	private String contrasenia = "admin";
	
	private Conexion() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(bd, usuario, contrasenia);
			this.connection.setAutoCommit(false);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Conexion getConexion() {
		if (instancia == null) {
			instancia = new Conexion();
		}
		return instancia;
	}
	
	public Connection getSQLConexion() {
		return this.connection;
	}
	
	public void cerrarConexion() {
		try {
			this.connection.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		instancia = null;
	}
}
