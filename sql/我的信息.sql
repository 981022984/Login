USE manager_score
GO
SELECT stu_no,stu_name,major_name,dept_name
FROM Student,Dept,Major
WHERE Student.dept_no = Dept.dept_no AND Student.major_no = Major.major_no
	  AND stu_no = '1415241'