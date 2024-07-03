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
import negocio.UsuarioNegocio;
import negocioImpl.ClienteNegImpl;
import negocioImpl.UsuarioNegImpl;

@WebServlet("/ServletCliente")
public class ServletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnAgregarCliente")!= null)
		{
			eventobtnAgregarCliente(request, response);
		}
		if (request.getParameter("Param")!= null ) 
		{
			cargarListaClientes(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnModificarCliente")!= null)
		{
			eventobtnModificarCliente(request, response);
		}
		if(request.getParameter("btnAgregarModificacionCliente")!= null)
		{
			eventobtnAgregarModificacionCliente(request, response);
		}
		
		if(request.getParameter("btnDesactivarCliente")!= null)  
		{
			
			int idDesactivar =(Integer.parseInt(request.getParameter("idClienteDesactivar")));
			
			System.out.println("Botón 'Desactivar' Funciona: "+idDesactivar);
			try 
			{
				UsuarioNegocio negU= new UsuarioNegImpl();
				negU.actualizarEstadoUsuario(idDesactivar, false);
				
			} 
			catch (Exception e) 
			{
				
			}
			cargarListaClientes(request, response);
		}
		
		if(request.getParameter("btnActivarCliente")!= null)
		{
			
			int idDesactivar =(Integer.parseInt(request.getParameter("idClienteActivar")));
			
			System.out.println("Botón 'Desactivar' Funciona: "+idDesactivar);
			try 
			{
				UsuarioNegocio negU= new UsuarioNegImpl();
				negU.actualizarEstadoUsuario(idDesactivar, true);
				
			} 
			catch (Exception e) 
			{
				
			}
			
			cargarListaClientes(request, response);
		}
		
	}
	//-------------------------------Metodos-------------------------------------
	private ArrayList<Cliente> cargarListaClientes() {
		ClienteNegocio negC= new ClienteNegImpl();
		ArrayList<Cliente> listadoCli = new ArrayList<Cliente>();
		listadoCli= (ArrayList<Cliente>) negC.listarClientes();
		
		return listadoCli;
	}
	
	public void eventobtnAgregarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
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
     	
        cargarListaClientes(request, response);
	}
	
	public void cargarListaClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ArrayList<Cliente> clientes = cargarListaClientes();
        request.setAttribute("listaC", clientes);
        request.getRequestDispatcher("/Clientes.jsp").forward(request, response);
	}
	
	public void eventobtnModificarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int idCliente = (int) request.getAttribute("idCliente");
	    ArrayList<Provincia> provincias = (ArrayList<Provincia>) request.getAttribute("provincias");
		
		ClienteNegocio negC = new ClienteNegImpl();
		Cliente cliente = negC.listarClienteXId(idCliente);

		request.setAttribute("clienteAModificar", cliente);
		request.setAttribute("provincias", provincias);
		
		request.getRequestDispatcher("/ModificarCliente.jsp").forward(request, response);
	}
	
	public void eventobtnAgregarModificacionCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		Cliente cliente = new Cliente();
		Provincia provincia = new Provincia();
		Localidad localidad = new Localidad();
		TipoUsuario tipoUsuario = new TipoUsuario();
		
		
		cliente.setIdCliente(Integer.parseInt(request.getParameter("clienteId")));
		cliente.setId(Integer.parseInt(request.getParameter("usuarioId")));
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
        
        //--------------------- Usuario---------------------------
        cliente.setUsuario(request.getParameter("txtUsuario"));       
        cliente.setContrasenia(request.getParameter("txtClave"));
       
        tipoUsuario.setId(2);
        cliente.setTipoUsuario(tipoUsuario);
        
        String mensaje = null;
        String claseMensaje = null;
        
        try {
        	ClienteNegocio negC = new ClienteNegImpl();
			boolean modificadoC = negC.modificarCliente(cliente);
			
			UsuarioNegocio negU = new UsuarioNegImpl();
			boolean modificadoU = negU.actualizarContraseniaUsuario(cliente.getId(), cliente.getContrasenia());
			
			mensaje = "El cliente fue modificado correctamente";
	        claseMensaje = "alert alert-success";
		}
        catch (Exception e) 
        {
        	mensaje = "El cliente no se pudo modificar | " + e.getMessage();
     		claseMensaje = "alert alert-danger";
		}       
        request.setAttribute("txtMensajeAgregarCliente", mensaje);
        request.setAttribute("claseMensajeAgregarCliente", claseMensaje);
        
        cargarListaClientes(request, response);
	}
	
}
