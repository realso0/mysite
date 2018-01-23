<%@page import="com.javaex.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	
	UserVo authUser=(UserVo)session.getAttribute("authUser"); //객체에 무엇이 들어갈지 모르므로, 형변환을 해줘야 한다.

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
		
		<div id="header">
			<h1>MySite</h1>
			<ul>
				<!-- 로그인 전 -->
				<li><a href="/mysite/user?a=loginform">로그인</a></li>
				<li><a href="/mysite/user?a=joinform">회원가입</a></li>
				
				<!-- 로그인 후 -->
				<!-- 
				<li><a href="">회원정보수정</a></li>
				<li><a href="">로그아웃</a></li> 
				<li> 황일영님 안녕하세요^^;</li>
				-->
			</ul>
		</div> <!-- /header -->
				
		<div id="navigation">
			<ul>
				<li><a href="">소한준</a></li>
				<li><a href="">방명록</a></li>
				<li><a href="">게시판</a></li>
			</ul>
		</div> <!-- /navigation -->
		
		<div id="wrapper">
			<div id="content">
				<div id="user">
	
					<form id="join-form" name="joinForm" method="get" action="/mysite/user">

						
						<label class="block-label" for="name">이름</label>
						<input id="name" name="name" type="text" value=<%=authUser.getName()%> />
	
						<label class="block-label" for="email">이메일</label>
						<strong><input name="email" type="email" value=<%=authUser.getEmail()%> /></strong>
						
						<label class="block-label">패스워드</label>
						<input name="password" type="password" value=<%=authUser.getPassword()%> />
						
						<fieldset>
							<legend>성별</legend>
							
							<label>여</label> <input type="radio" name="gender" value="female" >
							<label>남</label> <input type="radio" name="gender" value="male" checked="checked">
							
						</fieldset>
						<input type="text" name="a" value="modify"> <!-- 무조건  modify작업을 수행하도록 지정 -->
						<input type="submit" value="수정완료">
					</form>
				</div><!-- /user -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<div id="footer">
			<p>(c)opyright 2015,2016,2017</p>
		</div> <!-- /footer -->
		
	</div> <!-- /container -->

</body>
</html>
