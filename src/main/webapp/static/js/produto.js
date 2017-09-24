function adicionaNovoProduto() {
	var nomeProduto = $("#nomeProduto").val();
	var descricao = $("#descricao").val();
	var preco = $("#preco").val();

	$.ajax({
		url : 'produto?action=addProduto',
		type : 'POST',
		cache : false,
		data : {
			nomeProduto : nomeProduto,
			descricao : descricao,
			preco : preco
		},
		success : function() {
			refresh();
		}
	});
}

function excluirProduto(idProduto) {
	$.ajax({
		url : 'produto?action=removeProduto',
		type : 'POST',
		cache : false,
		data : { produtoId : idProduto },
		error : function() {
			alert('Erro ao tentar excluir produto!');
		},
		success : function() {
			refresh();
		}
	});
}

function voltarVisualizacaoPadrao(){
	$("#nomeProduto").val("");
	$("#descricao").val("");
	$("#preco").val("");
	$("#btnAlterar").css({"display" : "none"});
	$("#btnVoltar").css({"display" : "none"});
	
	$("#btnAdicionar").css({"display" : "block"});
}

function buscaProdutoSelecionado(idProduto) {
	$.ajax({
		url : 'produto?action=buscaProduto',
		type : 'POST',
		cache : false,
		data : { produtoId : idProduto },
		success : function(produto) {
			$("#id").val(produto.id);
			$("#nomeProduto").val(produto.nome);
			$("#descricao").val(produto.descricaoProduto);
			$("#preco").val(produto.preco);
			$("#btnAlterar").css({"display" : "inline-block"});
			$("#btnVoltar").css({"display" : "inline-block"});
			
			$("#btnAdicionar").css({"display" : "none"});
		}
	});
}

function alteraProdutoSelecionado() {
	var id = $("#id").val();
	var nomeProduto = $("#nomeProduto").val();
	var descricao = $("#descricao").val();
	var preco = $("#preco").val();
	
	$.ajax({
		url : 'produto?action=alteraProduto',
		type : 'POST',
		cache : false,
		data : {
			id : id,
			nomeProduto : nomeProduto,
			descricao : descricao,
			preco : preco
		},
		success : function() {			
			voltarVisualizacaoPadrao();
			refresh();
		}
	});
}

function refresh() {
	$("#bodyContent").load(location.href + "#bodyContent");
}