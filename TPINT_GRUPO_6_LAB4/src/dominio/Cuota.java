package dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Cuota {
	private int id;
	private Prestamo prestamo;
	private int nroCuota;
	private LocalDate fechaVencimiento;
	private LocalDateTime fechaPago;
	private Movimiento movimiento;
	
	public Cuota(int id, Prestamo prestamo, int nroCuota, LocalDate fechaVencimiento, LocalDateTime estadoPago, Movimiento movimiento) {
		super();
		this.id = id;
		this.prestamo = prestamo;
		this.nroCuota = nroCuota;
		this.fechaVencimiento = fechaVencimiento;
		this.fechaPago = estadoPago;
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

	public LocalDateTime getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(LocalDateTime fechaPago) {
		this.fechaPago = fechaPago;
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
                "\nCuota Paga: " + fechaPago;
	}
}
