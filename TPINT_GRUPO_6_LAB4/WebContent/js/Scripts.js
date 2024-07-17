document.addEventListener('DOMContentLoaded', (event) => {
	setTimeout(function () {
	    $("#alert").fadeOut(1000);
	}, 3000);
	
	
	// evitar acción de botones al presionar enter en los campos del formulario
	const inputs = document.querySelectorAll('input');

	inputs.forEach(input => {
        input.addEventListener('keypress', function(e) {
            if (e.key === 'Enter' && !e.target.classList.contains('noPreventDefault')) {
                e.preventDefault();
            }
        });
    });
});

function volverPantallaAnterior() {
	window.history.back();
}

function irAlIndex() {
	document.getElementById("btnInicio").click();
}

function cancelarOperacion() {
	document.getElementById("btnInicio").click();
};

(() => {
	  'use strict';

	  // Fetch all the forms we want to apply custom Bootstrap validation
		// styles to
	  const forms = document.querySelectorAll('.needs-validation');

	  // Loop over them and prevent submission
	  Array.from(forms).forEach(form => {
	    form.addEventListener('submit', event => {
	      if (!form.checkValidity()) {
	        event.preventDefault()
	        event.stopPropagation()
	      }

	      form.classList.add('was-validated')
	    }, false)
	  })
})();

// Pago de cuotas

function cargarConfirmacionPagoCuota() {
	let txtCuentaSaliente = document.getElementById("ddlCuentaSaliente").options[document.getElementById("ddlCuentaSaliente").selectedIndex].text;
	
	document.getElementById("txtCuentaSalienteAConfirmar").innerText = txtCuentaSaliente;
};

function mostrarConfirmacionPagoCuota() {
	document.getElementById("confirmacionPagoDeCuota").style.display = "block";
	document.getElementById("confirmarBtn").style.display = "inline-block";
    document.getElementById("cancelarBtn").style.display = "inline-block";
    
    document.getElementById("resumenCuota").style.display = "none";
    document.getElementById("pagoDeCuota").style.display = "none";
    document.getElementById("pagarBtn").style.display = "none";
    document.getElementById("volverBtn").style.display = "none";
    
    let camposFormulario = document.getElementsByClassName("camposFormulario");
    for (var i = 0; i < camposFormulario.length; i++) {
        camposFormulario[i].style.display = "none";
    }
};

function validarYMostrarConfirmacionPagoCuota(event) {
    var form = document.getElementById("pagoDeCuota");
    if (form.checkValidity() === false) {
        event.preventDefault();
        event.stopPropagation();
    } else {
    	cargarConfirmacionPagoCuota();
    	mostrarConfirmacionPagoCuota();
    }
    form.classList.add('was-validated');
};

// Prestamos

function cargarConfirmacionPrestamo() {
	// OBTIENE LOS VALORES DEL FORMULARIO DE CARGA
    var importeSolicitado = document.getElementById("txtImporteSolicitado").value;
    var cuotas = document.getElementById("txtCuotas").value;
    
    // CALCULA EL IMPORTE FINAL INTERES INCLUIDO
    var interes = 0.20;
    var montoAPagar = importeSolicitado * (1 + interes);
    var importeCuota = montoAPagar / cuotas;
 
    // ACTUALIZA CONTENIDO A MOSTRAR DEL DIV DE CONFIRMACION
    document.getElementById("confImporteSolicitado").innerText = "$" + parseFloat(importeSolicitado).toFixed(2);
    document.getElementById("confCuotas").innerText = cuotas;
    document.getElementById("confMontoPagar").innerText = "$" + montoAPagar.toFixed(2);
    document.getElementById("confImporteCuota").innerText = "$" + importeCuota.toFixed(2);
};

function mostrarConfirmacionPrestamo() {
    document.getElementById("prestamoAConfirmar").style.display = "block";
    document.getElementById("confirmarBtn").style.display = "inline-block";
    document.getElementById("cancelarBtn").style.display = "inline-block";
    
    document.getElementById("formularioSolicitud").style.display = "none";
    document.getElementById("volverBtn").style.display = "none";
    document.getElementById("solicitarBtn").style.display = "none";
    
    let camposFormulario = document.getElementsByClassName("camposFormulario");
    for (var i = 0; i < camposFormulario.length; i++) {
        camposFormulario[i].style.display = "none";
    }
};

function validarYMostrarConfirmacionPrestamo(event) {
    var form = document.getElementById("formularioSolicitud");
    if (form.checkValidity() === false) {
        event.preventDefault();
        event.stopPropagation();
    } else {
    	cargarConfirmacionPrestamo();
    	mostrarConfirmacionPrestamo();
    }
    form.classList.add('was-validated');
};


// Transferencias | Movimiento de cuentas

