package datos;

import java.util.ArrayList;

import dominio.Provincia;

public interface ProvinciaDao {
	public ArrayList<Provincia> listarProvincias();
	public Provincia obtenerProvinciaPorId(int id);
}
