package dominio;

public class TipoCuenta {
	private int id;
	private String descripcion;
	
	public TipoCuenta(int id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}
	
	public TipoCuenta() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "TipoCuenta [id=" + id + ", descripcion=" + descripcion + "]";
	}
}
