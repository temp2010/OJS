function changeProfile(profile) {
	if(profile == 5) {
		$('#form-thematics').prop('disabled', true);
		$('#form-support').prop('disabled', true);
	} else {
		$('#form-thematics').prop('disabled', false);
		$('#form-support').prop('disabled', false);
	}
}