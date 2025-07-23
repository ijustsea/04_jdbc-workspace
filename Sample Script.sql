--드랍은 맨위에 쓰는게 관행, DDL문( CREATE ALTER DROP )
DROP TABLE MEMEBER; 
DROP SEQUENCE SEQ_USER_NO;

CREATE TABLE MEMBER(
    USERNO NUMBER PRIMARY KEY,                  --회원번호
    USERID VARCHAR2(15) NOT NULL UNIQUE,        --회원아이디
    USERPWD VARCHAR2(15) NOT NULL,              --비밀번호
    USERNAME VARCHAR2(20) NOT NULL,             --회원이름
    GENDER CHAR(1) CHECK(GENDER IN ('M','F')),  --성별
    AGE NUMBER,                                 --나이
    EMAIL VARCHAR2(30),                         --이메일
    PHONE CHAR(11),                             --전화번호 -없이
    ADDRESS VARCHAR2(100),                      --주소
    HOBBY VARCHAR2(50),                         --취미
    ENROLLDATE DATE DEFAULT SYSDATE NOT NULL    --가입일
);

CREATE SEQUENCE SEQ_USER_NO
NOCACHE;

INSERT INTO MEMBER VALUES(SEQ_USER_NO.NEXTVAL,'admin', '1234', '관리자', 'M', 45, 'admin@kh.or.kr', '01011111111', '서울', NULL, '2025-07-01');

INSERT INTO MEMBER VALUES(SEQ_USER_NO.NEXTVAL,'user01', 'pass01', '변우석', NULL, 25, 'user01@kh.or.kr', '01055551111', '부산', '등산,영화보기', '2025-07-03');

SELECT * FROM MEMBER;
COMMIT;

CREATE TABLE TEST(
    TNO NUMBER,
    TNAME VARCHAR2(20),
    TDATE DATE    
);

SELECT * FROM TEST;

