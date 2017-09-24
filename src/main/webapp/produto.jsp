<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="produtoDao" class="br.com.lojinha.lojinhaDAO.ProdutoDAO" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="static/bootstrap/css/bootstrap.min.css" rel="stylesheet" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>
<script src="static/jquery/jquery-min.js" type="text/javascript"></script>
<script src="static/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="static/js/produto.js" type="text/javascript"></script>

<title>:: Produtos ::</title>
</head>
<body id="bodyContent">
	<nav class="navbar navbar-inverse">
		<ul class="nav navbar-nav">
			<li><a href="main.jsp">index</a></li>
			<li><a href="produto">Produtos</a></li>
			<li><a href="usuario">Usuários</a></li>
		</ul>
	</nav>

	<div
		style="width: 80%; margin: 0 auto; height: 100%; background-color: #ececec;">
		<div id="contentListaProdutos">
			<h3>Lista de produtos</h3>
			<table class="table table-striped" style="border: 1px solid #e0e0e0;">
				<thead>
					<tr>
						<th>Id</th>
						<th>Nome</th>
						<th>Descricao</th>
						<th>Preco</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="produto" items="${produtoDao.listarProdutos()}">
						<tr>
							<td>${produto.id}</td>
							<td>${produto.nome}</td>
							<td>${produto.descricaoProduto}</td>
							<td>${produto.preco}</td>
							<td><button type="button" class="btn btn-danger"
									onclick="excluirProduto(${produto.id})">Excluir</button></td>
							<td><button type="button" class="btn btn-success"
									onclick="buscaProdutoSelecionado(${produto.id})">Editar</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="form-group"
			style="width: 80%; margin: 0 auto; text-align: center;">
			<button id="btnRefresh" class="btn btn-warning"  onclick="refresh();"> Atualizar Tabela</button>
		</div>

		<div id="contentAdicionaProduto"
			style="margin-top: 10px !important; margin: 0 auto;">
			<form class="form-horizontal">
				<input type="hidden" id="id" class="form-control" style="display: none;">
			
				<div class="form-group">
					<label class="col-sm-2 control-label">Nome do produto: </label>
					<div class="col-sm-10">
						<input type="text" id="nomeProduto" name="nomeProduto" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Descrição:</label>
					<div class="col-sm-10">
						<input type="text"  id="descricao" name="descricao" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Preço:</label>
					<div class="col-sm-10">
						<input type="text"  id="preco" name="preco" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button id="btnAlterar" class="btn btn-default" onclick="alteraProdutoSelecionado();" style="display: none;">Alterar</button>
						<button id="btnVoltar" class="btn btn-default" onclick="voltarVisualizacaoPadrao();" style="display: none;">Voltar</button>
						<button id="btnAdicionar" class="btn btn-default" onclick="adicionaNovoProduto();">Gravar</button>
					</div>
				</div>
			</form>
		</div>
		
	</div>
</body>
</html>