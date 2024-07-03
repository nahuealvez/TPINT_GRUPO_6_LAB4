package dominio;

import java.time.LocalDate;

public class Cuota {
	private int id;
	private Prestamo prestamo;
	private LocalDate fechaVencimiento;
	private boolean estadoPago;
	private Movimiento movimiento;
	
	public Cuota (int id, Prestamo prestamo, LocalDate fechaVencimiento, boolean estadoPago, Movimiento movimiento) {
		this.id = id;
		this.prestamo = prestamo;
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
		return String.format("Cuota [id=%s, prestamo=%s, fechaVencimiento=%s, estadoPago=%s, movimiento=%s]", id,
				prestamo, fechaVencimiento, estadoPago, movimiento);
	}
}
