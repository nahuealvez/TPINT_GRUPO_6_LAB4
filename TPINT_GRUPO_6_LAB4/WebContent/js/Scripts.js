function volverPantallaAnterior() {
	window.history.back();
}

function cancelarOperacion() {
	document.getElementById("btnInicio").click();
};

(() => {
	  'use strict';

	  // Fetch all the forms we want to apply custom Bootstrap validation styles to
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

document.addEventListener('DOMContentLoaded', () => {
	setTimeout(function () {
	    $("#alert").fadeOut(1000);
	}, 3000);
});

// Transferencias | Movimiento de cuentas

function cargarConfirmacionMovimientoCuenta() {
	let txtImporteMovimientoCuenta = document.getElementById("txtImporteMovimientoCuenta").value;
	let txtCuentaDestino = document.getElementById("txtCuentaDestino").options[document.getElementById("txtCuentaDestino").selectedIndex].text;
	let txtCuentaSaliente = document.getElementById("txtCuentaSaliente").options[document.getElementById("txtCuentaSaliente").selectedIndex].text;
	
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

function actualizarCuentaDestino() {
	
    const selectSaliente = document.getElementById('txtCuentaSaliente');
    const selectDestino = document.getElementById('txtCuentaDestino');
	
    for (let i = 0; i < selectSaliente.options.length; i++) {
        const option = selectSaliente.options[i].cloneNode(true);
        selectDestino.appendChild(option);
    }
	
	document.getElementById('txtCuentaSaliente').addEventListener('change', function() {
	    const selectedIndex = selectSaliente.selectedIndex;

	    if (selectedIndex >= 0) {
	        const selectedValue = selectSaliente.options[selectedIndex].value;

	        for (let i = 0; i < selectDestino.options.length; i++) {
	            if (selectDestino.options[i].value === selectedValue) {
	                selectDestino.remove(i);
	                break;
	            }
	        }
	    }
	});
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
    document.getElementById("prestamoAConfirmar").style.display = "block";
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