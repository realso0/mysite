package com.javaex.dao;

import com.javaex.vo.UserVo;

public class DaoTest {

	public static void main(String[] args) {
		UserVo userVo=new UserVo();
		
//		userVo.setName("소한준");
//		userVo.setEmail("dda@naver.com");
//		userVo.setPassword("sadf");
//		userVo.setGender("male");
		
		UserDao userDao=new UserDao();
		
		//userDao.insert(userVo); //insert 테스트, dao의 insert메소드 확인작업
		
		userVo=userDao.getUser("23542", "234");
		
		System.out.println(userVo.toString());
	}

}
