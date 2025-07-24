package com.kh.view;
//View : 사용자가 보게될 시각적요소 입출력

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.MemberController;
import com.kh.model.vo.Member;

public class MemberMenu {

	// 필드 생성

	// 전역으로 Scanner생성 전역객체는 보통 접근제한자 작성한다.
	private Scanner sc = new Scanner(System.in);
	// MemberController 객체생성
	private MemberController mc = new MemberController();

	/**
	 * 사용자게 보게 될 첫 화면(메인화면)
	 */
	public void mainMenu() {

		while (true) {

			System.out.println("\n== 회원 관리 프로그램 ==");
			System.out.println("1.회원추가");
			System.out.println("2.회원 전체 조회");
			System.out.println("3.회원 아이디 검색");
			System.out.println("4.회원 이름 키워드 검색");
			System.out.println("5.회원 정보 변경"); // Update
			System.out.println("6.회원 탈퇴");// Delete
			System.out.println("0.프로그램 종료");

			System.out.print(">> 메뉴 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();// 버퍼에 남은 공백제거

			switch (menu) {
			case 1:
				inputMember();
				break;
			case 2:
				mc.selectList();
				break;
			case 3:
				//String userId = inputMemberId();
				//mc.selectByUserId(userId);
				mc.selectByUserId(inputMemberId());
				break;
			case 4:
				//String keyword = inputMemberName();
				//mc.selectByUserName(keyword);
				mc.selectByUserName(inputMemberName());
				break;
			case 5:
				updateMember();
				break;
			case 6:
				deleteMember();
				break;
			case 0:
				System.out.println("이용 감사합니다.");
				return;
			default:
				System.out.println("올바르지않은 메뉴번호, 다시입력바랍니다.");
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

		// 회원 추가 요청 == Contoller 메소드 호출
		mc.insertMember(userId, userPwd, userName, gender, age, email, phone, address, hobby);

	}

	/**
	 * 사용자에게 회원아이디 입력받은후 그떄입력된값을 반환시켜주는 메소드
	 * 
	 * @return : 사용자가 입력한 아이디값 String
	 */
	public String inputMemberId() {
		System.out.print("\n회원 아이디 입력 : ");
		return sc.nextLine();
	}

	public String inputMemberName() {
		System.out.print("\n회원 이름(키워드) 입력 : ");
		return sc.nextLine();
	}

	/**
	 * 사용자에게 변경할정보들(비번, 이메일, 전번 주소)과 회원의 아이디를 입력받는 화면 변경할 아이디 = userId userPwd,
	 * email, phone , address = 변경할 정보들.
	 */
	public void updateMember() {
		System.out.println("\n====회원정보변경====");
		// 아이디 => 비번, 이메일, 전번, 주소

		// System.out.print("아이디 : ");
		// String userId= sc.nextLine();
		// 재사용성 높히기 위해 앞서 만든 메소드 활용예정

		String userId = inputMemberId();
		System.out.print("변경할 비번 : ");
		String userPwd = sc.nextLine();

		System.out.print("변경할 이메일 : ");
		String email = sc.nextLine();

		System.out.print("변경할 전번 : ");
		String phone = sc.nextLine();

		System.out.print("변경할 주소 : ");
		String address = sc.nextLine();

		mc.updateMember(userId, userPwd, email, phone, address);
	}

	public void deleteMember() {
		System.out.println("\n====회원정보삭제====");
		String userId = inputMemberId();

		mc.deleteMember(userId);

	}

	// ---------------------------------------응답화면------------------------------------

	/**
	 * 서비스 요청 처리후 성공했을경우 사용자가 보게 될 응답화면
	 * 
	 * @param message
	 */
	public void displaySuccess(String message) {
		System.out.println("\n 서비스요청 성공 : " + message);

	}

	/**
	 * 서비스 요청 처리후 실패했을경우 사용자가 보게 될 응답화면
	 * 
	 * @param message
	 */
	public void displayFail(String message) {
		System.out.println("\n 서비스요청 실패 : " + message);

	}

	/**
	 * 서비스 조회서비스 요청시 조회결과가 없을 경우 사용자에게 출력할 응답화면
	 * 
	 * @param message
	 */
	public void displayNoDate(String message) {
		System.out.println("\n " + message);

	}

	/**
	 * 서비스 조회서비스 요청시 조회결과가 있을 경우 사용자에게 출력할 응답화면
	 * 
	 * @param message
	 */
	public void displayMemberList(ArrayList<Member> list) {
		System.out.println("\n조회된 데이터는 다음과 같습니다. ");

		for (Member a : list) {// a= list.get(0) -> a = list.get(1) ..... 있을떄까지
			System.out.println(a);
		}

	}

	/**
	 * 죄회갈겨과 한행일떄 사용자가 보게될 응답화면
	 * 
	 * @param m
	 */
	public void displayMember(Member m) {
		System.out.println("\n 조회딘 데이터는 다음과 같습니다.");
		System.out.println(m);
	}

}
