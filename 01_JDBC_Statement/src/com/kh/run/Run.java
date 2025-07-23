package com.kh.run;

import com.kh.view.MemberMenu;

public class Run {

	public static void main(String[] args) {
		/*
		 * MVC(model view controller) 패턴
		 * Model : 데이터 처리 담당 (데이터를 담기위한 클래스(VO)v), 데이터저장공간(DATABASE)에 직접적으로 접근해서 데이터 주고받기(DAO)) 
		 * View  : 화면을 담당 ( 지금은 콘솔! 사용가 보게되는 시가적요소, 출력 및 입력만 )
		 * Controller : 사용자의 요청을 처리해주는 담당 (사용자의 요청을 처리한후 그에 해당하는 응답화면 지정)
		 */
		
		//Run : 단지 프로그램 실행만 담당(사용자가 보게될 첫 화면만 띄워주고 끝)
		
		new MemberMenu().mainMenu();		
		
	}

}
