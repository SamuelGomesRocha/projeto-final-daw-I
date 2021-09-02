<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="Stylesheet" href="css/reset.css">
<link rel="Stylesheet" href="css/style-login.css">
<title>Login</title>
</head>
<body>

	<div id="corpao-da-bicha">
	<section>
	<form action="port?action=/login" method="post">
		
		<div class="input-container">
		<h2 class="title">Sistema de leilões</h2>
		<h2>Login de usuário</h2>
		<input type="text" name="input-user" placeholder="login" width="200px">
		<input type="password" name="input-senha" placeholder="senha" width="200px">
	
		</br></br></br><input class="btn" id="input-button" type="submit" value="entrar">
		</div>	
		<h4>Não possui conta? </h4>
		<h3 onclick="window.location.href='port?action=/newUser'"> Cadastre-se</h3>
	</form>
	
	</section>
	</div>	
</body>
</html>