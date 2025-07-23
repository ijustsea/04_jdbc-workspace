package com.kh.model.vo;

import java.sql.Date;

public class Member {
	
	/*  USERNO
		USERID
		USERPWD
		USERNAME
		GENDER
		AGE
		EMAIL
		PHONE
		ADDRESS
		HOBBY
		ENROLLDATE 
	 */
	//필드는 기본적으로 DB컬럼명과 유사하게 작업 + 낙타표기법
	
	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String gender;
	private int age;
	private String email;
	private String address;
	private String hobby;
	private Date enrollDate;	
	
	public Member() {}

	public Member(int userNo, String userId, String userPwd, String userName, String gender, int age, String email,
			String address, String hobby, Date enrollDate) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.address = address;
		this.hobby = hobby;
		this.enrollDate = enrollDate;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return "Member [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", gender=" + gender + ", age=" + age + ", email=" + email + ", address=" + address + ", hobby="
				+ hobby + ", enrollDate=" + enrollDate + "]";
	}
	
	
	
}
