package com.javaex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.util.WebUtil;

@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("board 진입");
		request.setCharacterEncoding("utf-8");
		String actionform=request.getParameter("a");
		
		if ("list".equals(actionform)) {
			System.out.println("list 진입");
			//게시판인 list는 따로 해줄 작업이 없고, 포워딩만 해주면 됨.
			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp"); //첫번째 슬래쉬(/)는 mysite를 의미함.
		} else if ("write".equals(actionform)) {
			System.out.println("write 진입");
			
			WebUtil.forward(request, response, "/WEB-INF/views/board/write.jsp");
			
			
			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
