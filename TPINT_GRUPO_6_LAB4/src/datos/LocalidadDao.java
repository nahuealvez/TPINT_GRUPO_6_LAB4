package datos;

import java.util.ArrayList;

import dominio.Localidad;

public interface LocalidadDao {
	public ArrayList<Localidad> listarLocalidades();
	public ArrayList<Localidad> listarLocalidadesPorProvincia(int idProvincia);
}
