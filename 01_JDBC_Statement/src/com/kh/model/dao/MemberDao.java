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
	 * @param m : (사용자가 입력한 값들이 담겨있는 Member객체 )
	 * @return : insert문 수행후 처리된 행의 갯수
	 */
	public int insertMember(Member m) {
		//insert문 => 처리된행수 => 트랜잭션처리 
		
		//필요변수 세팅
		
		int result=0; //처리된 결과 받아줄 인트형 변수
		Connection conn = null;//연결된 DB 연결정보 담는객체
		Statement stmt = null;//완성된 SQL문 전달해서 곧바로 실행후 결과 받는 객체
		
		//실행할 SQL문
		//INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, 'XXX', 'XXX', 'XXX', 'X', XX, 'XXXX','XXX','XXXXXX', 'XXX', SYSDATE);
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USER_NO.NEXTVAL,"
												+"'"+m.getUserId() +"',"
												+"'"+m.getUserPwd() +"',"
												+"'"+m.getUserName() +"',"
												+"'"+m.getGender() +"',"
													+m.getAge() +","
												+"'"+m.getEmail() +"',"
												+"'"+m.getPhone() +"',"
												+"'"+m.getAddress() +"',"
												+"'"+m.getHobby() +"',SYSDATE)";								
												
												
												
		System.out.println(sql);
		
		try {
			//1)driver연결
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2)커낵션 객체생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jdbc","jdbc");
		
			//3)stmt 객체생성
			stmt =conn.createStatement();
			
			//4.5) sql전달하면서 실행후 결과받기 .
			result = stmt.executeUpdate(sql);

			//6) 트잭처리
			if(result>0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {//7.다쓴 jdbc용 객체 반납
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
	 * @return
	 */
	public ArrayList<Member> selectList() {
		// TODO Auto-generated method stub
		// 여러행 조회 일떄는 결과를 ResultSet객체에 담고 => ArrayList 차곡차곡 담기
		
		//변수셋팅
		ArrayList<Member> list =new ArrayList<>(); //[] empty
		
		// 변수세팅 3총사
		Connection conn =null;
		Statement stmt = null;
		ResultSet rset = null;
		
		//실행할 SQL문
		String sql = "SELECT * FROM MEMBER";
		
 
		try {
			//JDBC 드라이버
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Connection 객체생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jdbc","jdbc");
			//stmt 객체생성
			stmt = conn.createStatement();
			//4.5) sql전달하면서 실행후 결과받기
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				Member m = new Member();//기본생성자로 생성
				
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
				//현재 참조하고 있는 행에 대한 모든 컬럼에 대한 데이터들은 하나의 Member 객체 m에 담기끝
				
				list.add(m);
			}
			
			//반복문이 다 끝난시점에 만약에 조회된 데이터가 하나도 없었다면 
			//list는 텅빈상태 | 조회된 상태가 있었다면 list에 뭐라도 담김
			
			
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
	
	
	
	
	
	
	
	
	
}
