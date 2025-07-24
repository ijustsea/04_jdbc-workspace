package com.kh.employee.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.employee.controller.EmployeeController;
import com.kh.employee.model.vo.Employee;

public class EmployeeMenu {

	private Scanner sc = new Scanner(System.in);
	private EmployeeController ec = new EmployeeController();
	
	public void mainMenu() {
		
		while(true) {
			System.out.println("\n== 사원 관리 프로그램 ==");
			System.out.println("1.사원 추가");//INSERT
			System.out.println("2.사원 전체 조회");//SELECT *
			System.out.println("3.사원 수정");//UPDATE
			System.out.println("4.사원 삭제");//DELETE
			System.out.println("0.프로그램종료");//RETURN;
			
			System.out.print(">>메뉴 입력 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1: inputEmployee(); break;
			case 2: ec.selectList(); break;
			case 3: updateEmployee(); break;
			case 4: deleteEmployee(); break;
			case 0: System.out.println("이용 감사합니다. 프로그램을 종료합니다."); return;
			default : System.out.println("메뉴번호가 올바르지 않습니다. 다시 입력해주세요.");
			}
					
		}		
		
	}
	

	public void inputEmployee() {
		System.out.print("추가할 사원정보를 입력하세요\n");
		System.out.print("직원 명 : ");
		String empName = sc.nextLine();
		System.out.print("주민등록번호 : ");
		String empNo = sc.nextLine();
		System.out.print("이메일 : ");
		String email = sc.nextLine();
		System.out.print("부서코드 : ");
		String deptCode = sc.nextLine();
		System.out.print("직급코드 : ");
		String jobCode = sc.nextLine();
		System.out.print("급여등급 : ");
		String salLevel = sc.nextLine();
		System.out.print("급여 : ");
		String salary = sc.nextLine();
		System.out.print("보너스율 : ");
		String bonus = sc.nextLine();
		System.out.print("관리자사번 : ");
		String managerId = sc.nextLine();
		
		ec.insertEmployee(empName, empNo, email, deptCode, jobCode, salLevel, salary, bonus, managerId);
		
	}
	
	public void updateEmployee() {
		System.out.println("\n====사원정보변경====");	
		
		System.out.print("\n 변경할 사원 사번 입력해주세요 : ");
		String empId = sc.nextLine();	

		System.out.print("변경할 이메일 : ");
		String email = sc.nextLine();

		System.out.print("변경할 전번 : ");
		String phone = sc.nextLine();

		System.out.print("변경할 급여 : ");
		int salary = sc.nextInt();
		sc.nextLine();

		ec.updateEmployee(empId, email, phone, salary);
	}
	
	public void deleteEmployee() {
		System.out.println("\n====사원정보삭제====");
		System.out.print("\n 삭제할 사원의 이름을 입력해주세요 : ");
		String empName = sc.nextLine();	

		ec.deleteEmployee(empName);
	}


	public void displaySuccess(String message) {
		System.out.println("\n 서비스요청 성공 : " + message);
		
	}

	public void displayFail(String message) {
		System.out.println("\n 서비스요청 성공 : " + message);
	}

	public void displayNoDate(String message) {
		System.out.println("\n " + message);
	}

	public void displayMemberList(ArrayList<Employee> list) {
		System.out.println("\n 사원 전체를 보여드립니다.");
		
		for(Employee a : list) {
			System.out.println(a);
		}
		
	}

}
