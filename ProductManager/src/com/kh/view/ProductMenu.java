package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.ProductController;
import com.kh.model.vo.Product;

public class ProductMenu {

	private Scanner sc = new Scanner(System.in);
	ProductController pc = new ProductController();
	
	public void mainMenu() {
		
		while(true) {
			System.out.println("\n===상품 관리 프로그램===");
			System.out.println("1.전체 조회 하기");//SELECT * 
			System.out.println("2.상품 추가 하기");//INSERT
			System.out.println("3.상품 수정 하기");//UPDATE
			System.out.println("4.상품 삭제 하기");//DELETE
			System.out.println("5.상품 검색 하기");//selectByProductName
			System.out.println("0.프로그램 종료하기");//return
		
			System.out.print("\n메뉴 입력 : ");
			int menu = sc.nextInt();
			sc.nextLine();
		
			switch(menu) {
			case 1 : pc.selectList(); break;
			case 2 : inputProduct(); break;
			case 3 : updateProduct(); break;
			case 4 : deleteProduct(); break;
			case 5 : pc.selectByProductName(inputProductName()); break;
			case 0 : System.out.println("이용 감사합니다. 프로그램을 종료합니다."); return;
			default : System.out.println("잘못된 입력입니다. 메뉴번호를 다시 입력하세요.");
		
			}
			
		}	
		
	}
	
	public String inputProductName() {
		System.out.println("\n===상품 검색 하기===");
		System.out.print("검색할 상품 이름 입력 : ");
		
		return sc.nextLine();
	}
	

	public void inputProduct() {
		System.out.println("\n===상품 추가 정보 입력===");
		System.out.print("상품 id 입력 : ");
		String productId = sc.nextLine();
		System.out.print("상품 이름 입력 : ");
		String pName = sc.nextLine();
		System.out.print("상품 가격 입력 : ");
		String price = sc.nextLine();		
		System.out.print("상품 상세 정보 입력 : ");
		String description = sc.nextLine();
		System.out.print("재고 수량 입력 : ");
		String stock = sc.nextLine();
		
		
		pc.insertProduct(productId, pName, price, description, stock);
		
	}

	public void updateProduct() {
		System.out.println("\n===상품 수정 정보 입력===");
		System.out.print("수정할 상품 id 선택 : ");
		String productId = sc.nextLine();
		System.out.print("수정 이름 입력 : ");
		String pName = sc.nextLine();
		System.out.print("수정 가격 입력 : ");
		String price = sc.nextLine();		
		System.out.print("수정 상세 정보 입력 : ");
		String description = sc.nextLine();
		System.out.print("수정 재고 수량 입력 : ");
		String stock = sc.nextLine();
		
		pc.updateProduct(productId, pName, price, description, stock);
		
	}
	
	public void deleteProduct() {
		System.out.println("\n===상품 삭제 정보 입력===");
		System.out.print("삭제할 상품 id 선택 : ");
		String productId = sc.nextLine();
		
		pc.deleteProduct(productId);
	}
	
	public void displayNoData(String message) {
		System.out.println("\n"+message);		
	}

	public void displayProductList(ArrayList<Product> list) {
		System.out.println("\n조회된 상품들은 다음과 같습니다. ");
		
		for(Product a : list) {
			System.out.println(a);
		}
	}

	public void displaySuccess(String message) {
		System.out.println("\n 서비스요청 성공 : " + message);
	}

	public void displayFail(String message) {
		System.out.println("\n 서비스요청 실패 : " + message);
	}

}
