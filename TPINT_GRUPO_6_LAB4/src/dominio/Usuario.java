package dominio;

public class Usuario {
	private int id;
	private TipoUsuario tipoUsuario;
	private String usuario;
	private String contrasenia;
	
	public Usuario(int id, TipoUsuario tipoUsuario, String usuario, String contrasenia) {
		this.id = id;
		this.tipoUsuario = tipoUsuario;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
	}
	
	public Usuario() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", tipoUsuario=" + tipoUsuario + ", usuario=" + usuario + ", contrasenia="
				+ contrasenia + "]";
	}
}
