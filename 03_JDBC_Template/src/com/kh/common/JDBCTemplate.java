package com.kh.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//공통템플릿 매번 반복적으로 작성될 코드를 메소드로 정의해둘거임
public class JDBCTemplate {
	//모든 메소드 다  static
	//이건 실행되자 마자 메모리 영역에 다올라감 
	//싱글톤 패턴 : 메모리영역에 단 한번만 올려두고 재사용하는 개념(math 클래스같은거)
	
	/**
	 * 1. 커낵션 객체생성 (DB와 접속)접속한 후 해당 Connection 객체 반환메소드 
	 * @return 
	 */
	public static Connection getConnection() {
		
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jdbc","jdbc");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/** 2. commit 처리해주는 메소드 .(conn전달받음)
	 * @param conn
	 */
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.commit();
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** 3. rollback 처리해주는 메소드 .(conn전달받음)
	 * @param conn	 
	 */
	public static void rollback (Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.rollback();
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/** 4.Statement관련 객체 전달받아서 반납시켜주는 메소드
	 * 	PreparedStatement 받을수 있음!! 다형성 적용!
	 * @param stmt
	 */
	public static void close(Statement stmt) {// preparedStatement는 자식이라 다형성으로 됨.
		try {			
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();

			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/** 5.conn관련 객체 전달받아서 반납시켜주는 메소드
	 * @param conn
	 */
	public static void close(Connection conn) {
		try {
			
			if(conn!=null&&!conn.isClosed()) {
				conn.close();
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//close(stmt), close(conn), close(rset) 함수명은같고 매개변수가 다를떄 : 오버로딩.
	/** 6.rset관련 객체 전달받아서 반납시켜주는 메소드
	 * @param rset
	 */
	public static void close(ResultSet rset) {
		try {
			
			if(rset!=null&&!rset.isClosed()) {
				rset.close();
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
}
