<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
/*
		window.test = function(input){
			alert(input.data);
		}
		
		var script = document.createElement('script');
		script.src='https://api.github.com/users/jquery/repos?callback=test';
		document.head.appendChild(script);
		*/
		$(document).ready(function(){
			$.ajax('https://api.github.com/users/jquery/repos',{
				dataType: 'jsonp',
				success: function(input){
					$.each(input.data, function(index, item){
						var a = $('<h1></h1>').html(item.name);
						var b = $('<a></a>').attr('href', item.html_url).html(item.html_url);
						var c = $('<hr/>');
						
						$('<div></div>').append(a,b,c).appendTo('body');
					});
					alert(input.data);
				}
			});
		});
		
	</script>
</head>
<body>
	
</body>
</html>