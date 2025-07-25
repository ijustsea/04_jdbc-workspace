package com.kh.model.vo;

import java.sql.Date;

public class ProductIo {
	
	private int ioNum;
	private String productId;
	private String pName;
	private Date ioDate;
	private int amount;
	private String status;
	
	public ProductIo() {}

	public ProductIo(int ioNum, String productId, String pName, Date ioDate, int amount, String status) {
		super();
		this.ioNum = ioNum;
		this.productId = productId;
		this.pName = pName;
		this.ioDate = ioDate;
		this.amount = amount;
		this.status = status;
	}

	public int getIoNum() {
		return ioNum;
	}

	public void setIoNum(int ioNum) {
		this.ioNum = ioNum;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public Date getIoDate() {
		return ioDate;
	}

	public void setIoDate(Date ioDate) {
		this.ioDate = ioDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return  String.format("%-10s %-12s %-20s %-15s %-12s %-10s",
				ioNum, productId,  pName, ioDate ,  amount , status);
	}
	
	public static String getHeader() {
	    return String.format("%-10s %-12s %-20s %-15s %-12s %-10s",
	            "입출고번호", "상품ID", "상품명", "입출고일", "입출고수량", "입출고상태");
	}
	
}
