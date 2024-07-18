package negocioImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.sun.jmx.snmp.Timestamp;

import Excepciones.SinSaldoException;
import datos.CuotaDao;
import datosImpl.CuotaDaoImpl;
import dominio.Cuenta;
import dominio.Cuota;
import dominio.Movimiento;
import dominio.Prestamo;
import dominio.TipoMovimiento;
import negocio.CuentaNegocio;
import negocio.CuotaNegocio;

public class CuotaNegImpl implements CuotaNegocio {
	private CuotaDao cuotaDao = new CuotaDaoImpl();
	
	@Override
	public ArrayList<Cuota> listarCuotasPorPrestamo(int idPrestamo) throws SQLException {
		try {
			return cuotaDao.listarCuotasPrestamo(idPrestamo);
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public boolean generarCuotas(Prestamo prestamo) throws SQLException {
		Cuota cuota = new Cuota();
		try {
			LocalDate fechaProximoVto = LocalDate.now().plusDays(prestamo.getPlazoDePago());
			boolean cuotasGeneradas = false;
			for(int i = 1; i <= prestamo.getCuotas(); i++) {
				cuota.setPrestamo(prestamo);
				cuota.setNroCuota(i);
				cuota.setFechaVencimiento(fechaProximoVto);
				fechaProximoVto = fechaProximoVto.plusDays(prestamo.getPlazoDePago());
				cuotaDao.insert(cuota);
				if(i == prestamo.getCuotas()) {
					cuotasGeneradas = true;
				}
			}
			return cuotasGeneradas;
			
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public Cuota obtenerCuotaPorId(int idCuota) throws SQLException {
		try {
			return cuotaDao.obtenerCuotaPorId(idCuota);
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public boolean registrarPago(int idCuenta, Cuota cuota) throws Exception, SQLException {
		boolean registrado = false;		
		CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
		Movimiento movimiento = new Movimiento();
		TipoMovimiento tipoMovimiento = new TipoMovimiento();
		Cuenta cuenta = new Cuenta();
		
		tipoMovimiento.setId(3); // Pago de préstamo
		movimiento.setTipoMovimiento(tipoMovimiento);
		movimiento.setConcepto("Cuota Nro:" + cuota.getNroCuota() + " | Préstamo #" + cuota.getPrestamo().getId());
		cuenta.setId(idCuenta);
		movimiento.setCuenta(cuenta);
		movimiento.setImporte(cuota.getPrestamo().getImporteMensual());
		
		try {
			int idMovimientoPagoCuota = cuentaNegocio.debitarPagoCuotaPrestamo(idCuenta, movimiento);
			if (idMovimientoPagoCuota > 0) {
				movimiento.setId(idMovimientoPagoCuota);
				cuota.setMovimiento(movimiento);
				LocalDateTime fechaPago = LocalDateTime.now();
				cuota.setFechaPago(fechaPago);
				cuotaDao.registrarPago(cuota);
				registrado = true;
			}
		}
		catch (SinSaldoException ex) {
			throw ex;
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			throw ex;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return registrado;
	}

	@Override
	public ArrayList<Cuota> listarCuotasPagadas(int idPrestamo) throws SQLException {
		try {
			return cuotaDao.listarCuotasPagadas(idPrestamo);
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ArrayList<Cuota> listarCuotasPendientes(int idPrestamo) throws SQLException {
		try {
			return cuotaDao.listarCuotasPendientes(idPrestamo);
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public int contarCuotasPagadas(int idPrestamo) throws SQLException {
		try {
			return cuotaDao.contarCuotasPagadas(idPrestamo);
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public int contarCuotasPendientes(int idPrestamo) throws SQLException {
		try {
			return cuotaDao.contarCuotasPendientes(idPrestamo);
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public int contarCuotas(int idPrestamo) throws SQLException {
		try {
			return cuotaDao.contarCuotas(idPrestamo);
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public BigDecimal sumarCuotasPagadas(int idPrestamo) throws SQLException {
		try {
			return cuotaDao.sumarCuotasPagadas(idPrestamo);
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public BigDecimal sumarCuotasPendientes(int idPrestamo) throws SQLException {
		try {
			return cuotaDao.sumarCuotasPendientes(idPrestamo);
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public BigDecimal sumarCuotas(int idPrestamo) throws SQLException {
		try {
			return cuotaDao.sumarCuotas(idPrestamo);
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

}
