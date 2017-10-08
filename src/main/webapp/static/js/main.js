function executaLogout(){
	alert("cai aqui");
	$.ajax({
		url : 'autentica?action=logout',
		type : 'GET',
		cache : false
	});
}