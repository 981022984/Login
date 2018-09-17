USE manager_score
GO
--Ժϵ��Ϣ��
CREATE TABLE Dept
(
	dept_no CHAR(4) PRIMARY KEY,	   --Ժϵ���
	dept_name VARCHAR(20)			   --Ժϵ��
)
GO
--רҵ��Ϣ��
CREATE TABLE Major
(
	major_no CHAR(4) PRIMARY KEY,      --רҵ��
	major_name VARCHAR(20),			   --רҵ��
	dept_no CHAR(4),				   --����Ժϵ��
	FOREIGN KEY(dept_no)REFERENCES Dept(dept_no)
)
GO
--ѧ����Ϣ��
CREATE TABLE Student
(
	stu_no CHAR(8) PRIMARY KEY,        --ѧ��
	stu_password VARCHAR(16),          --����
	sru_name VARCHAR(20),              --����
	dept_no CHAR(4),                   --ѧԺ
	major_no CHAR(4),				   --רҵ
	FOREIGN KEY(dept_no) REFERENCES Dept(dept_no),
	FOREIGN KEY(major_no)REFERENCES Major(major_no)
)
GO
--�γ���Ϣ��
CREATE TABLE Course
(
	course_no CHAR(4),				   --�γ̱��
	major_no CHAR(4),		           --�ڿ�רҵ
	course_name VARCHAR(20),		   --�γ���
	course_semeter CHAR(4),            --�Ͽ�ѧ��
	PRIMARY KEY(course_no,major_no),   --������
	FOREIGN KEY (major_no) REFERENCES Major(major_no)
)
GO
--�ɼ���Ϣ��
CREATE TABLE SC
(
	stu_no CHAR(8),                    --ѧ��
	course_no CHAR(4),				   --�γ̱��
	major_no CHAR(4),		           --�ڿ�רҵ
	sc_score TINYINT,                  --�ɼ�
	sc_rescore TINYINT,                --���޳ɼ�
	PRIMARY KEY(stu_no,course_no,major_no),
	FOREIGN KEY (stu_no) REFERENCES Student(stu_no),
	FOREIGN KEY (course_no,major_no) REFERENCES Course(course_no,major_no)
)
GO
--�������ɼ�
CREATE TABLE CTE46
(
	stu_no CHAR(8) PRIMARY KEY,        --ѧ��
	CTE4 TINYINT,					   --�ļ��ɼ�
	CTE6 TINYINT,					   --�����ɼ�
	remakers VARCHAR(10),              --��ע
	FOREIGN KEY (stu_no) REFERENCES Student(stu_no)
)
