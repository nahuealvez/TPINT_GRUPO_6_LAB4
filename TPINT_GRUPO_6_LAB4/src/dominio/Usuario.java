package dominio;

public class Usuario {
	private int id;
	private TipoUsuario tipoUsuario;
	private String usuario;
	private String contrasenia;
	private boolean estado;
	
	public Usuario(int id, TipoUsuario tipoUsuario, String usuario, String contrasenia, boolean estado) {
		this.id = id;
		this.tipoUsuario = tipoUsuario;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.estado = estado;
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

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return String.format("Usuario [id=%s, tipoUsuario=%s, usuario=%s, contrasenia=%s, estado=%s]", id, tipoUsuario,
				usuario, contrasenia, estado);
	}
}
