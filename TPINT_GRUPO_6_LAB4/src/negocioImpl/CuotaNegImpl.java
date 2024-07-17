package negocioImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import datos.CuotaDao;
import datosImpl.CuotaDaoImpl;
import dominio.Cuota;
import dominio.Prestamo;
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

}
