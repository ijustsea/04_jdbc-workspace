package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

public class MemberController {

	//Controller : view를 통해서 사용자가 요청한 기능에 대해 처리하는 기능 담당
	//				메소드로부터 전달된 데이터 가공처리한후 dao로 전달하면서 호출 		
	//              dao로부터 반환 받은 결과에 따라 성공인지 실패인지 판단후 응답화면 결정
	/**
	 * 사용자의 회원추가요청을 처리해주는 메소드
	 * @param userId ~ hobby : 사용자가 입력햇던 정보들이 담겨있는 매개변수
	 * 해당 메소드로부터 전달된 데이터를 가공처리한 후 dao로 전달하면서 호출~>가방에 넣는 방식 ㄱ 
	 */  			
	public void insertMember(String userId, String userPwd, String userName, String gender, String age, String email,
			String phone, String address, String hobby) {
		//받은 데이터를 dao한테 줄건데 가방에 담아서 주자, 예쁘게 가공하는법	
		//Member 객체에 담아서 주자. 두가지 방법잇음
		
		//1.기본생성자로 생성한후 각 필드에 값을 setter 메소드를 통해 일일히 담는법( 매개변수가 적을때 )
		
		//2.아싸리 매개변수 생성자를 통해서 생성과 동시에 담는 방법 (매개변수 많을 때 효율적)
		Member m =new Member(userId, userPwd, userName, gender, Integer.parseInt(age), email, phone, address, hobby );
		
		//System.out.println(m);//toString 생략.!
		
		int result =new MemberDao().insertMember(m);
		
		if(result >0) {
			new MemberMenu().displaySuccess("성공적으로 회원 추가 되었습니다.");
		}else {
			new MemberMenu().displayFail("성공적으로 회원 실패 되었습니다.");
		}
		
	}

	/**
	 * 사용자의 회원 전체 조회요청을 처리해주는 메소드
	 */
	public void selectList() {
		ArrayList<Member> list = new MemberDao().selectList();
		//조회결과가 있는 없는지 판단후 사용자가 보게 될 응답화면 지정.
		
		if(list.isEmpty()) {//조회데이터 없는경우
			new MemberMenu().displayNoDate("전체조회결과가 없습니다.");
		}else {
			new MemberMenu().displayMemberList(list);			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