function cargarConfirmacionMovimientoCuenta() {
	let txtImporteMovimientoCuenta = document.getElementById("txtImporteMovimientoCuenta").value;
	let txtCuentaDestino = document.getElementById("ddlCuentaDestino").options[document.getElementById("ddlCuentaDestino").selectedIndex].text;
	let txtCuentaSaliente = document.getElementById("ddlCuentaSaliente").options[document.getElementById("ddlCuentaSaliente").selectedIndex].text;
	
	let txtImporteMovimientoCuentaAConfirmar = document.getElementById("txtImporteMovimientoCuentaAConfirmar");
	let txtCuentaDestinoAConfirmar = document.getElementById("txtCuentaDestinoAConfirmar");
	let txtCuentaSalienteAConfirmar = document.getElementById("txtCuentaSalienteAConfirmar");
	
	txtImporteMovimientoCuentaAConfirmar.textContent = txtImporteMovimientoCuenta;
	txtCuentaDestinoAConfirmar.textContent = txtCuentaDestino;
	txtCuentaSalienteAConfirmar.textContent = txtCuentaSaliente;
};

function mostrarConfirmacionMovimientoCuenta() {	    	
    document.getElementById("MovimientoCuentaAConfirmar").style.display = "block";
    document.getElementById("confirmarBtn").style.display = "inline-block";
    document.getElementById("cancelarBtn").style.display = "inline-block";
    
    document.getElementById("formularioSolicitud").style.display = "none";
    document.getElementById("volverBtn").style.display = "none";
    document.getElementById("solicitarBtn").style.display = "none";
    
    let camposFormulario = document.getElementsByClassName("camposFormulario");
    for (var i = 0; i < camposFormulario.length; i++) {
        camposFormulario[i].style.display = "none";
    }
};

function validarYMostrarConfirmacionMovimientoCuenta(event) {
    var form = document.getElementById("formularioSolicitud");
    if (form.checkValidity() === false) {
        event.preventDefault();
        event.stopPropagation();
    } else {
    	cargarConfirmacionMovimientoCuenta();
    	mostrarConfirmacionMovimientoCuenta();
    }
    form.classList.add('was-validated');
};

function cargarCuentaDestino() {
    const ddlCuentaSaliente = document.getElementById('ddlCuentaSaliente');
    let ddlCuentaDestino = document.getElementById('ddlCuentaDestino');
    
    ddlCuentaDestino.innerHTML = '';
    
    const options = ddlCuentaSaliente.options;
    const selectedValue = ddlCuentaSaliente.value;

    for (let i = 0; i < options.length; i++) {
        const option = options[i].cloneNode(true);
        if (option.value != selectedValue) {
        	ddlCuentaDestino.appendChild(option);
        } 
    }
}

// Transferencias | Transferencias a terceros

function cargarConfirmacionTransferencia() {
	let txtImporteATransferir = document.getElementById("txtImporteATransferir").value;
	let txtCbuDestino = document.getElementById("txtCbuDestino").value;
	let txtCuentaSaliente = document.getElementById("txtCuentaSaliente").options[document.getElementById("txtCuentaSaliente").selectedIndex].text;
	
	let txtImporteAConfirmar = document.getElementById("txtImporteAConfirmar");
	let txtCbuAConfirmar = document.getElementById("txtCbuAConfirmar");
	let txtCuentaAConfirmar = document.getElementById("txtCuentaAConfirmar");
	
	txtImporteAConfirmar.textContent = txtImporteATransferir;
	txtCbuAConfirmar.textContent = txtCbuDestino;
	txtCuentaAConfirmar.textContent = txtCuentaSaliente;
};

function mostrarConfirmacionTransferencia() {	    	
    document.getElementById("transferenciaAConfirmar").style.display = "block";
    document.getElementById("confirmarBtn").style.display = "inline-block";
    document.getElementById("cancelarBtn").style.display = "inline-block";
    
    document.getElementById("formularioSolicitud").style.display = "none";
    document.getElementById("volverBtn").style.display = "none";
    document.getElementById("solicitarBtn").style.display = "none";
    
    let camposFormulario = document.getElementsByClassName("camposFormulario");
    for (var i = 0; i < camposFormulario.length; i++) {
        camposFormulario[i].style.display = "none";
    }
};

function validarYMostrarConfirmacionTransferencia(event) {
    var form = document.getElementById("formularioSolicitud");
    if (form.checkValidity() === false) {
        event.preventDefault();
        event.stopPropagation();
    } else {
    	cargarConfirmacionTransferencia();
    	mostrarConfirmacionTransferencia();
    }
    form.classList.add('was-validated');
};

function validarDecimal(event) {
    const char = String.fromCharCode(event.which);
    if (!(/[0-9.]|\./.test(char))) {
        event.preventDefault();
    }
};