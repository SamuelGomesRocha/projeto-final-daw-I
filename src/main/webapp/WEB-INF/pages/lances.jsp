<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="Stylesheet" href="css/reset.css">
<link rel="Stylesheet" href="css/style-cadLeilas.css">


<title>Dê seus lances!</title>
</head>
<body>
	
	
		<div class="title">
		<h1 onclick="window.location.href='port?action=/listar'">Sistema de Leilões</h1>
		<h3 id="sair" onclick="window.location.href='port?action=/login'">| sair</h3>
		<h3>${usuarioLogado}   |</h3>
		</div>	
	
	
	<div id="corpao-da-bicha">
	

	
		<div id="container-leilas">
		<section class="sec-leilas">
		<form action="port?action=/cadastrarLance&idLeilao=${idLeilao}" method="post">	
		<div class="input-container" >
		<h4>Dê seus lances!</h4>
		<input type="text" name="input-valorLance" id="item" placeholder="R$" width="200px">
		<label class="label" for="item">Valor do lance</label>
		</div>
		${mensagem}
		</form>
		
		</section>

		</div>

	<div id="tableLances">
	
	<h2>${leilaoA.item}  -  R$${leilaoA.lanceMinimo}</h2>
	<table>
		<thead>
			<tr>
				<th>Proponente</th>
				<th>Valor do lance</th>			
			</tr>
		</thead>
		
		<tbody>
	
			<c:forEach items="${lances}" var="l" >
				
				<tr>
					<td>${l.proponente.userName}</td>
					<td>${l.valorLance}</td>
					<td></td>
				</tr>
				
			</c:forEach>
		
		</tbody>
	
		</table>
	</div>
	
	</div>
	
	

	
</body>
</html>