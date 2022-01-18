$(document).ready(function() {

	// alert($('body').attr('file')) ;
	
	$('div.toc dl dd').addClass('toc-section-content') ;
	$('div.toc dl dd dl dd').removeClass('toc-section-content') ;
	$('div.toc dl dd dl dd').addClass('toc-subsection-content') ;
	
	$('.toc-section-content').prev().addClass('toc-section-head') ;
	$('.toc-subsection-content').prev().addClass('toc-subsection-head') ;
	
	$('<b class="expand-section">+</b>').prependTo('.toc-section-head') ;
	$('<b class="expand-section">+</b>').prependTo('.toc-subsection-head') ;
	
	$('.toc-section-content').hide() ;
	
	$('.toc-subsection-content').hide() ;
	
	$('.expand-section').toggle(
		function() {
			// $(this).html('<b class="expand-section">&#8211;</b>') ;
			$(this).parent().next().slideDown() ;
		}, 
		function() {
			// $(this).html('<b class="expand-section">+</b>') ;
			$(this).parent().next().slideUp() ;
		}
	) ;
	
	var fileName = $('body').attr('file') ;
	var parent = $("'a[href=\'"+fileName+"\']'").parent() ;
	while (parent.attr('class') != 'toc-section-head' && 
		   parent.attr('class') != 'toc-subsection-head' && 
		   parent.attr('class') != 'toc-section-content') {
		
		parent = parent.parent() ;
	}
	
	if (parent.attr('class') == 'toc-section-content') {
		
		parent.show() ;
		
	} else if (parent.attr('class') == 'toc-subsection-head') {
		
		var subsection = parent.next() ;
		while (parent.attr('class') != 'toc-section-content') {
			parent = parent.parent() ;
		}
		parent.show() ;
		subsection.show() ;
		
	} else {
		
		parent.next().show() ;
		
	}
}) ;