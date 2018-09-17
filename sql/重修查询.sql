USE manager_score
GO
SELECT course_name 课程名称,sc_score AS 考试成绩,
(SELECT course_semeter+1
 FROM SC,Course C,Student ST
 WHERE ST.stu_no = SC.stu_no AND SC.sc_score < 60 AND SC.course_no = C.course_no
	  AND Student.stu_no = ST.stu_no AND C.course_no = Course.course_no) AS 重修学期
,sc_rescore AS 重修成绩
FROM Student,SC,Course
WHERE Student.stu_no = SC.stu_no AND SC.sc_score < 60 AND SC.course_no = Course.course_no
	  AND SC.sc_score != (-1) AND Student.stu_no = '1415243'