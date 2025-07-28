package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.model.vo.Member;

//데이터 작업
//DAO(Data Access Object) : DB에 직접적으로 접근해서 사용해서 사용자의 요청에 맞는 SQL문실행후 결과받기
//결과를 Controller로 다시 리턴. 
public class MemberDao {

	/**
	 * 사용자가 입력한 정보들을 추가시켜주는 메소드
	 * 
	 * @param m : (사용자가 입력한 값들이 담겨있는 Member객체 )
	 * @return : insert문 수행후 처리된 행의 갯수
	 */
	public int insertMember(Member m) {
		// insert문 => 처리된행수 => 트랜잭션처리
		// 필요변수 세팅

		int result = 0; // 처리된 결과 받아줄 인트형 변수
		Connection conn = null;// 연결된 DB 연결정보 담는객체
		Statement stmt = null;// 완성된 SQL문 전달해서 곧바로 실행후 결과 받는 객체

		// 실행할 SQL문
		// INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, 'XXX', 'XXX', 'XXX', 'X', XX,
		// 'XXXX','XXX','XXXXXX', 'XXX', SYSDATE);
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USER_NO.NEXTVAL," + "'" + m.getUserId() + "',"
																	+ "'" + m.getUserPwd() + "'," 
																	+ "'" + m.getUserName() + "'," 
																	+ "'" + m.getGender() + "'," 
																	+ m.getAge() + ","
																	+ "'" + m.getEmail() + "',"
																	+ "'" + m.getPhone() + "'," 
																	+ "'" + m.getAddress() + "'," 
																	+ "'" + m.getHobby() + "',SYSDATE)";

		System.out.println(sql);

		try {
			// 1)driver연결
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2)커낵션 객체생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbc", "jdbc");

			// 3)stmt 객체생성
			stmt = conn.createStatement();

			// 4.5) sql전달하면서 실행후 결과받기 .
			result = stmt.executeUpdate(sql);

			// 6) 트잭처리
			if (result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {// 7.다쓴 jdbc용 객체 반납
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return result;

	}

	/**
	 * 사용자가 요청한 회원전체 조회 처리해주는 메소드
	 * 
	 * @return
	 */
	public ArrayList<Member> selectList() {
		// TODO Auto-generated method stub
		// 여러행 조회 일떄는 결과를 ResultSet객체에 담고 => ArrayList 차곡차곡 담기

		// 변수셋팅
		ArrayList<Member> list = new ArrayList<>(); // [] empty

		// 변수세팅 3총사
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		// 실행할 SQL문
		String sql = "SELECT * FROM MEMBER";

		try {
			// JDBC 드라이버
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection 객체생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbc", "jdbc");
			// stmt 객체생성
			stmt = conn.createStatement();
			// 4.5) sql전달하면서 실행후 결과받기
			rset = stmt.executeQuery(sql);

			while (rset.next()) {
				Member m = new Member();// 기본생성자로 생성

				m.setUserNo(rset.getInt("USERNO"));
				m.setUserId(rset.getString("USERID"));
				m.setUserPwd(rset.getString("USERPWD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));
				// 현재 참조하고 있는 행에 대한 모든 컬럼에 대한 데이터들은 하나의 Member 객체 m에 담기끝

				list.add(m);
			}

			// 반복문이 다 끝난시점에 만약에 조회된 데이터가 하나도 없었다면
			// list는 텅빈상태 | 조회된 상태가 있었다면 list에 뭐라도 담김

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

		return list;

	}

	/**
	 * 사용자 아이디로 회원검색 요청처리해주는 멧도ㅡ
	 * 
	 * @param userId : 사용자가 입력한 검색하고자한 아이디
	 * @return :아이디와 일치하는 조회된결과를 담은 맴버객체 반환 , 없을시 null 반환
	 */
	public Member selectByUserId(String userId) {
		// SELECT 문 한행 => ResultSet객체
		// ArrayList 굳이 필요없음 Member 객체 하나면 될듯

		// 변수세팅
		Member m = null;// 조회결과있을수도 있고 없을수도 있음
		// 3총사
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		// sql
		String sql = "SELECT * FROM MEMBER WHERE USERID ='" + userId + "'";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbc", "jdbc");

			stmt = conn.createStatement();

			rset = stmt.executeQuery(sql);

			if (rset.next()) {// 한행이라도 조회됬을시
				m = new Member(rset.getInt("USERNO"), rset.getString("USERID"), rset.getString("USERPWD"),
						rset.getString("USERNAME"), rset.getString("GENDER"), rset.getInt("AGE"),
						rset.getString("EMAIL"), rset.getString("PHONE"), rset.getString("ADDRESS"),
						rset.getString("HOBBY"), rset.getDate("ENROLLDATE"));

			}

			// 조건문 끝난시점 조회된데이터 없는 경우 => m =null;
			// 있는 경우 => m에 뭐라도 담겨있음

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return m;
	}

	/**
	 * 사용자로부터 키워드 검색 요청시 처리해주는 메소드
	 * 
	 * @param keyword : 사용자가 검색하려는 키워드
	 * @return 어레이리스트제네릭 맴버 리스트 반환
	 */
	public ArrayList<Member> selectByUserName(String keyword) {
		// select문수행(여러행) =>resultset => arraylist
		ArrayList<Member> list = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE '%" + keyword + "%'";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbc", "jdbc");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);

			while (rset.next()) {// 여러행일떈 while 한행은 if

				list.add(new Member(rset.getInt("USERNO"),
									rset.getString("USERID"),
									rset.getString("USERPWD"),
									rset.getString("USERNAME"),
									rset.getString("GENDER"),
									rset.getInt("AGE"),									
									rset.getString("EMAIL"),
									rset.getString("PHONE"), 
									rset.getString("ADDRESS"),
									rset.getString("HOBBY"),
									rset.getDate("ENROLLDATE")

				));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return list;// 텅빈리스트거나 값이 담긴 리스트 반환

	}

	/**
	 * 사용자가 입력한 아이디의 정보변경을 처리해줌
	 * 
	 * @param m
	 * @return 인트형
	 */
	public int updateMember(Member m) {
		// 변수선언 update문 => 처리된 행수 int변수
		int result = 0;

		// 2총사
		Connection conn = null;
		Statement stmt = null;

		String sql = "UPDATE MEMBER SET " + "USERPWD = " + "'" + m.getUserPwd() + "'" 
									+ ", EMAIL = " + "'" + m.getEmail() + "'" 
									+ ", PHONE = " + "'" + m.getPhone() + "'" 
									+ ", ADDRESS = " + "'" + m.getAddress()	+ "'" 
									+ "WHERE USERID = " + "'" + m.getUserId() + "'";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbc", "jdbc");
			stmt = conn.createStatement();

			result = stmt.executeUpdate(sql);

			if (result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return result;

	}

	public int deleteMember(String userId) {
		// delete문 => 지워진 행
		int result = 0;
		Connection conn = null;
		Statement stmt = null;

		String sql = "DELETE FROM MEMBER WHERE USERID = " + "'" + userId + "'";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbc", "jdbc");
			stmt = conn.createStatement();

			result = stmt.executeUpdate(sql);

			if (result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return result;
	}

}
