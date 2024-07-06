package dominio;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Cuenta {
	private int id;
	private Cliente cliente;
	private LocalDateTime fechaCreacion;
	private TipoCuenta tipoCuenta;
    private String cbu;
    private BigDecimal saldo;
    private boolean estado;
    
    public Cuenta(int id, Cliente cliente, LocalDateTime fechaCreacion, TipoCuenta tipoCuenta, String cbu, BigDecimal saldo, boolean estado) {
    	this.id = id;
    	this.cliente = cliente;
    	this.fechaCreacion = fechaCreacion;
    	this.tipoCuenta = tipoCuenta;
    	this.cbu = cbu;
    	this.saldo = saldo;
    	this.estado = estado;
    }
    
    public Cuenta() {
    	
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

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getCbu() {
		return cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cuenta [id=" + id + ", cliente=" + cliente + ", fechaCreacion=" + fechaCreacion + ", tipoCuenta="
				+ tipoCuenta + ", cbu=" + cbu + ", saldo=" + saldo + ", estado=" + estado + "]";
	}
	
	public String toStringResumido() {
		return tipoCuenta.getDescripcion() + " || " + id;
	}
}
