<%@page import="com.javaex.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	UserVo userVo=(UserVo)request.getAttribute("userVo"); //객체에 무엇이 들어갈지 모르므로, 형변환을 해줘야 한다.
	//세션과 리퀘스트 헷갈리지 않게 데이터를 뽑아야함.
%>
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
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
		
				
		<!-- /navigation -->
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
			
		
		<div id="wrapper">
			<div id="content">
				<div id="user">
	
					<form id="join-form" name="joinForm" method="get" action="/mysite/user">

						
						<label class="block-label" for="name">이름</label>
						<input id="name" name="name" type="text" value="<%=userVo.getName() %>" />
	
						<label class="block-label" for="email">이메일</label>
						<strong><%=userVo.getEmail() %></strong> <!-- 못바꾸게 하기 위해서 input이 없음. -->
						
						<label class="block-label">패스워드</label>
						<input name="password" type="password" value="<%=userVo.getPassword() %>" />
						
						<fieldset>
							<legend>성별</legend>
							<% if ("male".equals(userVo.getGender()))  { %>
							<label>여</label> <input type="radio" name="gender" value="female" >
							<label>남</label> <input type="radio" name="gender" value="male" checked="checked">
							<% } else { %>
							<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
							<label>남</label> <input type="radio" name="gender" value="male">
							<% } %>
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
