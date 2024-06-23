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
            display: flex;
            height: 100vh;
            background-color: #f4f4f4;
        }
        .login-container {
            display: flex;
            width: 100%;
            height: auto;
        }
        .image-container {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .form-container {
            flex: 1;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            padding: 40px;
            background-color: white;
            box-shadow: -10px 0 10px rgba(0, 0, 0, 0.1);
        }
        .logo {
            font-size: 24px;
            margin-bottom: 20px;
        }
        .input-group {
            margin-bottom: 15px;
            width: 100%;
        }
        .input-group label {
            display: block;
            margin-bottom: 5px;
        }
        .input-group input {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
        }
        .btn {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <div class="image-container">
            <img src="images/hb.png" alt="Login">
        </div>
        <div class="form-container">
            <h1 class="logo">Piggy Bank</h1>
            <form method="post" action="ServletLogin">
                <div class="input-group">
                    <label for="usuario">Usuario</label>
                    <input type="text"  name="txtUsuario" placeholder="Ingrese su usuario" required>
                </div>
                <div class="input-group">
                    <label for="passoword">Contrase�a</label>
                    <input type="password"  name="txtPass" placeholder="Ingrese su contrase�a" required>
                    <% 
        				String loginError = (String) request.getAttribute("loginError");
        					if (loginError != null) { 
    				%>
        					<p style="color: red;"><%= loginError %></p>
   					 	<% 
       						 } 
   						 %>
                </div>
                <button type="submit" name="btnIniciarSesion"  class="btn">Iniciar Sesi�n</button>
            </form>
        </div>
    </div>
</body>
</html>