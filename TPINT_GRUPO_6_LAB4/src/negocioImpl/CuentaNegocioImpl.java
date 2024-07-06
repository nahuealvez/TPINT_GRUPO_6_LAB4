package negocioImpl;

import java.sql.SQLException;
import java.util.ArrayList;
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

	@Override
	public boolean actualizarEstado(int idCuenta, boolean estado) {
		
		return cuentaDao.actualizarEstado(idCuenta, estado);
	}

	@Override
	public boolean verificarCbu(String cbu) {
		
		return cuentaDao.verificarCbu(cbu);
	}

	@Override
	public String nuevoCbu() {
		
		return cuentaDao.nuevoCbu();
	}

	@Override
	public boolean verificarEstado(int idCuenta) {
		
		return cuentaDao.verificarEstado(idCuenta);
	}

	@Override
	public int contarCuentasActivas(List<Cuenta> listaCuentas) {
		
		return cuentaDao.contarCuentasActivas(listaCuentas);
	}

	@Override
	public List<Cuenta> cuentasPorClienteActivas(int idCliente) throws SQLException {
		return cuentaDao.cuentasPorClienteActivas(idCliente);
	}
}
