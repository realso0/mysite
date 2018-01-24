<%@page import="com.javaex.vo.UserVo"%>
<%@page import="com.javaex.vo.GuestbookVo"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	UserVo authUser=(UserVo)session.getAttribute("authUser");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mysite/assets/css/main.css" rel="stylesheet"
	type="text/css">
<title>Mysite</title>
</head>
<body>
	<div id="container">

		<!-- /header -->
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>

		<!-- /navigation -->
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
		<div id="wrapper">
			<div id="content">
				<form action="gb" method="post">
						<h2>
							<center>방명록을 작성해주세요!</center>
						</h2>
						<% if(authUser!=null) { %>
						<table border=1 width=600>
							<tr>
								<td>이름</td>
								<td><input type="text" name="name" value="<%=authUser.getName() %>"></td>
								<td>비밀번호</td>
								<td><input type="password" name="pass"></td>
								<td><input type="hidden" name="a" value="add"></td>
							</tr>
							<tr>
								<td colspan=4><center>
										<textarea name="content" cols=70 rows=5></textarea>
									</center></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
							</tr>
						</table>
						<% } %>
						<br />
						
						<%
							List<GuestbookVo> list = (List) request.getAttribute("elist"); //Object형을 뱉어냄, Object형으로 형변환해서, 리스트를 꺼내야함.
							for (GuestbookVo vo : list) {
						%>

						<table border=1 width=400>
							<tr>
								<td><%=vo.getNo()%></td>
								<td><%=vo.getName()%></td>
								<td><%=vo.getRegDate()%></td>
								<td><a href="gb?a=deleteform&no=<%=vo.getNo()%>">삭제</a></td>
							</tr>
							<tr>
								<td colspan=4><%=vo.getContent()%></td>
							</tr>
						</table>
						<br />
						<%
							}
						%>
				</form>
			</div>
		</div>
		<!-- /footer -->
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>

	</div>
</body>
</html>