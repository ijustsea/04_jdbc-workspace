package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;
import com.kh.model.vo.Member;


public class MemberDao {
	
	public int insertMember(Connection conn, Member m) {
		//insert문 =>처리된 행수 => 트젝처리
		int result =0;
				
		PreparedStatement pstmt = null;
				
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USER_NO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
		
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
		
		String sql ="SELECT * FROM MEMBER";
		
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
		
		String sql = "SELECT * FROM MEMBER WHERE USERID = ?";
		
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
		
		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE ?"; 
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
		
		String sql = "UPDATE MEMBER SET USERPWD = ? , EMAIL = ? , PHONE = ?, ADDRESS = ? WHERE USERID = ?";
		
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
		
		String sql = "DELETE FROM MEMBER WHERE USERID = ?";
		
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
