package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.kh.model.vo.Member;

//데이터 작업
//DAO(Data Access Object) : DB에 직접적으로 접근해서 사용해서 사용자의 요청에 맞는 SQL문실행후 결과받기
//결과를 Controller로 다시 리턴. 
public class MemberDao {
	
	/*
	 * < Statement & PreparedStatement > 
	 * ~둘 다 sql문 실행 후 결과를 받아내는 객체
	 * 
	 * Statement & PreparedStatement 차이점
	 * 
	 *-Statement의 경우 sql문 바로 전달하면서 실행시키는 객체.
	 * (즉, sql문을 완성형태로 만들어야한다. 사용자가 입력한값이 다채워진형태로)
	 * 
	 * >> 기존 Statemnet 방식
	 * 1)Connection객체로 Statement/ 객체 생성 : stmt = conn.createStatement();
	 * 2)Statement객체를 통해 "완성된 sql문" 실행 및 결과 받기 : 결과 = stmt.excutexxxx(완성된 sql); 
	 * 
	 *-PreparedStatement 같은 경우 "미완성된 sql문"을 잠시 보관해둘수있는 객체 
	 * (즉, 사용자가 입력한 값을 채워두지않고 각각 들어갈 공간을 확보만 해놓아두됨)
	 * 단, 해당 sql문 본격적으로 실행하기전에는 빈공간을 사용자가 입력한 값으로 채워서 실행하긴 해야함 
	 * 
	 * >> PreparedStatement 방식
	 * 1)Connection객체로 PreparedStatement/ 객체 생성 : pstmt = conn.preparedStatement(미완성된 sql);  
	 * 2)pstmt에 담긴 sql문이 미완성일 경우 우선 완성시켜야함. pstmt.setxxx(1|2|3. "대체값")
	 * 3)완성된 sql문을 실행하고 결과받기/ 결과 : pstmt.execuexxxx(완성된 sql);
	 *  
	 */

	/** 회원 추가 메소드 
	 * @param m
	 * @return
	 */
	public int insertMember(Member m) {
		//insert문 =>처리된 행수 => 트젝처리
		int result =0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		//실행할 sql 문
		//INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, 'XXX', 'XXX', 'XXX', 'X', XX,
		// 'XXXX','XXX','XXXXXX', 'XXX', SYSDATE);
		// 미리 사용자가 입력할 값들 공간만 확보만 해두면됨 
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USER_NO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jdbc","jdbc");
			
			pstmt= conn.prepareStatement(sql);//애초에 pstmt 객체 생성시 sql 담은채로 생성후 앞으로 빈공간 채우기
			//> 빈공간을 실제값으로 채워준 후 실행 
			// pstmt.setString(홀더순번, 대체할값); => '대체할값'
			//pstmt.setInt(홀더순번, 대체할값);  => 대체할값
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			
			result = pstmt.executeUpdate();
			
			if(result>0){
				conn.commit();
			}else {
				conn.rollback();
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		return result;
	}
	
	
	/** 전체 조회 하는 메소드
	 * @return
	 */
	public ArrayList<Member> selectList(){
		//selectans(여러행) => ResultSet => ArrayList
		ArrayList<Member> list = new ArrayList<Member>();//[]
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql ="SELECT * FROM MEMBER";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jdbc","jdbc");
			
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Member(rset.getInt("USERNO"), rset.getString("USERID"), rset.getString("USERPWD"),
						rset.getString("USERNAME"), rset.getString("GENDER"), rset.getInt("AGE"),
						rset.getString("EMAIL"), rset.getString("PHONE"), rset.getString("ADDRESS"),
						rset.getString("HOBBY"), rset.getDate("ENROLLDATE") ));		
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
		
	}


	/** 회원 아이디 검색
	 *  select문  => 한행만 => ResultSet => Member객체 하나만
	 * @param userId
	 * @return
	 */
	public Member selectByUserId(String userId) {
		
		Member m = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM MEMBER WHERE USERID = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jdbc","jdbc");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(rset.getInt("USERNO"), rset.getString("USERID"), rset.getString("USERPWD"),
						rset.getString("USERNAME"), rset.getString("GENDER"), rset.getInt("AGE"),
						rset.getString("EMAIL"), rset.getString("PHONE"), rset.getString("ADDRESS"),
						rset.getString("HOBBY"), rset.getDate("ENROLLDATE") );
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		return m;
	}


	/** 회원 이름 키워드 검색
	 * @param keyword
	 * @return
	 * 
	 * select문  => 한행만 => ResultSet => Member객체 하나만
	 */
	public ArrayList<Member> selectByUserName(String keyword) {
		ArrayList<Member> list  = new ArrayList<Member>();//[]
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE ?"; 
					//1번 "SELECT * FROM MEMBER WHERE USERNAME LIKE '%' || ? || '%'";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jdbc","jdbc");
			
			pstmt = conn.prepareStatement(sql);
			//1)해결방법
			//pstmt.setString(1, keyword);
			//2)해결방법
			pstmt.setString(1, "%"+keyword+"%");
			
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Member(rset.getInt("USERNO"), rset.getString("USERID"), rset.getString("USERPWD"),
						rset.getString("USERNAME"), rset.getString("GENDER"), rset.getInt("AGE"),
						rset.getString("EMAIL"), rset.getString("PHONE"), rset.getString("ADDRESS"),
						rset.getString("HOBBY"), rset.getDate("ENROLLDATE")
						
						
						));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		
		return list;
	}


	public int updateMember(Member m) {
		int result =0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE MEMBER SET USERPWD = ? , EMAIL = ? , PHONE = ?, ADDRESS = ? WHERE USERID = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jdbc","jdbc");
			
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserPwd());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getUserId());
			
			result = pstmt.executeUpdate();
			if(result >0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {				
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
		return result;
	}


	public int deleteMember(String userId) {
		int result =0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM MEMBER WHERE USERID = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jdbc","jdbc");
			
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
				
			result = pstmt.executeUpdate();
			
			if(result >0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {				
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
