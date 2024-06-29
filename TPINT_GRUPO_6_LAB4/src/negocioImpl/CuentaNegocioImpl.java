package negocioImpl;

import java.util.List;

import datos.CuentaDao;
import datosImpl.CuentaDaoImpl;
import dominio.Cuenta;
import negocio.CuentaNegocio;

public class CuentaNegocioImpl implements CuentaNegocio {

		private CuentaDao cuentaDao= new CuentaDaoImpl();
		
	
	@Override
	public boolean insert(Cuenta cuenta) {
		
		return cuentaDao.insert(cuenta);
	}

	@Override
	public List<Cuenta> cuentasXCliente(int idCliente) {
		
		return cuentaDao.cuentasXCliente(idCliente);
	}

}
