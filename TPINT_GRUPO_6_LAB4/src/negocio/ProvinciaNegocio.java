package negocio;

import java.util.ArrayList;

import dominio.Provincia;

public interface ProvinciaNegocio {
	public ArrayList<Provincia> listarProvincias();
	public Provincia obtenerProvinciaPorId(int id);
}
