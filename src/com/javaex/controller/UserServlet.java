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
				
				WebUtil.redirect(request, response, "/mysite/user?a=loginform&result=fail"); //실패시에만 오류메시지 출력하도록 하기위한 작업
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
			//세션에서 no(pk값) 가져오기(session에서 getNo()), 만약 없다면? 직접 주소에 a=modify치고 들어오는 사람이 있다면, 세션에 값이 없어도 수정을 하는 경우가 생김
			HttpSession session= request.getSession();
			UserVo authUser=(UserVo)session.getAttribute("authUser");
			
			if (authUser==null) {
				//세션 없다면 로그인폼으로 이동
			} else { //로그인 상태
				//로그인회원의 no
				int no=authUser.getNo();
				//데이터 가져옴
				UserDao userDao=new UserDao();
				UserVo userVo=userDao.getUser(no);
				//담음
				//데이터 리퀘스트에 담아 보내기
				request.setAttribute("userVo", userVo);
				WebUtil.forward(request, response, "/WEB-INF/views/user/modifyform.jsp");
			}
		} else if ("modify".equals(actionform)) {
			System.out.println("modify 진입");
			
			//session정보도 업데이트 해준다.
			HttpSession session= request.getSession();
			UserVo authUser=(UserVo)session.getAttribute("authUser");//UserVo에 맞춰서, 세션공간의 주소값이 authUser에 저장됨.
			
			if (authUser==null) {
				//세션 없다면 로그인폼으로 이동
			} else { //로그인 상태
				//modifyform에서 변수값 넘겨받기.
				//vo(no,name,password,gender)을 받아, vo에 저장하기
				int no=authUser.getNo();
				String name=request.getParameter("name");
				String password=request.getParameter("password");
				String gender=request.getParameter("gender");
				
				UserVo userVo=new UserVo(no,name,"",password,gender);
				
				//dao.update(userVo) 그 값들로 업데이트해주기
				UserDao userDao=new UserDao();
				userDao.update(userVo);
				
				//session에 있는 name도 바뀌어야, main홈에서 사용자의 이름이 바뀜, no는 고유값이므로 바꾸지x
				authUser.setName(name); //UserVo 클래스이므로, 생성자와 메소드 이용가능. 
				
				//main으로 redirect
				WebUtil.redirect(request, response, "/mysite/main");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
