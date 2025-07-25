package test1.kh.controller;

import java.util.ArrayList;

import com.kh.view.MemberMenu;

import test1.kh.model.dao.MemberDao1;
import test1.kh.model.vo.Member1;
import test1.kh.view.MemberMenu1;

/*
 * controller : view를 통해 사용자가 요청한 기능에 대해 처리하는 담당
 * 메인메뉴의 메소드로부터 전달된 데이터 가공처리한후 dao로 전달하면서 호출 		
 * dao로부터 반환 받은 결과에 따라 성공인지 실패인지 판단후 응답화면 결정 * 
 */


public class MemberController1 {

	public void insertMember(String userId, String userPwd, String userName, String gender, String age, String email, String phone, String address, String hobby) {
		//받은 데이터들을 dao한테 전달해줘야하는데 예쁘게 줘야한다.
		//데이터 주는방법은 1.기본생성자로 만들어서 setter로 일일이 담는법 2.매개변수생성자로 만들어서 생성과 동시에 담는방법
		
		Member1 m = new Member1(userId, userPwd, userName, gender, Integer.parseInt(age), email, phone, address, hobby);
		
		int result = new MemberDao1().insertMember(m);
		
		if(result >0) {
			new MemberMenu().displaySuccess("성공적으로 회원 추가 되었습니다.");
		}else {
			new MemberMenu().displayFail("회원 추가가 실패 되었습니다.");
		}
	}

	
	
	public void selectList() {
		//ArrayList 만들기 
		ArrayList<Member1> list = new MemberDao1().selectList();
		
		if(list.isEmpty()) {
			new MemberMenu1().displayNoData("조회결과가 없습니다.");
		}else {
			new MemberMenu1().displayMemberList(list);
		}
	}

}
