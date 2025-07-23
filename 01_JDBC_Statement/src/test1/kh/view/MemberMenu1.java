package test1.kh.view;
//view : 사용자가 보게 될 시각적요소 입출력
//컨트롤러와 스캐너를 필드부에 전역으로 생성해둔다.

import java.util.ArrayList;
import java.util.Scanner;

import test1.kh.controller.MemberController1;
import test1.kh.model.vo.Member1;

public class MemberMenu1 {
	
	private MemberController1 mc1 = new MemberController1();
	private Scanner sc = new Scanner(System.in);
	
	
	public void mainMenu1() {
		
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
			case 1 : inputMember(); break; 
			case 2 : mc1.selectList();  break;
			case 3 : break;
			case 4 : break;
			case 5 : break;
			case 6 : break;
			case 0 : System.out.println("이용 감사합니다. 안녕히가세요");return;
			default :  System.out.println("잘못된 입력입니다. 다시 입력하세요");
			
			}	
			
			
		}
		
		
	}
	
	public void inputMember() {
		System.out.print("아이디 : ");
		String userId = sc.nextLine();
				
		System.out.print("비밀번호 : ");
		String userPwd = sc.nextLine();
		
		System.out.print("이름 : ");
		String userName = sc.nextLine();
		
		System.out.print("성별(M/F) : ");
		String gender = sc.nextLine(); 
		
		System.out.print("나이 : ");
		String age = sc.nextLine();
		
		System.out.print("이메일 : ");
		String email = sc.nextLine();
		
		System.out.print("전화번호 : ");
		String phone = sc.nextLine();

		System.out.print("주소 : ");
		String address = sc.nextLine();
		
		System.out.print("취미 : ");
		String hobby = sc.nextLine();
		//회원 추가요청 -> MemberController1 메소드 호출 
		mc1.insertMember(userId, userPwd, userName, gender, age, email, phone, address, hobby);
	}
	
	/** 
	 * 서비스 요청 처리후 성공했을경우 사용자가 보게 될 응답화면
	 * @param message
	 */
	public void displaySuccess(String message) {
		System.out.println("\n 서비스요청 성공 : "+ message);
		
	}
	
	/**서비스 요청 처리후 실패했을경우 사용자가 보게 될 응답화면
	 * @param message
	 */
	public void displayFail(String message) {
		System.out.println("\n 서비스요청 실패 : "+ message);
		
	}

	public void displayNoData(String message) {
		System.out.println("\n"+message);
		
	}

	public void displayMemberList(ArrayList<Member1> list) {
		System.out.println("\n 조회된결과는 다음과 같습니다.");
		
		for(Member1 a : list) {
			System.out.println(a);
		}
		
	}
	
	
}
