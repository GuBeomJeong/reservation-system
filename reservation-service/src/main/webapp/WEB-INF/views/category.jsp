<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="/category">
    name : <input type="text" name="name">
    <input type="submit" value="확인">
</form>


	<ul id="category_list">
	
	<c:forEach var="listElement" items="${categories}" varStatus="listCount">       
	         <li id=${listElement.id}><h3>${listElement.name}</h3> <button class="del">삭제하기</button><input type="text"><button class="update">수정하기</button></li>
    </c:forEach>

	
</ul>
<script src="resources/js/libs/jquery.min.js"></script>
<script src="resources/js/category.js"></script>
</body>
</html>