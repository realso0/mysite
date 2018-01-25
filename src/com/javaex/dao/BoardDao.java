package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVo;
import com.javaex.vo.GuestbookVo;
import com.javaex.vo.UserVo;

public class BoardDao {

	public List<BoardVo> getList() {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 이전까지는 while문에서 괄호 밖으로 나가면 값이 사라짐. 이를 해결하기 위해, 밖에서 리스트 생성
		List<BoardVo> boardList = new ArrayList<BoardVo>();

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 연결된 ip주소, 포트
			conn = DriverManager.getConnection(url, "webdb", "webdb"); // 연결 url, 아이디, 비밀번호

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = " select bo.no, " + 
							"        title, " + 
							"        us.name, " + 
							"        hit, " + 
							"        reg_date, " + 
							"        user_no " + 
							" from board bo, users us " + 
							" where bo.user_no=us.no ";
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				BoardVo vo = new BoardVo();

				int no = rs.getInt("no");
				String title = rs.getString("title");
				String name= rs.getString("name");
				int hit = rs.getInt("hit");
				String regDate = rs.getString("reg_date");
				int userNo= rs.getInt("user_no");

				vo.setNo(no);
				vo.setTitle(title);
				vo.setName(name);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setUserNo(userNo);

				boardList.add(vo);

			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}
		return boardList; // list 결과값 출력

	}	
	
	public void insert(BoardVo vo) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = " insert into board " + 
							" values (seq_board_id.nextval,?,?,?,sysdate,?) ";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, vo.getTitle());	// 웹으로부터 값을 받아서, 가져옴.
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, 0);
			pstmt.setInt(4, vo.getUserNo());

			int count = pstmt.executeUpdate();

			System.out.println(count + "건 저장완료"); // db에 값이 저장되고, 확인할 수 있도록 출력창에 출력.

			// 4.결과처리

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}
	}
}
