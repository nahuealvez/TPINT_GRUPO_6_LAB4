package negocioImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datos.CuentaDao;
import datosImpl.CuentaDaoImpl;
import dominio.Cuenta;
import negocio.CuentaNegocio;

public class CuentaNegocioImpl implements CuentaNegocio  {

		private CuentaDao cuentaDao= new CuentaDaoImpl();
		
	
	@Override
	public boolean insert(Cuenta cuenta) throws SQLException {
		
		return cuentaDao.insert(cuenta);
	}

	@Override
	public List<Cuenta> cuentasXCliente(int idCliente) throws SQLException {
		
		return cuentaDao.cuentasXCliente(idCliente);
	}

	@Override
	public boolean actualizarEstado(int idCuenta, boolean estado)  throws SQLException{
		
		return cuentaDao.actualizarEstado(idCuenta, estado);
	}

	@Override
	public boolean verificarCbu(String cbu) throws SQLException {
		
		return cuentaDao.verificarCbu(cbu);
	}

	@Override
	public String nuevoCbu() throws SQLException {
		
		return cuentaDao.nuevoCbu();
	}

	@Override
	public boolean verificarEstado(int idCuenta) throws SQLException {
		
		return cuentaDao.verificarEstado(idCuenta);
	}

	@Override
	public int contarCuentasActivas(List<Cuenta> listaCuentas) throws SQLException {
		
		return cuentaDao.contarCuentasActivas(listaCuentas);
	}

	@Override
	public List<Cuenta> cuentasPorClienteActivas(int idCliente) throws SQLException {
		return cuentaDao.cuentasPorClienteActivas(idCliente);
	}
}
