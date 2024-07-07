package dominio;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Prestamo {
	private int id;
	private Cliente cliente;
	private Cuenta cuenta;
	private LocalDateTime fechaSolicitud;
	private BigDecimal importeAPagar;
	private int plazoDePago;
	private BigDecimal importePedido;
	private int cuotas;
	private BigDecimal importeMensual;
	private Boolean estadoValidacion;
	private LocalDateTime fechaValidacion;
	
	public Prestamo(int id, Cliente cliente, Cuenta cuenta, LocalDateTime fechaSolicitud, BigDecimal importeAPagar, int plazoDePago, BigDecimal importePedido, int cuotas, BigDecimal importeMensual, Boolean estadoValidacion, LocalDateTime fechaValidacion) {
		this.id = id;
		this.cliente = cliente;
		this.cuenta = cuenta;
		this.fechaSolicitud = fechaSolicitud;
		this.importeAPagar = importeAPagar;
		this.plazoDePago = plazoDePago;
		this.importePedido = importePedido;
		this.cuotas = cuotas;
		this.importeMensual = importeMensual;
		this.estadoValidacion = estadoValidacion;
		this.fechaValidacion = fechaValidacion;
	}
	
	public Prestamo() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public LocalDateTime getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public BigDecimal getImporteAPagar() {
		return importeAPagar;
	}

	public void setImporteAPagar(BigDecimal importeAPagar) {
		this.importeAPagar = importeAPagar;
	}

	public int getPlazoDePago() {
		return plazoDePago;
	}

	public void setPlazoDePago(int plazoDePago) {
		this.plazoDePago = plazoDePago;
	}

	public BigDecimal getImportePedido() {
		return importePedido;
	}

	public void setImportePedido(BigDecimal importePedido) {
		this.importePedido = importePedido;
	}

	public int getCuotas() {
		return cuotas;
	}

	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}

	public BigDecimal getImporteMensual() {
		return importeMensual;
	}

	public void setImporteMensual(BigDecimal importeMensual) {
		this.importeMensual = importeMensual;
	}

	public Boolean isEstadoValidacion() {
		return estadoValidacion;
	}

	public void setEstadoValidacion(Boolean estadoValidacion) {
		this.estadoValidacion = estadoValidacion;
	}

	public LocalDateTime getFechaValidacion() {
		return fechaValidacion;
	}

	public void setFechaValidacion(LocalDateTime fechaValidacion) {
		this.fechaValidacion = fechaValidacion;
	}

	@Override
	public String toString() {
		return "Prestamo [id=" + id + ", cliente=" + cliente + ", cuenta=" + cuenta + ", fechaSolicitud="
				+ fechaSolicitud + ", importeAPagar=" + importeAPagar + ", plazoDePago=" + plazoDePago
				+ ", importePedido=" + importePedido + ", cuotas=" + cuotas + ", importeMensual=" + importeMensual
				+ ", estadoValidacion=" + estadoValidacion + ", fechaValidacion=" + fechaValidacion + "]";
	}
}
