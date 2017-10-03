function mostraLogin() {
	$("#containerLogin").css({
		"display" : "block"
	});
}

function autenticaUsuario() {
	var login = $("#login").val();
	var senha = $("#password").val();

	$.ajax({
		url : 'autentica?action=autenticaUsuario',
		type : 'POST',
		cache : false,
		async:false,  
		data : {
			login : login,
			password : senha
		},
		error : function() {
			alert('Erro ao tentar excluir Usuario!');
		},
		success : function() {
			alert('teste');
		}
	});
}
