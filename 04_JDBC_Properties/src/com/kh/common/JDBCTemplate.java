package com.kh.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	
	
	/**
	 * 1. 커낵션 객체생성 (DB와 접속)접속한 후 해당 Connection 객체 반환메소드 
	 * @return 
	 */
	public static Connection getConnection() {
		/*
		 * 기존의 방식 : jdbc driver 구문, 접속할 db url 구문, 접속할 계정명 / 
		 * (정적 코딩방식) 비번들을 자바 소스코드내에 명시적으로 작성함.
		 * 
		 * >문제점: dbms 변경되었을경우, 접속 db의 url, 계정명/ 비번이 변경될경우 
		 * =>자바코드를 수정해야함. => 수정된 내용을 반영시키고자하면 프로그램 재구동해야함
		 * (프로그램 비정상적으로 종료해야하고 유지보수에 불편함을 느끼게 됨) 따라서 외부파일로 뺴는 방법
		 * 
		 * >해결방식: db관련정보를 별도 관리하는 외부파일(.properties) 생성해서 관리.
		 * (동적 코딩방식)=>외부파일로부터 읽어들여서 반영시킴 
		 * 
		 */	
		
		Connection conn = null;
		Properties prop = new Properties();//[]		
		
		try {
			prop.load(new FileInputStream("resources/driver.properties"));
			
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("username"),prop.getProperty("password"));
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
