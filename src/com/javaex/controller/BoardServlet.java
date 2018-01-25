package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDao;
import com.javaex.dao.GuestbookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

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
			
			BoardDao dao=new BoardDao();
			List<BoardVo> list=dao.getList(); 
			
			request.setAttribute("elist", list);
			
			System.out.println("list 뿌려주기 성공");
			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp"); //첫번째 슬래쉬(/)는 mysite를 의미함.
		} else if ("write".equals(actionform)) {
			System.out.println("write 진입");
			
			HttpSession session=request.getSession(); //요청문서에서 session번호를 꺼내 저장함.
			UserVo authUser=(UserVo)session.getAttribute("authUser");
			
			if (authUser==null) {
				//세션 없다면 로그인폼으로 이동(주소창에 바로 치고 들어오는 사람들이 존재하므로)
				WebUtil.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
			} else { //로그인 상태
				WebUtil.forward(request, response, "/WEB-INF/views/board/write.jsp");
			}
		} else if ("insert".equals(actionform)) {
			System.out.println("insert 진입");
			
			HttpSession session=request.getSession(); //요청문서에서 session번호를 꺼내 저장함.
			UserVo authUser=(UserVo)session.getAttribute("authUser");
			
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			int userNo=authUser.getNo();
			
			BoardVo vo=new BoardVo();
			
			vo.setTitle(title);
			vo.setContent(content);
			vo.setUserNo(userNo);
			
			BoardDao dao=new BoardDao();
			
			dao.insert(vo);
			
			WebUtil.redirect(request, response, "/mysite/board?a=list");
		} else if ("view".equals(actionform)) {
			
			
			WebUtil.forward(request, response, "/WEB-INF/views/board/view.jsp");
		} else if ("delete".equals(actionform)) {
			HttpSession session=request.getSession(); //요청문서에서 session번호를 꺼내 저장함.
			
			int no=Integer.valueOf(request.getParameter("no"));
			int userNo = Integer.valueOf(request.getParameter("userNo"));
			UserVo authUser=(UserVo)session.getAttribute("authUser");
			
			if (userNo==authUser.getNo()) {
				BoardDao dao=new BoardDao();
				dao.delete(no);
				WebUtil.redirect(request, response, "/mysite/board?a=list");
			} else {
				WebUtil.redirect(request, response, "/mysite/board?a=list");
			}
			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
