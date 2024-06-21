package dominio;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Movimiento {
	private int id;
	private LocalDateTime fecha;
	private TipoMovimiento tipoMovimiento;
	private String concepto;
	private Cuenta cuenta;
	private BigDecimal importe;
	
	public Movimiento(int id, LocalDateTime fecha, TipoMovimiento tipoMovimiento, String concepto, Cuenta cuenta, BigDecimal importe) {
		this.id = id;
		this.fecha = fecha;
		this.tipoMovimiento = tipoMovimiento;
		this.concepto = concepto;
		this.cuenta = cuenta;
		this.importe = importe;
	}
	
	public Movimiento() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public TipoMovimiento getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	@Override
	public String toString() {
		return "Movimiento [id=" + id + ", fecha=" + fecha + ", tipoMovimiento=" + tipoMovimiento + ", concepto="
				+ concepto + ", cuenta=" + cuenta + ", importe=" + importe + "]";
	}
}
