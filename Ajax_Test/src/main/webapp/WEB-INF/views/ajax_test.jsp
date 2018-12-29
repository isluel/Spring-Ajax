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
	<form id="ajaxForm">
		<table>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id" id="id"/></td>
			</tr>
			<tr>
				<td>비번</td>
				<td><input type="text" name="pw"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="전송"/></td>
			</tr>
		</table>
	</form>
	<div id="output">
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			$('#ajaxForm').submit(function(event){
				var id = $('#id').val();
				$.ajax({
					url: '/ajax_Test/ajax_submit',
					type: 'post',
					datatype: 'json',
					data: $(this).serialize(),
					success: function(data){
						//alert(data.data);
						$('<h1></h1>').text(data.data).appendTo('#output');
						
					},
					error: function(request, status, error){
						alert("실패");
					}
				})
				event.preventDefault();
			})
			
			
		});
	</script>
</body>
</html>