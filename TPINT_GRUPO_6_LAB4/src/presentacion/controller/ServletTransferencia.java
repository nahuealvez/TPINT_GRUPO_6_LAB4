package presentacion.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import dominio.Localidad;
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
		if (request.getParameter("btnMovimientoCuentas") != null) {
			int tipoTransferencia = 1;
			request.setAttribute("tipoTransferencia", tipoTransferencia);
			
			RequestDispatcher rd = request.getRequestDispatcher("/Transferencias.jsp");
			rd.forward(request, response);
		}
		
		if (request.getParameter("btnTransferenciaTerceros") != null) {
			int tipoTransferencia = 2;
			request.setAttribute("tipoTransferencia", tipoTransferencia);
			
			RequestDispatcher rd = request.getRequestDispatcher("/Transferencias.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("btnTransferencia") != null) {
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
			int opcion = Integer.parseInt(request.getParameter("tipoTransferencia"));
			System.out.println(opcion);
			
			switch (opcion) {
				case 1:
					eventoConfirmarMovimientoDeCuentas(request, response);
					break;
				case 2:
					eventoConfirmarTransferenciaTerceros(request, response);
					break;
			}
		}			
	}
	
	public void eventoConfirmarMovimientoDeCuentas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mensaje = "";
		String claseMensaje = null;
        
		Movimiento movimientoSaliente = new Movimiento();
		Movimiento movimientoDestino = new Movimiento();
		TipoMovimiento tipoMovimiento = new TipoMovimiento();
		Cuenta cuentaSaliente = new Cuenta();
		Cuenta cuentaDestino = new Cuenta();
		CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
		
		tipoMovimiento.setId(4); // 4 | Tipo transferencia
		movimientoSaliente.setTipoMovimiento(tipoMovimiento);
		movimientoDestino.setTipoMovimiento(tipoMovimiento);
		
		cuentaSaliente.setId(Integer.parseInt(request.getParameter("ddlCuentaSaliente")));
		movimientoSaliente.setCuenta(cuentaSaliente);
		
		cuentaDestino.setId(Integer.parseInt(request.getParameter("ddlCuentaDestino")));
		movimientoDestino.setCuenta(cuentaDestino);
		
		BigDecimal importe = new BigDecimal(request.getParameter("txtImporteMovimientoCuenta"));
		movimientoSaliente.setImporte(importe);
		movimientoDestino.setImporte(importe);
		
		String concepto = "Mov. Cuentas: " + cuentaSaliente.getId() + " -> " + cuentaDestino.getId();
		movimientoSaliente.setConcepto(concepto);
		movimientoDestino.setConcepto(concepto);
		
		try {
			if (cuentaNegocio.debitar(cuentaSaliente.getId(), movimientoSaliente)) {
				if (cuentaNegocio.acreditar(cuentaDestino.getId(), movimientoDestino))
				mensaje = "Movimiento de cuentas realizado correctamente";
				claseMensaje = "alert alert-success";
			}
			else {
				mensaje = "Error al realizar el movimiento de cuentas | Consulte con el administrador del sistema";
				claseMensaje = "alert alert-danger";
			}
		}
		catch (SinSaldoException ex) {
			mensaje = "No se pudo realizar el movimiento de cuentas | " + ex.getMessage();
			claseMensaje = "alert alert-danger";
		}
		catch (SQLException ex) {
			mensaje = "No se pudo realizar el movimiento de cuentas | " + ex.getMessage();
			claseMensaje = "alert alert-danger";
		}
		catch (Exception ex) {
			mensaje = "No se pudo realizar el movimiento de cuentas | " + ex.getMessage();
			claseMensaje = "alert alert-danger";
		}
		
		int tipoTransferencia = 0;
		
		request.setAttribute("txtMensajeAgregarTransferencia", mensaje);
        request.setAttribute("claseMensajeAgregarTransferencia", claseMensaje);
        request.setAttribute("tipoTransferencia", tipoTransferencia);
		
		RequestDispatcher rd = request.getRequestDispatcher("/Transferencias.jsp");
		rd.forward(request, response);
	}
	
	public void eventoConfirmarTransferenciaTerceros(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mensaje = "";
		String claseMensaje = null;
        
		Movimiento movimientoDebito = new Movimiento();
		Movimiento movimientoCredito = new Movimiento();
		TipoMovimiento tipoMovimiento = new TipoMovimiento();
		Cuenta cuenta = new Cuenta();
		Cuenta cuentaCredito = new Cuenta();
		CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
		
		tipoMovimiento.setId(4); // 4 | Tipo transferencia
		movimientoDebito.setTipoMovimiento(tipoMovimiento);
		cuenta.setId(Integer.parseInt(request.getParameter("txtCuentaSaliente")));
		movimientoDebito.setCuenta(cuenta);
		System.out.println(cuenta.getId());
		BigDecimal importe = new BigDecimal(request.getParameter("txtImporteATransferir"));
		movimientoDebito.setImporte(importe);
		System.out.println(importe);
		String cbu = request.getParameter("txtCbuDestino");
		String concepto = "Dest. | Cta 3ros: " + cbu;
		System.out.println(concepto);
		movimientoDebito.setConcepto(concepto);
		
		movimientoCredito.setTipoMovimiento(tipoMovimiento);
	    movimientoCredito.setImporte(importe);
	    movimientoCredito.setConcepto(concepto);
		
		try {
			if (cuentaNegocio.obtenerNroCuentaActivaPorCBU(cbu) > 0) {
				int idCuentaAcreditar = cuentaNegocio.obtenerNroCuentaActivaPorCBU(cbu);
				cuentaCredito.setId(idCuentaAcreditar);
				movimientoCredito.setCuenta(cuentaCredito);
				
				if (cuentaNegocio.debitar(cuenta.getId(), movimientoDebito) && cuentaNegocio.acreditar(idCuentaAcreditar, movimientoCredito)) {
					mensaje = "Transferencia debitada correctamente";
					claseMensaje = "alert alert-success";
				}
				else {
					mensaje = "Error en la transferencia | Notifique al administrador del sistema";
					claseMensaje = "alert alert-danger";
				}
			}
			else {
				mensaje = "No se pudo realizar la transferencia | No existe cuenta activa con ese CBU";
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
}

