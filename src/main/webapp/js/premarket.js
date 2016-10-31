$(document).ready(function() {
	$('.premarket-row').on('click', function() {
		var marketName = $(this).find('.name').text();
		var marketId = $(this).find('.id').text();
		
		$('#options .name').text('Nome: ' + marketName);
		$('#options .id').val(marketId);
		$('#options').modal({
			fadeDuration: 100,
			closeClass: 'close'
		});
	});
});