<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
#b_title{
	width : 230px;
}
#b_content{
	width : 230px;
	height : 500px;
}
</style>
</head>
<body>
게시판 작성화면
<form action="<%=request.getContextPath()%>/boardInsert.do" method="post" enctype="text/html">
<table>
	<tr>
		<td>제목:</td>
		<td><input type="text" id="b_title" name="b_title"></td>
	</tr>
	<tr>
		<td>내용:</td>
		<td><textarea id="b_content" name="b_content"></textarea>
	</tr>
</table>
<input type="submit" value="작성">
</form>
</body>
</html>