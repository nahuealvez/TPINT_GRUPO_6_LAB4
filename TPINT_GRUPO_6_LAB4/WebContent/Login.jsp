<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Piggy Bank - Login</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background: url('images/hb.png') no-repeat center center fixed;
            background-size: cover;
        }
        .login-container {
            width: 90%;
            max-width: 800px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
            border-radius: 10px;
            margin: 20px;
            display: flex;
            flex-direction: row; /* Cambiado a fila para poner el logo y el formulario uno al lado del otro */
            overflow: hidden;
        }
        .logo-container {
            flex: 1; /* Ocupa 50% del ancho disponible */
            background: url('images/logopiggybank.png') no-repeat top center;
            background-size: contain;
            display: flex;
            justify-content: center;
            align-items: center;
            border-top-left-radius: 10px;
            border-bottom-left-radius: 10px;
        }
        .form-container {
            flex: 1; /* Ocupa 50% del ancho disponible */
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            
            padding: 20px;
            border-top-right-radius: 10px;
            border-bottom-right-radius: 10px;
        }
        .logo {
            font-size: 28px;
            margin-bottom: 20px;
            text-align: center;
            color: #333333;
        }
        .input-group {
            margin-bottom: 20px;
            width: 100%;
        }
        .input-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #333333;
        }
        .input-group input {
            width: 100%;
            padding: 12px;
            box-sizing: border-box;
            border: 1px solid #cccccc;
            border-radius: 5px;
            background-color: #d3d3d3;
        }
        .btn {
            width: 100%;
            padding: 12px;
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }
        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <div class="logo-container">
           
        </div>
        <div class="form-container">
            <form method="post" action="ServletLogin">
                <div class="input-group">
                    <label for="usuario">Usuario</label>
                    <input type="text" name="txtUsuario" placeholder="Ingrese su usuario" required>
                </div>
                <div class="input-group">
                    <label for="password">Contraseña</label>
                    <input type="password" name="txtPass" placeholder="Ingrese su contraseña" required>
                     <% 
        				String loginError = (String) request.getAttribute("loginError");
        					if (loginError != null) { 
    				%>
        					<p style="color: red;"><%= loginError %></p>
   					 	<% 
       						 } 
   						 %>
                </div>
                <button type="submit" name="btnIniciarSesion" class="btn">Iniciar Sesión</button>
            </form>
        </div>
    </div>
</body>
</html>
