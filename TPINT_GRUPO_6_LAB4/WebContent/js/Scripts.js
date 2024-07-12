function volverPantallaAnterior() {
	window.history.back();
}

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

function cargarConfirmacion() {
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

function mostrarConfirmacion() {	    	
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

function cancelarOperacion() {
	document.getElementById("btnInicio").click();
};

function validarYMostrarConfirmacion(event) {
    var form = document.getElementById("formularioSolicitud");
    if (form.checkValidity() === false) {
        event.preventDefault();
        event.stopPropagation();
    } else {
    	cargarConfirmacion();
        mostrarConfirmacion();
    }
    form.classList.add('was-validated');
};

function validarDecimal(event) {
    const caracter = String.fromCharCode(event.which);
    if (!(/[0-9.]|\./.test(caracter))) {
        event.preventDefault();
    }
};