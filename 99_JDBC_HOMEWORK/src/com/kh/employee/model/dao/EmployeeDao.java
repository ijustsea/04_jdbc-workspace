package com.kh.employee.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.employee.model.vo.Employee;

public class EmployeeDao {
	
	//INSERT
	public int insertEmployee(Employee e) {
		int result =0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO COPY_EMP VALUES(SEQ_EMPID.NEXTVAL, ?, ?, ?, NULL, ?, ?, ?, ?, ?, ?, SYSDATE, NULL, DEFAULT)";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kh","kh");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, e.getEmpName());
			pstmt.setString(2, e.getEmpNo());
			pstmt.setString(3, e.getEmail());
			pstmt.setString(4, e.getDeptCode());
			pstmt.setString(5, e.getJobCode());
			pstmt.setString(6, e.getSalLevel());
			pstmt.setInt(7, e.getSalary());
			pstmt.setDouble(8, e.getBonus());
			pstmt.setString(9, e.getManagerId());
			//empName, empNo, email, detpCode, jobCode, salLevel, Integer.parseInt(salary), Double.parseDouble(bonus), managerId
			
			result = pstmt.executeUpdate();
			
			if(result>0){
				conn.commit();
			}else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}	
		
		return result;
	}

	//SELECT 
	public ArrayList<Employee> selectList() {
		ArrayList<Employee> list = new ArrayList<Employee>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM COPY_EMP";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kh","kh");
			
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) { 
				
				list.add(new Employee(rset.getString("EMP_ID"), rset.getString("EMP_NAME"), rset.getString("EMP_NO")
									,rset.getString("EMAIL"), rset.getString("PHONE"), rset.getString("DEPT_CODE")
									, rset.getString("JOB_CODE"), rset.getString("SAL_LEVEL"), rset.getInt("SALARY")
									, rset.getDouble("BONUS"), rset.getString("MANAGER_ID"), rset.getDate("HIRE_DATE")
									, rset.getDate("ENT_DATE"), rset.getString("ENT_YN").charAt(0))); 
				
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
	//UPDATE : int 반환

	public int updateEmployee(Employee e) {
		
		int result =0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE COPY_EMP SET EMAIL = ?, PHONE = ?, SALARY = ? WHERE EMP_ID = ?";		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kh","kh");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, e.getEmail());
			pstmt.setString(2,  e.getPhone());
			pstmt.setInt(3,  e.getSalary());
			pstmt.setString(4,  e.getEmpId());
			
			result= pstmt.executeUpdate();
			if(result>0) {
				conn.commit();
			}else {
				conn.rollback();				
			}
			
			
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {				
				pstmt.close();
				conn.close();
			} catch (SQLException c) {
				c.printStackTrace();
			}
		}
		
		return result;
	}

		
	
	//DELETE : int 반환
	public int deleteEmployee(String empName) {
		int result =0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM COPY_EMP WHERE EMP_NAME = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kh","kh");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empName);
			result = pstmt.executeUpdate();
			
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
