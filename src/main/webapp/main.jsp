<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/lib/jquery/1.11.3/jquery.min.js"></script>
<script src="static/bootstrap/js/bootstrap.min.js"></script>
<script src="static/jquery/jquery-min.js" type="text/javascript"></script>
<script src="static/js/main.js"></script>


<link href="static/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="static/css/main.css" rel="stylesheet" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>:: Bem-vindo Rapaziada! ::</title>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<ul class="nav navbar-nav">
			<li><a href="produto">Produtos</a></li>
			<li><a href="usuario">Usuários</a></li>
		</ul>
		<ul class="nav navbar-nav bemVindoMenu">
			<li>
				<label>Bem vindo ${usuario.nome}</label>
				<label id="imageLogout" onclick="executaLogout();">Sair</label>
			</li>
		</ul>
	</nav>
</body>
</html>