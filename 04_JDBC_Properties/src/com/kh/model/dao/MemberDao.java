package com.kh.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*;
import com.kh.model.vo.Member;


public class MemberDao {
	
	/*
	 * 기존의 방식 : DAO 클래스에 사용자가 요청할때마다 실행되는 SQL 문을 자바소스코드에 명시적으로 작성 =>정적코딩방식
	 * 정적 문제점 : SQL문 수정해야 될 경우 자바소스 코드를 수정해야되고 반영시키려면 프로그램재구동해야하는 번거로움.
	 * 
	 * 문제 해결법 : SQL문들만 별도로 관리하는 외부파일(.xml)을 만들어서 실시간으로 그 파일에 기록된 sql문을 읽음 =>동적코딩방식
	 *  
	 */
	
	//전역변수로 프로펄티즈 선언
	private Properties prop = new Properties(); 
	
	//사용자가 어떤 서비스 요청할떄마다 new MemberDao().xxx
	//그럼 기본생성자가 매번실행되니 기본생성자에 쿼리.xml 읽게 하는 기능 적어두자? 
	public MemberDao() {//기본생성자
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public int insertMember(Connection conn, Member m) {
		//insert문 =>처리된 행수 => 트젝처리
		int result =0;
				
		PreparedStatement pstmt = null;
				
		String sql = prop.getProperty("insertMember");
		
		try {						
			
			pstmt= conn.prepareStatement(sql);
						
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
					
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//conn 객체는 반납하지마세용  why service에서 처리할게 남아있음.
			close(pstmt);						
		}		
		
		return result;
	}
	

	public ArrayList<Member> selectList(Connection conn){
		//selectans(여러행) => ResultSet => ArrayList
		ArrayList<Member> list = new ArrayList<Member>();//[]
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql =prop.getProperty("selectList");
		
		try {			
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Member(rset.getInt("USERNO"), rset.getString("USERID"), rset.getString("USERPWD"),
						rset.getString("USERNAME"), rset.getString("GENDER"), rset.getInt("AGE"),
						rset.getString("EMAIL"), rset.getString("PHONE"), rset.getString("ADDRESS"),
						rset.getString("HOBBY"), rset.getDate("ENROLLDATE") ));		
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			//이왕이면 conn을 만든곳에서 반납하자.
		}
		
		return list;
		
	}

	
	public Member selectByUserId(Connection conn, String userId) {
		
		Member m = null;		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectByUserId");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(rset.getInt("USERNO"), rset.getString("USERID"), rset.getString("USERPWD"),
						rset.getString("USERNAME"), rset.getString("GENDER"), rset.getInt("AGE"),
						rset.getString("EMAIL"), rset.getString("PHONE"), rset.getString("ADDRESS"),
						rset.getString("HOBBY"), rset.getDate("ENROLLDATE") );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			//만든곳에서 반납.
		}
			
		return m;
	}


	public ArrayList<Member> selectByUserName(Connection conn, String keyword) {
		ArrayList<Member> list  = new ArrayList<Member>();//[]
		
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectByUserName"); 
					//1번 "SELECT * FROM MEMBER WHERE USERNAME LIKE '%' || ? || '%'";
		try {		
			
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
						rset.getString("HOBBY"), rset.getDate("ENROLLDATE")	));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			//이왕이면 호출한곳에서 
		}	
		
		return list;
	}


	public int updateMember(Connection conn, Member m) {
		int result =0;
		
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateMember"); 
		
		try {			
			
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserPwd());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getUserId());
			
			result = pstmt.executeUpdate();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			close(pstmt);			
		}		
		
		return result;
	}


	public int deleteMember(Connection conn, String userId) {
		int result =0;
		
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteMember"); 
		
		try {
			
			
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
				
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);			
		}	
		
		return result;
	}
	
	
	
	
}
