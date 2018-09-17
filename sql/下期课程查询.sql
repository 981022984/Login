USE manager_score
GO
SELECT course_name,course_semeter
FROM Student,SC,Course
WHERE Student.stu_no = SC.stu_no AND SC.course_no = Course.course_no AND Student.stu_no = '1415243'
	  AND course_semeter 
	  IN(SELECT course_semeter FROM SC,Course WHERE SC.course_no = Course.course_no AND SC.sc_score = (-1))