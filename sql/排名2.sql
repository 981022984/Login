USE manager_score
GO
--获取相同专业,相同课程,某一个学生的排名
SELECT T_stu,排名
FROM (SELECT DISTINCT S1.stu_no AS T_stu,course_name,S1.sc_score,DENSE_RANK() OVER(ORDER BY S1.sc_score DESC) AS 排名
      FROM SC S1,SC S2,Course
      WHERE S1.course_no = S2.course_no AND S1.major_no = S2.major_no AND Course.course_no = S1.course_no
	        AND S1.major_no = '156' AND Course.course_no = '1001'
      ) T,Student
WHERE Student.stu_no = T.T_stu AND Student.stu_no = '1415241'
