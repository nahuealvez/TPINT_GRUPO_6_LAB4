package datosImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import datos.Conexion;
import datos.ProvinciaDao;
import dominio.Provincia;

public class ProvinciaDaoImpl implements ProvinciaDao {
	private static final String listarProvincias = "SELECT P.Id, P.Nombre FROM Provincias AS P";

	@Override
	public ArrayList<Provincia> listarProvincias() {
		ArrayList<Provincia> provincias = new ArrayList<Provincia>();
		
		try {
			PreparedStatement st;
			ResultSet rs;
			Conexion conexion = Conexion.getConexion();
			
			st = conexion.getSQLConexion().prepareStatement(listarProvincias);
			rs = st.executeQuery();
			while (rs.next()) {
				Provincia provincia = new Provincia();
				provincia.setId(rs.getInt("P.Id"));
				provincia.setNombre(rs.getString("P.Nombre"));
				provincias.add(provincia);
			}
			
			conexion.cerrarConexion();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return provincias;
	}
}
