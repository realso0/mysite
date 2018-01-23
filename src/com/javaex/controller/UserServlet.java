package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

//회원가입하고 회원관리 폼이 나오도록 해주는 작업
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("user 진입");
		request.setCharacterEncoding("utf-8");
		String actionform=request.getParameter("a");
		
		if ("joinform".equals(actionform)) {
			System.out.println("joinform 진입");
			//회원관리폼인 joinform은 따로 해줄 작업이 없고, 포워딩만 해주면 됨.
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinform.jsp"); //첫번째 슬래쉬(/)는 mysite를 의미함.
		} else if ("join".equals(actionform)){
			System.out.println("join 진입");
			
			String name = request.getParameter("name"); //joinform.jsp로부터 변수 값을 받아옴.
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender=request.getParameter("gender");
			
			UserVo userVo=new UserVo();
			
			userVo.setName(name);
			userVo.setEmail(email);
			userVo.setPassword(password);
			userVo.setGender(gender);
			
			System.out.println(userVo.toString()); //입력값 확인
			
			UserDao userDao=new UserDao();
			userDao.insert(userVo); //insert 확인메시지
			
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinsuccess.jsp"); //포워딩
		} else if ("loginform".equals(actionform)) {
			System.out.println("loginform 진입");
			//회원관리폼인 loginform은 따로 해줄 작업이 없고, 포워딩만 해주면 됨.
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginform.jsp"); //첫번째 슬래쉬(/)는 mysite를 의미함.
		} else if ("login".equals(actionform)) {
			System.out.println("login 진입");
			
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			
			//System.out.println(email+"/"+password); //로그인 클릭시 제대로 수행되는지 확인
			
			UserDao userDao=new UserDao();
			UserVo userVo=userDao.getUser(email, password);
			//userVo에 해당하는 no,name을 가지고 있음
			if(userVo==null) {
				System.out.println("로그인 실패"); //db 안에 해당 no,name이 없으면 null값임.
			} else {
				System.out.println("로그인 성공");
				HttpSession session=request.getSession(); //요청문서에서 session번호를 꺼내 저장함.
				session.setAttribute("authUser", userVo); //session은 안사라지는 공간이므로, userVo가 안 사라지게 session에 저장함.
			
				WebUtil.redirect(request, response, "/mysite/main"); //일반 main페이지가 아니라, 세션값이 서버에 있으면, 로그인으로 처리해주어야 함.
			}
		} else if ("logout".equals(actionform)) {
			HttpSession session=request.getSession();
			session.removeAttribute("authUser"); //지정된 이름에 해당하는 객체를 세션에서 제거한다.
			session.invalidate(); //해당세션을 없애고 세션에 속해있는 값들을 없앤다.
			
			WebUtil.redirect(request, response, "/mysite/main");
		} else if ("modifyform".equals(actionform)) {
			System.out.println("modifyform 진입");
			
			WebUtil.forward(request, response, "/WEB-INF/views/user/modifyform.jsp");
		} else if ("modify".equals(actionform)) {
			System.out.println("modify 진입");
			
			UserDao dao = new UserDao();
			UserVo vo = new UserVo();

			HttpSession session = request.getSession();
			UserVo userVo = (UserVo)session.getAttribute("authUser");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");

			vo.setName(name);
			vo.setPassword(password);
			vo.setGender(gender);
			vo.setNo(userVo.getNo());

			dao.modify(vo);

			session.setAttribute("authUser", vo);
			WebUtil.redirect(request, response, "/mysite/main");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
