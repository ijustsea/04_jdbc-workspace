package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class TestRun {

	public static void main(String[] args) {
		//Properties 복습
		/*
		 * Properties 특징 - Map계열 컬랙션 (key-value 세트로 담는특징)
		 * key.value 모두 String(문자열)으로 담기
		 * setProperty(String key, String value)
		 * getProperty(String key) : String value 반환
		 * 
		 * 주로 외부파일(.properties, .xml)로 입출력할때 사용함.=> 환경설정 파일같은거
		 *  
		 */
		/*
		Properties prop = new Properties();//순서유지안함.
		
		prop.setProperty("C", "INSERT");
		prop.setProperty("R", "SELECT");
		prop.setProperty("U", "UPDATE");
		prop.setProperty("D", "DELETE");
		
		try {
			//prop.store(new FileOutputStream("resources/test.properties"), "properties Test");
				prop.storeToXML(new FileOutputStream("resources/text.xml"), "쵀재홍 바보");	
		} catch (IOException e) {
			e.printStackTrace();
		}
		 */
		
		Properties prop = new Properties();//[]
		
		try {
			prop.load(new FileInputStream("resources/driver.properties"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(prop.getProperty("driver"));
		System.out.println(prop.getProperty("url"));
		System.out.println(prop.getProperty("username"));
		System.out.println(prop.getProperty("password"));
		System.out.println(prop.getProperty("password1"));//존재하지않는 키값제시하면 null
	}
}
