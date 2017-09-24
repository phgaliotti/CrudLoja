function adicionaNovoUsuario() {
	var nomeUsuario = $("#nomeUsuario").val();
	var email = $("#email").val();
	var login = $("#login").val();
	var senha = $("#senha").val();
	var tipoPermissao = $("#tipoPermissao").val();

	$.ajax({
		url : 'usuario?action=addUsuario',
		type : 'POST',
		cache : false,
		data : {
			nomeUsuario : nomeUsuario,
			email : email,
			login : login,
			senha : senha,
			tipoPermissao : tipoPermissao
		},
		success : function() {
			refresh();
		}
	});
}

function voltarVisualizacaoPadrao(){
	$("#nomeUsuario").val("");
	$("#email").val("");
	$("#login").val("");
	$("#senha").val("");
	$("#tipoPrrmissao").val("");
	$("#btnAlterar").css({"display" : "none"});
	$("#btnVoltar").css({"display" : "none"});
	
	$("#btnAdicionar").css({"display" : "block"});
}

function buscaUsuarioSelecionado(id) {
	$.ajax({
		url : 'usuario?action=buscaUsuario',
		type : 'POST',
		cache : false,
		data : { usuarioId : id },
		success : function(usuario) {
			$("#id").val(usuario.id);
			$("#nomeUsuario").val(usuario.nome);
			$("#email").val(usuario.email);
			$("#login").val(usuario.login);
			$("#senha").val(ussuario.senha);
			$("#tipoPrrmissao").val(usuario.codTipoUsuario);
			$("#btnAlterar").css({"display" : "inline-block"});
			$("#btnVoltar").css({"display" : "inline-block"});
			
			$("#btnAdicionar").css({"display" : "none"});
		}
	});
}

function excluirUsuario(idUsuario) {
	$.ajax({
		url : 'usuario?action=removeUsuario',
		type : 'POST',
		cache : false,
		data : { usuarioId : idUsuario },
		error : function() {
			alert('Erro ao tentar excluir Usuario!');
		},
		success : function() {
			refresh();
		}
	});
}

function alteraUsuario(id) {
	$.ajax({
		url : 'usuario?action=alteraUsuario',
		type : 'POST',
		cache : false,
		data : { usuarioId : id },
		success : function() {
			voltarVisualizacaoPadrao();
			refresh();
		}
	});
}

function refresh() {
	$("#bodyContent").load(location.href + "#bodyContent");
}