function changeProfile(profile) {
	if(profile == 6) {
		$('#form-thematics').prop('disabled', true);
		$('#form-support').prop('disabled', true);
	} else {
		$('#form-thematics').prop('disabled', false);
		$('#form-support').prop('disabled', false);
	}
}

function aprobar(accion) {
	if(window.confirm('¿Desea '+accion+' este registro?')) {
		$('#form-accion').val(accion);
		$('#form-form').submit();
	}
}