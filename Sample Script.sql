--����� ������ ���°� ����, DDL��( CREATE ALTER DROP )
DROP TABLE MEMEBER; 
DROP SEQUENCE SEQ_USER_NO;

CREATE TABLE MEMBER(
    USERNO NUMBER PRIMARY KEY,                  --ȸ����ȣ
    USERID VARCHAR2(15) NOT NULL UNIQUE,        --ȸ�����̵�
    USERPWD VARCHAR2(15) NOT NULL,              --��й�ȣ
    USERNAME VARCHAR2(20) NOT NULL,             --ȸ���̸�
    GENDER CHAR(1) CHECK(GENDER IN ('M','F')),  --����
    AGE NUMBER,                                 --����
    EMAIL VARCHAR2(30),                         --�̸���
    PHONE CHAR(11),                             --��ȭ��ȣ -����
    ADDRESS VARCHAR2(100),                      --�ּ�
    HOBBY VARCHAR2(50),                         --���
    ENROLLDATE DATE DEFAULT SYSDATE NOT NULL    --������
);

CREATE SEQUENCE SEQ_USER_NO
NOCACHE;

INSERT INTO MEMBER VALUES(SEQ_USER_NO.NEXTVAL,'admin', '1234', '������', 'M', 45, 'admin@kh.or.kr', '01011111111', '����', NULL, '2025-07-01');

INSERT INTO MEMBER VALUES(SEQ_USER_NO.NEXTVAL,'user01', 'pass01', '���켮', NULL, 25, 'user01@kh.or.kr', '01055551111', '�λ�', '���,��ȭ����', '2025-07-03');

SELECT * FROM MEMBER;
COMMIT;

CREATE TABLE TEST(
    TNO NUMBER,
    TNAME VARCHAR2(20),
    TDATE DATE    
);

SELECT * FROM TEST;

