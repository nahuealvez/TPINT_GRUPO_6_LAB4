package presentacion.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Excepciones.SinSaldoException;
import dominio.Cliente;
import dominio.Cuenta;
import dominio.Movimiento;
import dominio.TipoMovimiento;
import negocio.CuentaNegocio;
import negocio.MovimientoNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegImpl;

/**
 * Servlet implementation class ServletTransferencia
 */
@WebServlet("/ServletTransferencia")
public class ServletTransferencia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTransferencia() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getAttribute("btnTransferencia") != null) {
			Cliente cliente = new Cliente();
			ArrayList<Cuenta> cuentasPorCliente = new ArrayList<Cuenta>();
			CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
			
			HttpSession sessionLogueada = request.getSession(false);
			
			if (sessionLogueada != null) {
				try {
					cliente = (Cliente)sessionLogueada.getAttribute("cliente");
					int id = cliente.getIdCliente();
					cuentasPorCliente = (ArrayList<Cuenta>) cuentaNegocio.cuentasPorClienteActivas(id);
				}
				catch (SQLException ex) {
					ex.printStackTrace();
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
				
				sessionLogueada.setAttribute("cuentasPorCliente", cuentasPorCliente);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/Transferencias.jsp");
			rd.forward(request, response);
		}
		
		if (request.getParameter("confirmarBtn") != null) {
			String mensaje = "";
			String claseMensaje = null;
	        
			Movimiento movimiento = new Movimiento();
			TipoMovimiento tipoMovimiento = new TipoMovimiento();
			Cuenta cuenta = new Cuenta();
			CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
			
			tipoMovimiento.setId(4); // 4 | Tipo transferencia
			movimiento.setTipoMovimiento(tipoMovimiento);
			cuenta.setId(Integer.parseInt(request.getParameter("txtCuentaSaliente")));
			movimiento.setCuenta(cuenta);
			System.out.println(cuenta.getId());
			BigDecimal importe = new BigDecimal(request.getParameter("txtImporteATransferir"));
			movimiento.setImporte(importe);
			System.out.println(importe);
			String cbu = request.getParameter("txtCbuDestino");
			String concepto = "Dest. | Cta 3ros: " + cbu;
			System.out.println(concepto);
			movimiento.setConcepto(concepto);
			
			try {
				if (cuentaNegocio.debitar(cuenta.getId(), movimiento)) {
					mensaje = "Transferencia debitada correctamente";
					claseMensaje = "alert alert-success";
				}
				else {
					mensaje = "Error al realizar la transferencia | Consulte con el administrador del sistema";
					claseMensaje = "alert alert-danger";
				}
			}
			catch (SinSaldoException ex) {
				mensaje = "No se pudo realizar la transferencia | " + ex.getMessage();
				claseMensaje = "alert alert-danger";
			}
			catch (SQLException ex) {
				mensaje = "Error al realizar la transferencia | " + ex.getMessage();
				claseMensaje = "alert alert-danger";
			}
			catch (Exception ex) {
				mensaje = "Error al realizar la transferencia | " + ex.getMessage();
				claseMensaje = "alert alert-danger";
			}
			
			int tipoTransferencia = 0;
			
			request.setAttribute("txtMensajeAgregarTransferencia", mensaje);
	        request.setAttribute("claseMensajeAgregarTransferencia", claseMensaje);
	        request.setAttribute("tipoTransferencia", tipoTransferencia);
			
			RequestDispatcher rd = request.getRequestDispatcher("/Transferencias.jsp");
			rd.forward(request, response);
		}
		
		if (request.getParameter("btnMovimientoCuentas") != null) {
			
			int tipoTransferencia = 1;
			request.setAttribute("tipoTransferencia", tipoTransferencia);
			
			RequestDispatcher rd = request.getRequestDispatcher("/Transferencias.jsp");
			rd.forward(request, response);
		}
		
		if (request.getParameter("btnTransferenciaTerceros") != null) {
			int cargarCampos = 1;
			int tipoTransferencia = 2;
			request.setAttribute("btnTransferencia", cargarCampos);
			request.setAttribute("tipoTransferencia", tipoTransferencia);
			
			RequestDispatcher rd = request.getRequestDispatcher("/ServletTransferencia");
			rd.forward(request, response);
		}
	}

}
