package com.kh.model.dao;

import java.beans.Statement;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*;
import com.kh.model.vo.Product;
import com.kh.model.vo.ProductIo;

import oracle.net.aso.r;

public class ProductDao {
	
	private Properties prop = new Properties();
	
	public ProductDao (){//
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public ArrayList<Product> selectList(Connection conn) {
		
		ArrayList<Product> list = new ArrayList<Product>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Product(rset.getString("PRODUCT_ID"), rset.getString("P_NAME")
									,rset.getInt("PRICE"), rset.getString("DESCRIPTION"),rset.getInt("STOCK")	));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}


	public int insertProduct(Connection conn, Product p) {
		int result = 0; 
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertProduct");
		
		try {
			pstmt= conn.prepareStatement(sql);
						
			pstmt.setString(1, p.getProductId());
			pstmt.setString(2, p.getpName());
			pstmt.setInt(3, p.getPrice());
			pstmt.setString(4, p.getDescription());
			pstmt.setInt(5, p.getStock());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			close(pstmt);
		}		
		
		return result;
	}


	public int updateProduct(Connection conn, Product p) {
		int result = 0; 
		PreparedStatement pstmt = null;
				
		String sql = prop.getProperty("updateProduct"); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, p.getpName());
			pstmt.setInt(2, p.getPrice());
			pstmt.setString(3, p.getDescription());
			pstmt.setInt(4, p.getStock());
			pstmt.setString(5, p.getProductId());
			
			result = pstmt.executeUpdate(); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
				
		return result;
	}


	public int deleteProduct(Connection conn, String productId) {
		int result = 0; 
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteProduct"); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productId);
			result= pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}	
		
		return result;
	}


	public ArrayList<Product> selectByProductName(Connection conn, String keyword) {
		ArrayList<Product> list = new ArrayList<Product>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectByProductName");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new Product(rset.getString("PRODUCT_ID"), rset.getString("P_NAME")
									,rset.getInt("PRICE"), rset.getString("DESCRIPTION"),rset.getInt("STOCK")	));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return list;
		
	
	}

	public ArrayList<ProductIo> selectIoList(Connection conn) {
		ArrayList<ProductIo> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectIoList");
		

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new ProductIo(rset.getInt("IO_NUM"), rset.getString("PRODUCT_ID"), rset.getString("P_NAME")
									,rset.getDate("IO_DATE"), rset.getInt("AMOUNT"), rset.getString("STATUS")	));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}


	public ArrayList<ProductIo> selectICList(Connection conn) {
		ArrayList<ProductIo> list = new ArrayList<>();
			
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectICList");
		

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new ProductIo(rset.getInt("IO_NUM"), rset.getString("PRODUCT_ID"), rset.getString("P_NAME")
									,rset.getDate("IO_DATE"), rset.getInt("AMOUNT"), rset.getString("STATUS")	));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}


	public ArrayList<ProductIo> selectOGList(Connection conn) {
		ArrayList<ProductIo> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectOGList");
		

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new ProductIo(rset.getInt("IO_NUM"), rset.getString("PRODUCT_ID"), rset.getString("P_NAME")
									,rset.getDate("IO_DATE"), rset.getInt("AMOUNT"), rset.getString("STATUS")	));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}


	public int updateIC(Connection conn, String productId, int amount) {
		int result = 0; 
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateIC"); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, productId);	
			pstmt.setInt(2, amount);
						
			
			result = pstmt.executeUpdate(); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
				
		return result;
	}


	public int updateOG(Connection conn, String productId, int amount) {
		int result = 0; 
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		String sql = prop.getProperty("updateOG"); 
		ResultSet rset = null;
		String sql1 = prop.getProperty("selectList");
		
		try {
			pstmt1= conn.prepareStatement(sql1);
			rset = pstmt1.executeQuery();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}	
		
		int currentStock = 0;

	    try {
			if (rset.next()) {
			      currentStock = rset.getInt("STOCK");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    
	    if (amount > currentStock) {
            System.out.println("❗ 출고량이 현재 재고보다 많습니다. 출고를 진행할 수 없습니다.");
            return 0;
        }
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, productId);	
			pstmt.setInt(2, amount);
						
			
			result = pstmt.executeUpdate(); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
				
		return result;
	}

}
