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
	
	<form action="port?action=/cad-user" method="post">
		
		<div class="input-container">
		<h2 class="title">Sistema de leilões</h2>
		<h2>Cadastro de usuário</h2>
		<input type="text" name="input-cpf" placeholder="cpf" width="200px">
		<input type="text" name="input-nome" placeholder="nome" width="200px">
		<input type="text" name="input-email" placeholder="email" width="200px">
		<input type="text" name="input-telefone" placeholder="telefone" width="200px">
		<input type="text" name="input-user_name" placeholder="user name" width="200px">
		<input type="text" name="input-password" placeholder="senha" width="200px">
	
		</br></br></br><input class="btn" id="input-button" type="submit" value="salvar">
		</div>	
	</form>
	
	</section>
	</div>	
</body>
</html>


