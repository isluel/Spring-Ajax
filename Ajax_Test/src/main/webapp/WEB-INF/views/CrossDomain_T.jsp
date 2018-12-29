<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
</head>
<body>
<script>
	$.ajax({
		url: 'jsonp_Test',
		dataType: 'jsonp',
		success: function(data){
			alert(data.data1);
		}
	})
</script>
</body>
</html>