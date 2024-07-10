package dominio;

import java.time.LocalDate;

public class Cuota {
	private int id;
	private Prestamo prestamo;
	private int nroCuota;
	private LocalDate fechaVencimiento;
	private boolean estadoPago;
	private Movimiento movimiento;
	
	public Cuota(int id, Prestamo prestamo, int nroCuota, LocalDate fechaVencimiento, boolean estadoPago, Movimiento movimiento) {
		super();
		this.id = id;
		this.prestamo = prestamo;
		this.nroCuota = nroCuota;
		this.fechaVencimiento = fechaVencimiento;
		this.estadoPago = estadoPago;
		this.movimiento = movimiento;
	}

	public Cuota () {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}
	
	public int getNroCuota() {
		return nroCuota;
	}

	public void setNroCuota(int nroCuota) {
		this.nroCuota = nroCuota;
	}

	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public boolean isEstadoPago() {
		return estadoPago;
	}

	public void setEstadoPago(boolean estadoPago) {
		this.estadoPago = estadoPago;
	}

	public Movimiento getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}

	@Override
	public String toString() {
        return "Cuota id: " + id +
                "\nPrestamo ID: " + prestamo.getId() +
                "\nCuota Nro: " + nroCuota +
                "\nCuota Fecha VTO: " + fechaVencimiento +
                "\nImporte Cuota: " + prestamo.getImporteMensual() +
                "\nCuota Paga: " + estadoPago;
	}
}
