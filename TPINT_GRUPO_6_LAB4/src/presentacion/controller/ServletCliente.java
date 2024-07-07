package presentacion.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Excepciones.ErrorMensajeException;

import java.time.LocalDate;
import java.util.ArrayList;

import datos.ClienteDao;
import datosImpl.ClienteDaoImpl;
import dominio.Cliente;
import dominio.Localidad;
import dominio.Provincia;
import dominio.TipoUsuario;
import dominio.Usuario;
import negocio.ClienteNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.ClienteNegImpl;
import negocioImpl.UsuarioNegImpl;

@WebServlet("/ServletCliente")
public class ServletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramValue = request.getParameter("Param");
		
		if (request.getParameter("btnVerCliente") != null) 
		{
			eventobtnbtnVerCliente(request, response);
		}
		if(request.getParameter("btnAgregarCliente")!= null)
		{
			eventobtnAgregarCliente(request, response);
		}
		if ("1".equals(paramValue))
		{
			cargarListaClientes(request, response);
		}
		else if ("2".equals(paramValue))
		{
			eventoVerPerfilParam2(request, response);
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
				 e.printStackTrace();
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
				 e.printStackTrace();
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
        boolean existeMensaje = false;
        
     	try {
     		ClienteNegocio negocioC= new ClienteNegImpl();
	        boolean aux=negocioC.crearCliente(cliente);
	        
	        mensaje = "El cliente fue agregado correctamente a la base";
	        claseMensaje = "alert alert-success";
	        existeMensaje = true;
     	}
     	catch(ErrorMensajeException e) 
     	{
     		mensaje =  e.getMessage();
     		claseMensaje = "alert alert-danger";
     		existeMensaje = true;
     	}
     	catch (Exception e) {
     		e.printStackTrace();
     	}
        
     	request.setAttribute("txtMensajeAgregarCliente", mensaje);
        request.setAttribute("claseMensajeAgregarCliente", claseMensaje);
        request.setAttribute("existeMensaje", existeMensaje);
     	
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
        boolean existeMensaje = false;
        
        try {
        	ClienteNegocio negC = new ClienteNegImpl();
			boolean modificadoC = negC.modificarCliente(cliente);
			
			if(modificadoC) {
                UsuarioNegocio negU = new UsuarioNegImpl();
                boolean modificadoU = negU.actualizarContraseniaUsuario(cliente.getId(), cliente.getContrasenia());
            }
			
			mensaje = "El cliente fue modificado correctamente";
	        claseMensaje = "alert alert-success";
	        existeMensaje = true;
		}
        catch (ErrorMensajeException e) 
        {
        	mensaje = e.getMessage();
            claseMensaje = "alert alert-danger";
            existeMensaje = true;
        }
        catch (Exception e) 
        {
        	e.printStackTrace();
		}       
        request.setAttribute("txtMensajeAgregarCliente", mensaje);
        request.setAttribute("claseMensajeAgregarCliente", claseMensaje);
        request.setAttribute("existeMensaje", existeMensaje);
        
        cargarListaClientes(request, response);
	}
	public void eventobtnbtnVerCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		 int idVerCliente = Integer.parseInt(request.getParameter("IdVerCliente"));
		    ClienteNegocio negC = new ClienteNegImpl();

		    try 
		    {
		        Cliente verCliente = negC.buscarClienteXidUsuario(idVerCliente);
		        request.setAttribute("verCliente", verCliente);
		    } 
		    catch 
		    (Exception e) 
		    {
		        e.printStackTrace();
		    }
		    request.getRequestDispatcher("/VerCliente.jsp").forward(request, response);
	}
	
	public void eventoVerPerfilParam2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession(false); // Obtiene la sesion sin crear una nueva si no existe
		if (session != null) 
		{
			Usuario usuarioLogueado= (Usuario)session.getAttribute("sessionUsuario");
		    if (usuarioLogueado != null)
		    {
		    	System.out.println("El id es!: " + usuarioLogueado.getId());
		    	ClienteNegocio negC = new ClienteNegImpl();
		    	try 
		    	{
		    		Cliente verCliente= negC.buscarClienteXidUsuario(usuarioLogueado.getId());
		    		request.setAttribute("verCliente", verCliente);
				} 
		    	catch (Exception e) 
		    	{
		    		 e.printStackTrace();
				}
		    } 
		request.getRequestDispatcher("/VerCliente.jsp").forward(request, response);
		}
	}

	
}
