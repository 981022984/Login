USE manager_score
GO
SELECT course_name AS 科目,sc_score AS 成绩,
(SELECT AVG(S1.sc_score) 
 FROM SC S1,SC S2
 WHERE S1.course_no = S2.course_no AND S1.major_no = S2.major_no AND S1.major_no = ST1.major_no
	   AND S1.course_no = Course.course_no
 GROUP BY S1.course_no) AS 专业平均成绩,

(SELECT 排名
 FROM (SELECT DISTINCT S1.stu_no AS T_stu,course_name,S1.sc_score,DENSE_RANK() OVER(ORDER BY S1.sc_score DESC) AS 排名
       FROM SC S1,SC S2,Course C1
       WHERE S1.course_no = S2.course_no AND S1.major_no = S2.major_no AND C1.course_no = S1.course_no
	         AND S1.major_no = ST1.major_no AND C1.course_no = Course.course_no
      ) T,Student
 WHERE Student.stu_no = T.T_stu AND Student.stu_no = ST1.stu_no
) AS 排名

FROM Student ST1,SC,Course
WHERE ST1.stu_no = SC.stu_no AND SC.course_no = Course.course_no AND ST1.stu_no = '1415243' AND SC.sc_score != (-1)