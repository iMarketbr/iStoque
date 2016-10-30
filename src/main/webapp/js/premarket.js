$(document).ready(function() {
	$('.premarket-row').on('click', function() {
		var marketName = $(this).find('.name').text();
		
		$('#options .name').text('Nome: ' + marketName);
		$('#options').modal({
			fadeDuration: 100,
			closeClass: 'close',
			closeText:'X'
		});
	});
});