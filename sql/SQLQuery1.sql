USE manager_score
GO
--院系信息表
CREATE TABLE Dept
(
	dept_no CHAR(4) PRIMARY KEY,	   --院系编号
	dept_name VARCHAR(20)			   --院系名
)
GO
--专业信息表
CREATE TABLE Major
(
	major_no CHAR(4) PRIMARY KEY,      --专业号
	major_name VARCHAR(20),			   --专业名
	dept_no CHAR(4),				   --所属院系号
	FOREIGN KEY(dept_no)REFERENCES Dept(dept_no)
)
GO
--学生信息表
CREATE TABLE Student
(
	stu_no CHAR(8) PRIMARY KEY,        --学号
	stu_password VARCHAR(16),          --密码
	sru_name VARCHAR(20),              --姓名
	dept_no CHAR(4),                   --学院
	major_no CHAR(4),				   --专业
	FOREIGN KEY(dept_no) REFERENCES Dept(dept_no),
	FOREIGN KEY(major_no)REFERENCES Major(major_no)
)
GO
--课程信息表
CREATE TABLE Course
(
	course_no CHAR(4),				   --课程编号
	major_no CHAR(4),		           --授课专业
	course_name VARCHAR(20),		   --课程名
	course_semeter CHAR(4),            --上课学期
	PRIMARY KEY(course_no,major_no),   --表级主键
	FOREIGN KEY (major_no) REFERENCES Major(major_no)
)
GO
--成绩信息表
CREATE TABLE SC
(
	stu_no CHAR(8),                    --学号
	course_no CHAR(4),				   --课程编号
	major_no CHAR(4),		           --授课专业
	sc_score TINYINT,                  --成绩
	sc_rescore TINYINT,                --重修成绩
	PRIMARY KEY(stu_no,course_no,major_no),
	FOREIGN KEY (stu_no) REFERENCES Student(stu_no),
	FOREIGN KEY (course_no,major_no) REFERENCES Course(course_no,major_no)
)
GO
--四六级成绩
CREATE TABLE CTE46
(
	stu_no CHAR(8) PRIMARY KEY,        --学号
	CTE4 TINYINT,					   --四级成绩
	CTE6 TINYINT,					   --六级成绩
	remakers VARCHAR(10),              --备注
	FOREIGN KEY (stu_no) REFERENCES Student(stu_no)
)
