package negocio;

import java.util.ArrayList;

import dominio.Localidad;

public interface LocalidadNegocio {
	public ArrayList<Localidad> listarLocalidades();
	public ArrayList<Localidad> listarLocalidadesPorProvincia(int idProvincia);
}
