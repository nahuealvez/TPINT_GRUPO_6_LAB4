package negocioImpl;

import java.util.ArrayList;

import datos.LocalidadDao;
import datosImpl.LocalidadDaoImpl;
import dominio.Localidad;
import negocio.LocalidadNegocio;

public class LocalidadNegImpl implements LocalidadNegocio {
	private LocalidadDao localidadDao = new LocalidadDaoImpl();
	
	@Override
	public ArrayList<Localidad> listarLocalidades() {
		return localidadDao.listarLocalidades();
	}

}
