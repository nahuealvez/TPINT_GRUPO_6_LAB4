package negocioImpl;

import java.util.ArrayList;

import datos.ProvinciaDao;
import datosImpl.ProvinciaDaoImpl;
import dominio.Provincia;
import negocio.ProvinciaNegocio;

public class ProvinciaNegImpl implements ProvinciaNegocio{
	private ProvinciaDao provinciaDao = new ProvinciaDaoImpl();

	@Override
	public ArrayList<Provincia> listarProvincias() {
		return provinciaDao.listarProvincias();
	}
	
}
