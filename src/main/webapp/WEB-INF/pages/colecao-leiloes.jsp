<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="Stylesheet" href="css/reset.css">
<link rel="Stylesheet" href="css/style-index.css">

<title>Leilões</title>
</head>
<body>
	<div class="title">
	<h1>Lista de Leilões</h1>
	<h2 id="sair" onclick="window.location.href='port?action=/login'">| sair</h2>
	<h2>${usuarioLogado} |</h2>
	</div>
	<div id="colecao-content">
	
	<div>
	<p><button class="btn" type="button" onclick="window.location.href='port?action=/novo'">Novo leilão</button></p>
	</div>

		</br>
		<form action="port?action=/listar" method="post">
		<input type="text" name="input-statusLeilas" id="item" placeholder="Filtro de status" width="200px">
		<input type="submit" value="pesquisar">
		</form>
		</br>
	<table>
		
		<thead>
			<tr>
				<th>Item  </th>
				<th> Lance mínimo  </th>
				<th> Status  </th>
				<th> Data de expiração  </th>			
			</tr>
		</thead>
		
		<tbody>
	
			<c:forEach items="${leiloes}" var="l" >
							
				
				<tr id="trLeilas" 
				ondblclick="window.location.href='port?action=/newLances&idLeilao=${l.idLeilao}'">
				
					<td>${l.item}</td>
					<td>${l.lanceMinimo}</td>
					<td>${l.status}</td>
					<td>${l.dataExpiracao}</td>
					<td><a href="port?action=/deletar&idLeilao=${l.idLeilao}">excluir</a></td>
				</tr>
				
			</c:forEach>
		
		</tbody>
	
	</table>
	</div>
</body>
</html>