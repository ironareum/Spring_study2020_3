<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<c:set var='root' value='${pageContext.request.contextPath }/'/>
	<script>
		alert('저장되었습니다')
		location.href = '${root}board/read'
	</script>

</body>
</html>

