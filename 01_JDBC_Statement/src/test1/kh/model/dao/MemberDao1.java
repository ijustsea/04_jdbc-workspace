package test1.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import test1.kh.model.vo.Member1;

public class MemberDao1 {

	public int insertMember(Member1 m) {
		//insert문 => 처리된행수 => 트랜잭션처리 
		//필요 변수 세팅
		int result =0;
		Connection conn = null;
		Statement stmt = null;
		
		//실행할 sql문 기입
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USER_NO.NEXTVAL,"
												+"'"+m.getUserId()+"',"
												+"'"+m.getUserPwd()+"',"
												+"'"+m.getUserName()+"',"
												+"'"+m.getGender()+"',"
													+m.getAge()+","
												+"'"+m.getEmail()+"',"
												+"'"+m.getPhone()+"',"
												+"'"+m.getAddress()+"',"
												+"'"+m.getHobby()+"',SYSDATE)";
		
		System.out.println(sql);
		
		//1.드라이버 2.conn객체 3.stmt객체 4.5.stmt로 sql 실행하면서 결과받기 6.트젝처리 7.역순으로 객체반납
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jdbc","jdbc");
			
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(sql);//insert라 행의 갯수가 나오는데 0이면 빈거고, 그외는 잘되는거 
			
			if(result >0) {//0아닌 그외 잘되었을때 
				conn.commit();
			}else {//0일떄 아무것도 없을때 
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

	public ArrayList<Member1> selectList() {
		// TODO Auto-generated method stub
		//DAO에서 변수세팅 -> 실행할 SQL문작성 ->JDBC 동작 흐름
		
		ArrayList<Member1> list = new ArrayList<Member1>();//[] empty
		//변수세팅 3총사 conn stmt rset
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		//실행할 SQL
		String sql = "SELECT * FROM MEMBER";
		
		//JDBC 동작 흐름 7가지
		//1.Driver 2.conn 3.stmt 4.5. sql 전달하면서 rset에 담기 6.트렌젝션 제어  7.역순반납 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jdbc","jdbc");
			
			stmt= conn.createStatement();
			
			rset =stmt.executeQuery(sql);
			
			while(rset.next()) {
				Member1 m = new Member1();
				
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
				//멤버1 객체 m에 한행 컬럼값들 하나하나씩 담기 반복 언제까지? 행들 다 넣을때까지 
				
				list.add(m); //전부넣은 m을 list에 담음 
				
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		
		
		return list;
	}

}
