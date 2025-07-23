package test;

import java.sql.Connection;
import java.sql.Statement;

public class TestRun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * JDBC객체
		 * -Connection : DB의 연결정보를 담고있는 객체
		 * -[Prepared]Statement : 연결된 DB에 SQL문 전달해서 실행하고 그 결과를 받아내는 객체 <가장중요>
		 * -ResultSet : SELECT문 실행후 조회된 결과물들이 담겨있는		 
		 * 
		 * JDBC과정
		 * 1. jdbc driver 등록 : 해당 DBMS(오라클)가 제공하는 클래스등록 
		 * 2. Connection 생성 : 연결하고자 하는 DB정보를 입력해서 해당 DB와 연결되면서 생성된다. 
		 * 3. Statement 생성 : Connection 객체를 이용해서 생성 (sql문 실행및 결과받는 객체) 
		 * 4. sql문 전달하면서 실행 : Statement 객체 이용해서 sql문 실행
		 * 5. 결과 받기 
		 * 		> SELECT문 실행 => ResultSet 객체(조회된 데이터) ~ 셀렉문을 리절트세트가 필요하고 
		 * 		--DML (INSERT UPDATE DELETE)
		 * 		>    DML문 실행 => int(처리된 행 수) ~ DML문을 실행하면 인트변수가 필요하다. 
		 *
		 * 6-1.셀렉문을 리절트세트가 필요
		 *-ResultSet에 담겨있는 데이터들을 하나씩 하나씩 꺼내서 vo 객체에 담아야한 다.
		 * [여러행 조회시 ArrayList에 담기]
		 *
		 * 6-2.DML문을 실행하면 인트변수 필요
		 *-트랜젝션 처리 (성공적으로 수행했으면 commit; , 실패했으면 rollback;)
		 *
		 * 7.다 사용한 JDBC 객체를 반드시 자원반납!~ 디비가 락걸리는 에러 발생 꼭 반납 (close)
		 *-생성된 역순으로 반납해야한다. 역순이 핵심!		 *
		 */
		
		/*
		 * 1.각자 pc(localhost)에 JDBC계정에 연결한후 TEST테이블에 INSERT 해보기
		 * 
		 */
		
		//INSERT문 -> 1.2.3.4.5-DML-INT.6-2.7
		//필요한 변수먼저 세팅 ~ INSERT문은 처리된 행수(int) > 0이면 커밋, =0이면 롤백
		
		int result =0; //처리된 행수를 받아줄 변수
		
		Connection conn = null;//DB 연결정보 보관할 객체
		Statement stmt =null;//sql문 전달해서 실행후 결과받는 객체
		//ResultSet은 셀렉절에서만 필요하기 때문에 지금은 패스
		
		//앞으로 실행할 sql문 작성 ("완성형태")=> 쿼리문에 세미콜론 없어야함 주요에러 why 알아서 세미콜론 붙여주기때문에 
		String sql ="INSERT INTO TEST VALUES(1, '차은우', SYSDATE)";
		
		//DB에서도 접속정보 입력하는것처럼, 자바에서 접속정보 입력해야함.HOW?
		//1)자바랑 오라클 이어주는 jdbc driver 등록 ~ DBMS(오라클)가 제공하는 클래스등록??
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("jdbc driver 등록 성공!");
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
		
	}

}
