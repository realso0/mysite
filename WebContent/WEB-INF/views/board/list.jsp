<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>mysite</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="/mysite/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		
		<!-- /header -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		
		<!-- /navigation -->
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
			
		
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>				
					<c:forEach items="${requestScope.elist}" var="list">
					<tr>
						<td>1</td>
						<td><a href="">${list.title}</a></td>
						<td>${list.name}</td>
						<td>3</td>
						<td>${list.regDate}</td>
						<td><a href="" class="del">삭제</a></td>
					</tr>
					</c:forEach>
				</table>
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li><a href="">2</a"D:/javaStudy/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/work/Catalina/localhost/mysite/org/apache/jsp/WEB_002dINF/views/guestbook/list_jsp.java"></li>
						<li class="selected">3</li>
						<li><a href="">4</a></li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>				
				<div class="bottom">
					<a href="/mysite/board?a=write" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		
		<div id="footer">
			<p>(c)opyright 2015,2016,2017</p>
		</div> <!-- /footer -->
		
	</div>
</body>
</html>