package presentacion.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.util.ArrayList;

import datos.ClienteDao;
import datosImpl.ClienteDaoImpl;
import dominio.Cliente;
import dominio.Localidad;
import dominio.Provincia;
import dominio.TipoUsuario;
import negocio.ClienteNegocio;
import negocioImpl.ClienteNegImpl;

@WebServlet("/ServletCliente")
public class ServletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnAgregarCliente")!= null)
		{
			Cliente cliente = new Cliente();
			Provincia provincia = new Provincia();
			Localidad localidad = new Localidad();
			TipoUsuario tipoUsuario = new TipoUsuario();
			
			cliente.setDni(request.getParameter("txtDni"));
			cliente.setCuil(request.getParameter("txtCuil"));
			cliente.setNombre(request.getParameter("txtNombre"));
			cliente.setApellido(request.getParameter("txtApellido"));
	        cliente.setFechaNacimiento(LocalDate.parse(request.getParameter("txtFechaNacimiento")));
	        cliente.setSexo(request.getParameter("ddlSexo").charAt(0));
	        cliente.setNacionalidad(request.getParameter("ddlNacionalidad"));
	        
	        provincia.setId(Integer.parseInt(request.getParameter("ddlProvincia")));
	        cliente.setProvincia(provincia);
	        
	        localidad.setId(Integer.parseInt(request.getParameter("ddlLocalidad")));
	        cliente.setLocalidad(localidad);
	        cliente.setDireccion(request.getParameter("txtDireccion"));
	        cliente.setEmail(request.getParameter("txtEmail"));
	        cliente.setTelefono(request.getParameter("txtTelefono"));
	        cliente.setUsuario(request.getParameter("txtUsuario"));       
	        cliente.setContrasenia(request.getParameter("txtClave"));
	        
	        tipoUsuario.setId(2);
	        cliente.setTipoUsuario(tipoUsuario);
	        
	        
	     // Mostrar los valores capturados en la consola (para propositos de prueba)
	        System.out.println("Datos del cliente capturados:");
	        System.out.println("DNI: " + cliente.getDni());
	        System.out.println("CUIL: " + cliente.getCuil());
	        System.out.println("Nombre: " + cliente.getNombre());
	        System.out.println("Apellido: " + cliente.getApellido());
	        System.out.println("Fecha de Nacimiento: " + cliente.getFechaNacimiento());
	        System.out.println("Sexo: " + cliente.getSexo());
	        System.out.println("Nacionalidad: " + cliente.getNacionalidad());
	        System.out.println("ID Provincia: " + cliente.getProvincia().getId());
	        System.out.println("ID Localidad: " + cliente.getLocalidad().getId());
	        System.out.println("Direccion: " + cliente.getDireccion());
	        System.out.println("Email: " + cliente.getEmail());
	        System.out.println("Telefono: " + cliente.getTelefono());
	        System.out.println("Usuario: " + cliente.getUsuario());
	        System.out.println("Contraseña: " + cliente.getContrasenia());
	        
	        String mensaje = null;
	        String claseMensaje = null;
	        
	     	try {
	     		ClienteNegocio negocioC= new ClienteNegImpl();
		        boolean aux=negocioC.crearCliente(cliente);
		        
		        mensaje = "El cliente fue agregado correctamente a la base";
		        claseMensaje = "alert alert-success";
	     	}
	     	catch (Exception e) {
	     		mensaje = "El cliente no se pudo agregar | " + e.getMessage();
	     		claseMensaje = "alert alert-danger";
	     	}
	        
	     	request.setAttribute("txtMensajeAgregarCliente", mensaje);
            request.setAttribute("claseMensajeAgregarCliente", claseMensaje);
	     	
            ArrayList<Cliente> clientes = cargarListaClientes();
            
            request.setAttribute("listaC", clientes);
	        request.getRequestDispatcher("/Clientes.jsp").forward(request, response);
		}
		
		if (request.getParameter("Param")!= null ) 
		{
			ArrayList<Cliente> clientes = cargarListaClientes();
			
			request.setAttribute("listaC", clientes);
			request.getRequestDispatcher("/Clientes.jsp").forward(request, response);
		}
		
	}

	private ArrayList<Cliente> cargarListaClientes() {
		ClienteNegocio negC= new ClienteNegImpl();
		ArrayList<Cliente> listadoCli = new ArrayList<Cliente>();
		listadoCli= (ArrayList<Cliente>) negC.listarClientes();
		
		return listadoCli;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnModificarCliente")!= null)
		{
			int idCliente = (int) request.getAttribute("idCliente");
		    ArrayList<Provincia> provincias = (ArrayList<Provincia>) request.getAttribute("provincias");
			
			ClienteNegocio negC = new ClienteNegImpl();
			Cliente cliente = negC.listarClienteXId(idCliente);

			request.setAttribute("clienteAModificar", cliente);
			request.setAttribute("provincias", provincias);
			
			request.getRequestDispatcher("/ModificarCliente.jsp").forward(request, response);
		}
		
	}

}
