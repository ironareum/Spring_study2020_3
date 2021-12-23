<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>미니 프로젝트</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>

	<c:set var="root" value="${pageContext.request.contextPath }/"></c:set>

	<!-- 상단 메뉴 부분 -->
	<c:import url="/WEB-INF/views/include/top_menu.jsp"></c:import>

	<div class="container" style="margin-top: 100px">
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6">
				<div class="card shadow">
					<div class="card-body">
						<!-- form태그 input 속성중 disabled:  -->
						<form:form action="${root}user/modify_pro" method="post" modelAttribute="modifyUserBean">
							<div class="form-group">
								<form:label path="user_name">이름</form:label> 
								<form:input type="text" path="user_name" class="form-control" disabled="true" />
							</div>
							<div class="form-group">
								<form:label path="user_id">아이디</form:label> 
								<form:input type="text" path="user_id" class="form-control"  disabled="true" />
							</div>
							<div class="form-group">
								<form:label path="user_pw">비밀번호</form:label> 
								<form:input type="password" path="user_pw" class="form-control"  />
							</div>
							<div class="form-group">
								<form:label path="user_pw2">비밀번호 확인</form:label> 
								<form:input type="password" path="user_pw2" class="form-control"  />
							</div>
							<div class="form-group">
								<div class="text-right">
									<form:button type="submit" class="btn btn-primary">정보수정</form:button>
								</div>
							</div>

						</form:form>
					</div>
				</div>
			</div>
			<div class="col-sm-3"></div>
		</div>
	</div>

	<!-- 하단 메뉴 부분 -->
	<c:import url="/WEB-INF/views/include/bottom_menu.jsp"></c:import>

</body>
</html>
