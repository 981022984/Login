USE manager_score
GO
--��ȡ��ͬרҵ��ͬ�γ̵ĳɼ�����
SELECT DISTINCT S1.stu_no,course_name,S1.sc_score,DENSE_RANK() OVER(ORDER BY S1.sc_score DESC) AS ����
FROM SC S1,SC S2,Course
WHERE S1.course_no = S2.course_no AND S1.major_no = S2.major_no AND Course.course_no = S1.course_no
	  AND S1.major_no = '156' AND Course.course_no = '1005'