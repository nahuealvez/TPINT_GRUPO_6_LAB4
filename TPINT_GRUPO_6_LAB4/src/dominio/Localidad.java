package dominio;

public class Localidad {
	private int id;
	private Provincia provincia;
	private String nombre;
	
	public Localidad(int id, Provincia provincia, String nombre) {
		this.id = id;
		this.provincia = provincia;
		this.nombre = nombre;
	}
	
	public Localidad() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Localidad [id=" + id + ", provincia=" + provincia + ", nombre=" + nombre + "]";
	}
}
