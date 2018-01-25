<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="/mysite/assets/css/user.css" rel="stylesheet" type="text/css">
	<title>Insert title here</title>
</head>
<body>

	<div id="container">
		
		<!-- /header -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
				
		<!-- /navigation -->
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
				
		
		<div id="wrapper">
			<div id="content">
				<div id="user">
	
					<form id="join-form" name="joinForm" method="get" action="/mysite/user">

						
						<label class="block-label" for="name">이름</label>
						<input id="name" name="name" type="text" value="${requestScope.userVo.name}" />
	
						<label class="block-label" for="email">이메일</label>
						<strong>${requestScope.userVo.email}</strong> <!-- 못바꾸게 하기 위해서 input이 없음. -->
						
						<label class="block-label">패스워드</label>
						<input name="password" type="password" value="${requestScope.userVo.password}" />
						
						<fieldset>
							<legend>성별</legend>
							<c:if test="${requestScope.userVo.gender=='male'}">
							<label>여</label> <input type="radio" name="gender" value="female" >
							<label>남</label> <input type="radio" name="gender" value="male" checked="checked">
							</c:if>
							<c:if test="${requestScope.userVo.gender=='female'}">
							<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
							<label>남</label> <input type="radio" name="gender" value="male">
							</c:if>
						</fieldset>
						<input type="text" name="a" value="modify"> <!-- 무조건  modify작업을 수행하도록 지정 -->
						<input type="submit" value="수정완료">
					</form>
				</div><!-- /user -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<!-- /footer -->
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
		
		
	</div> <!-- /container -->

</body>
</html>
