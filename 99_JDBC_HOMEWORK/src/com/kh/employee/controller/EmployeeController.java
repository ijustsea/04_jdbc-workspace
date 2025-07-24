package com.kh.employee.controller;

import java.util.ArrayList;

import com.kh.employee.model.dao.EmployeeDao;
import com.kh.employee.model.vo.Employee;
import com.kh.employee.view.EmployeeMenu;



public class EmployeeController {

	public void insertEmployee(String empName, String empNo, String email, String deptCode, String jobCode,
			String salLevel, String salary, String bonus, String managerId) {
	
	//Employee e 객체에 담기
	Employee e = new Employee(empName, empNo, email, deptCode, jobCode, salLevel, Integer.parseInt(salary), Double.parseDouble(bonus), managerId);
	
	//Employee dao에 전달
	int result = new EmployeeDao().insertEmployee(e);
	
	if (result > 0) {
		new EmployeeMenu().displaySuccess("성공적으로 사원추가 되었습니다.");
	} else {
		new EmployeeMenu().displayFail("사원추가가 실패하였습니다.");
	}
	
	}

	public void selectList() {
		ArrayList<Employee> list =new EmployeeDao().selectList();
		
		if (list.isEmpty()) {// 조회데이터 없는경우
			new EmployeeMenu().displayNoDate("전체조회결과가 없습니다.");
		} else {
			new EmployeeMenu().displayMemberList(list);
		}
	}

	public void updateEmployee(String empId, String email, String phone, int salary) {
		//매개변수 3개이상이면 객체로 담아서 주기 
		Employee e =new Employee();
		e.setEmpId(empId);
		e.setEmail(email);
		e.setPhone(phone);
		e.setSalary(salary);
	
		int result = new EmployeeDao().updateEmployee(e);
		
		if (result > 0) {
			new EmployeeMenu().displaySuccess("성공적으로 사원정보가 변경되었습니다.");
		} else {
			new EmployeeMenu().displayFail("사원정보 변경이 실패하였습니다.");
		}
	}

	public void deleteEmployee(String empName) {
		
		int result = new EmployeeDao().deleteEmployee(empName);
		if (result > 0) {
			new EmployeeMenu().displaySuccess("성공적으로 사원이 삭제 되었습니다.");
		} else {
			new EmployeeMenu().displayFail("삭제할 사원이 존재하지 않습니다.");
		}
		
	}
}
