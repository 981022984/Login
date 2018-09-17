USE manager_score
GO
SELECT Student.stu_no,CTE4,CTE6,remakers
FROM Student,CTE46
WHERE Student.stu_no = CTE46.stu_no AND Student.stu_no = '1415243'