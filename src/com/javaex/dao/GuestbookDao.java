package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestbookVo;
import com.javaex.vo.UserVo;

public class GuestbookDao {
	
	public void insert(GuestbookVo vo) { //매개변수 여러개 받기 위해 리스트로 받음
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
			String query = " insert into guestbook " + 
							" values (seq_guestbook_no.nextval, ?, ?, ?, sysdate) ";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContent());
			
			int count = pstmt.executeUpdate();
			
			//System.out.println(count+"건 저장완료");
			
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
	public void delete(int no, String password) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		// ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = " delete from guestbook " + 
							" where no=? and password=? "; // 쿼리문
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no); // 첫번째 물음표가 2인 것
			pstmt.setString(2, password);
			
			int count = pstmt.executeUpdate();
			
			//System.out.println(count + "건 삭제완료");

			// 4.결과처리

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				// if (rs != null) {
				// rs.close();
				// }
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
	
	public List<GuestbookVo> getList() {
		// 0. import java.sql.*;
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				// 이전까지는 while문에서 괄호 밖으로 나가면 값이 사라짐. 이를 해결하기 위해, 밖에서 리스트 생성
				List<GuestbookVo> guestList = new ArrayList<GuestbookVo>();

				try {
					// 1. JDBC 드라이버 (Oracle) 로딩
					Class.forName("oracle.jdbc.driver.OracleDriver");

					// 2. Connection 얻어오기
					String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 연결된 ip주소, 포트
					conn = DriverManager.getConnection(url, "webdb", "webdb"); // 연결 url, 아이디, 비밀번호

					// 3. SQL문 준비 / 바인딩 / 실행
					String query = " select no, " + 
									"        name, " + 
									"        password, " + 
									"        content, " + 
									"        reg_date " + 
									" from guestbook ";
					pstmt = conn.prepareStatement(query);

					rs = pstmt.executeQuery();

					// 4.결과처리
					while (rs.next()) {
						GuestbookVo vo = new GuestbookVo();
						
						int no = rs.getInt("no");
						String name = rs.getString("name");
						String password = rs.getString("password");
						String content = rs.getString("content");
						String regDate = rs.getString("reg_date");
						
						vo.setNo(no);
						vo.setName(name);
						vo.setPassword(password);
						vo.setContent(content);
						vo.setRegDate(regDate);
						
						guestList.add(vo);

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
				return guestList; // list 결과값 출력	
	}
}
