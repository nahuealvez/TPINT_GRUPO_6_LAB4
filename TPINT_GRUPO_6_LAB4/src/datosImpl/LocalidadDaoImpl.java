package datosImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import datos.Conexion;
import datos.LocalidadDao;
import datos.ProvinciaDao;
import dominio.Localidad;
import dominio.Provincia;

public class LocalidadDaoImpl implements LocalidadDao {
	private static final String listarLocalidades = "SELECT L.Id, L.IdProvincia, L.Nombre FROM Localidades AS L";
	
	@Override
	public ArrayList<Localidad> listarLocalidades() {
		ArrayList<Localidad> localidades = new ArrayList<Localidad>();
		ProvinciaDao provinciaDao = new ProvinciaDaoImpl();
		
		try {
			PreparedStatement st;
			ResultSet rs;
			Conexion conexion = Conexion.getConexion();
			
			st = conexion.getSQLConexion().prepareStatement(listarLocalidades);
			rs = st.executeQuery();
			while (rs.next()) {
				Localidad localidad = new Localidad();
				localidad.setId(rs.getInt("L.Id"));
				Provincia provincia = new Provincia();
				provincia = provinciaDao.obtenerProvinciaPorId(rs.getInt("L.IdProvincia"));
				localidad.setProvincia(provincia);
				localidad.setNombre(rs.getString("L.Nombre"));
				localidades.add(localidad);
			}
			
			conexion.cerrarConexion();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return localidades;
	}
}
