<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script>
$(document).ready(function(){
	$.ajax({
		url: 'rssT',
		success: function(data){
			$('#output').append(data);
			alert(data);
		}
	});
});
</script>
</head>
<body>
<div id="output">
</div>
</body>
</html>