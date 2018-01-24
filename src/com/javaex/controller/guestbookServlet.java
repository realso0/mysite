package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.GuestbookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVo;
import com.javaex.vo.UserVo;


@WebServlet("/gb")
public class guestbookServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("환영합니다 Servlet guestbook2"); //웹페이지와 연결됬는지 확인
		request.setCharacterEncoding("utf-8");
		String actionform=request.getParameter("a"); //주소 창에 입력받는 값은 값으로 a를 하나 만들어줌. 요청문서에서 a를 얻어옴.
		
		if ("list".equals(actionform)) {
			System.out.println("list 진입");
			
			GuestbookDao dao=new GuestbookDao();
			List<GuestbookVo> list=dao.getList(); 
			
			//어떤 데이터가 올지 모르므로, RequestDispatcher 객체에 담아 보냄.
			request.setAttribute("elist", list);
			//어떤 데이터가 올지 모르므로, RequestDispatcher 객체에 담아 보냄.
//			RequestDispatcher rd=request.getRequestDispatcher("list.jsp"); //포워드 작업
//			rd.forward(request,response);
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
			
		} else if ("deleteform".equals(actionform)) {
			System.out.println("deleteform 진입"); //여기까지 웹 접속되는지 확인
			
			int no = Integer.parseInt(request.getParameter("no"));
			request.setAttribute("no", no); //no라는 값을 별명을 no로 해서 받음.
			
//			RequestDispatcher rd=request.getRequestDispatcher("deleteform.jsp"); //값을 입력받아, RequestDispatcher객체가 만들어지고, 
//			//rd로 객체를 받는다. request클래스 내의 getRequestDispatcher메소드를 이용해, deleteform.jsp로 보낸다.
//			rd.forward(request, response); //rd의 forword메소드를 이용하여, request문서와 response문서를 deleteform.jsp로 보낸다.
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteform.jsp");
			
		} else if ("add".equals(actionform)) {
			System.out.println("add 진입");
			
			String name=request.getParameter("name"); //폼에서 약속한 변수이름의 값을 받아옴.
			String password=request.getParameter("pass");
			String content=request.getParameter("content");
			
			GuestbookVo vo=new GuestbookVo(1,name,password,content,"");

			vo.setName(name);
			vo.setPassword(password);
			vo.setContent(content);
			
			//System.out.println(vo.toString());
			
			GuestbookDao dao=new GuestbookDao();
			
			dao.insert(vo);
			
//			response.sendRedirect("gb?a=list");
			WebUtil.redirect(request, response, "/mysite/gb?a=list");
			
		} else if ("delete".equals(actionform)) {
			System.out.println("delete 진입");
			
			int no = Integer.parseInt(request.getParameter("no"));
			String password=request.getParameter("password");

			GuestbookDao dao=new GuestbookDao();
			dao.delete(no, password);
			
//			response.sendRedirect("gb?a=list");
			WebUtil.redirect(request, response, "/mysite/gb?a=list");
			
		} else {
			System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
