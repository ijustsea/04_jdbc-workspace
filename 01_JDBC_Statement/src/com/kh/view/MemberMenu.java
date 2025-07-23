package com.kh.view;
//View : 사용자가 보게될 시각적요소 입출력

import java.util.Scanner;

public class MemberMenu {
	
	//필드 생성
	
	//전역으로 Scanner생성  전역객체는 보통 접근제한자 작성한다.
	private Scanner sc = new Scanner(System.in);
			
	/**
	 * 사용자게 보게 될 첫 화면(메인화면)
	 */
	public void mainMenu() {
		
		
		
		while(true) {
			
			System.out.println("\n== 회원 관리 프로그램 ==");
			System.out.println("1.회원추가");
			System.out.println("2.회원 전체 조회");
			System.out.println("3.회원 아이디 검색");
			System.out.println("4.회원 이름 키워드 검색");
			System.out.println("5.회원 정보 변경"); //Update
			System.out.println("6.회원 탈퇴");//Delete
			System.out.println("0.프로그램 종료");
			
			System.out.print(">> 메뉴 선택 : ");
			int menu =sc.nextInt();
			sc.nextLine();//버퍼에 남은 공백제거
			
			switch(menu) {
			case 1: break;
			case 2: break;
			case 3: break;
			case 4: break;
			case 5: break;
			case 6: break;
			case 0: System.out.println("이용 감사합니다."); return;
			default : System.out.println("올바르지않은 메뉴번호, 다시입력바랍니다."); 
			}			
			
		}
				
		
		
	}
	
	
	/**
	 * 아이디부터 취미까지 입력받기
	 */
	public void inputMember() {
		System.out.print("아이디 : ");
		String userId = sc.nextLine();
				
		System.out.print("비밀번호 : ");
		String userPwd = sc.nextLine();
		System.out.print("이름 : ");
		String userName = sc.nextLine();
		
		System.out.print();
		System.out.print();
		System.out.print();
		System.out.print();
		System.out.print();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
