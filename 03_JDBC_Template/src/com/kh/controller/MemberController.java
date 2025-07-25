package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.service.MemberService;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

public class MemberController {

	// Controller : view를 통해서 사용자가 요청한 기능에 대해 처리하는 기능 담당
	// 메소드로부터 전달된 데이터 가공처리한후 dao로 전달하면서 호출
	// dao로부터 반환 받은 결과에 따라 성공인지 실패인지 판단후 응답화면 결정
	/**
	 * 사용자의 회원추가요청을 처리해주는 메소드
	 * 
	 * @param userId ~ hobby : 사용자가 입력햇던 정보들이 담겨있는 매개변수 해당 메소드로부터 전달된 데이터를 가공처리한 후
	 *               dao로 전달하면서 호출~>가방에 넣는 방식 ㄱ
	 */
	public void insertMember(String userId, String userPwd, String userName, String gender, String age, String email,
			String phone, String address, String hobby) {
		
		Member m = new Member(userId, userPwd, userName, gender, Integer.parseInt(age), email, phone, address, hobby);

		int result = new MemberService().insertMember(m);
		
		if (result > 0) {
			new MemberMenu().displaySuccess("성공적으로 회원추가 되었습니다.");
		} else {
			new MemberMenu().displayFail("회원추가가 실패하였습니다.");
		}

	}

	/**
	 * 사용자의 회원 전체 조회요청을 처리해주는 메소드
	 */
	public void selectList() {
		ArrayList<Member> list = new MemberService().selectList();
		// 조회결과가 있는 없는지 판단후 사용자가 보게 될 응답화면 지정.

		if (list.isEmpty()) {// 조회데이터 없는경우
			new MemberMenu().displayNoDate("전체조회결과가 없습니다.");
		} else {
			new MemberMenu().displayMemberList(list);
		}
	}

	public void selectByUserId(String userId) {

		Member m = new MemberService().selectByUserId(userId);

		if (m == null) { // 검색결과없음요
			new MemberMenu().displayNoDate(userId + "에 해당하는 검색결과 없습니다.");
		} else {
			new MemberMenu().displayMember(m);
		}
	}

	public void selectByUserName(String keyword) {
		ArrayList<Member> list = new MemberService().selectByUserName(keyword);

		if (list.isEmpty()) {
			new MemberMenu().displayNoDate(keyword + "에 해당하는 검색결과 없습니다.");
		} else {
			new MemberMenu().displayMemberList(list);
		}

	}

	public void updateMember(String userId, String userPwd, String email, String phone, String address) {

		Member m = new Member();

		m.setUserId(userId);
		m.setUserPwd(userPwd);
		m.setEmail(email);
		m.setPhone(phone);
		m.setAddress(address);

		int result = new MemberService().updateMember(m);

		if (result > 0) {
			new MemberMenu().displaySuccess("성공적으로 회원정보가 변경되었습니다.");
		} else {
			new MemberMenu().displayFail("회원정보 변경이 실패하였습니다.");
		}

	}

	public void deleteMember(String userId) {
		int result = new MemberService().deleteMember(userId);
		if (result > 0) {
			new MemberMenu().displaySuccess("성공적으로 회원정보가 삭제되었습니다.");
		} else {
			new MemberMenu().displayFail("삭제할 회원정보가 존재하지 않습니다.");
		}
	}

}
