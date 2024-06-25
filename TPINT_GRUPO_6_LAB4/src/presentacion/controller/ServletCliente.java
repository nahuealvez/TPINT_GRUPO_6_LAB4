package presentacion.controller;

import java.io.IOException;
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
	        	        
	     	        
	        ClienteNegocio negocioC= new ClienteNegImpl();
	        boolean aux=negocioC.crearCliente(cliente);  
	        if(aux) {
	            String resultado = "Agregado con Exito!";
	            request.setAttribute("txtMensajeAgregarCliente", resultado);
	        } else {
	            String resultado = "Error al agregar el cliente.";
	            request.setAttribute("txtMensajeAgregarCliente", resultado);
	        }
	        
	     // Redireccionar hacia AgregarCliente.jsp
	        request.getRequestDispatcher("/AgregarCliente.jsp").forward(request, response);
		}
		
		if (request.getParameter("Param")!= null ) 
		{
			ClienteNegocio negC= new ClienteNegImpl();
			ArrayList<Cliente> listadoCli = new ArrayList<Cliente>();
			listadoCli= (ArrayList<Cliente>) negC.listarClientes();
			
			request.setAttribute("listaC", listadoCli);
			request.getRequestDispatcher("/FrontClientes.jsp").forward(request, response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnModificarCliente")!= null)
		{
			String aux= request.getParameter("idCliente").toString();
			System.out.println(aux);
			int id = Integer.parseInt(request.getParameter("idCliente").toString());
			
			ClienteNegocio negC = new ClienteNegImpl();
			Cliente cliente = new Cliente();
			//cliente= negC.
			
			request.getRequestDispatcher("/AgregarCliente.jsp").forward(request, response);
		}
		
	}

}
