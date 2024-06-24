	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	<script>
		// Example starter JavaScript for disabling form submissions if there are invalid fields
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
		})()
	</script>
	</main>

	<footer class="footer mt-auto ps-4 pe-4 bg-dark d-flex align-items-center gap-2">
            <span class="text-white fw-bold display-14">Piggy Bank</span>
            <span class="text-white fw-bold display-14">|</span>
            <span class="text-white">Todos los derechos reservados - 2024</span>
	</footer>
</body>
</html>