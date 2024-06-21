package dominio;

import java.time.LocalDate;

public class Cliente {
	private int id;
	private String dni;
	private String cuil;
	private String nombre;
	private String apellido;
	private char sexo;
	private String nacionalidad;
	private LocalDate fechaNacimiento;
	private Provincia provincia;
	private Localidad localidad;
	private String direccion;
	private String email;
	private String telefono;
	private Usuario usuario;
	
	public Cliente(int id, String dni, String cuil, String nombre, String apellido, char sexo, String nacionalidad, LocalDate fechaNacimiento, Provincia provincia, Localidad localidad, String direccion, String email, String telefono, Usuario usuario) {
		this.id = id;
		this.dni = dni;
		this.cuil = cuil;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		this.provincia = provincia;
		this.localidad = localidad;
		this.direccion = direccion;
		this.email = email;
		this.telefono = telefono;
		this.usuario = usuario;
	}
	
	public Cliente() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return String.format(
				"Cliente [id=%s, dni=%s, cuil=%s, nombre=%s, apellido=%s, sexo=%s, nacionalidad=%s, fechaNacimiento=%s, provincia=%s, localidad=%s, direccion=%s, email=%s, telefono=%s, usuario=%s, estado=%s]",
				id, dni, cuil, nombre, apellido, sexo, nacionalidad, fechaNacimiento, provincia, localidad, direccion,
				email, telefono, usuario);
	}
}
