USE manager_score
GO
SELECT course_name �γ�����,sc_score AS ���Գɼ�,
(SELECT course_semeter+1
 FROM SC,Course C,Student ST
 WHERE ST.stu_no = SC.stu_no AND SC.sc_score < 60 AND SC.course_no = C.course_no
	  AND Student.stu_no = ST.stu_no AND C.course_no = Course.course_no) AS ����ѧ��
,sc_rescore AS ���޳ɼ�
FROM Student,SC,Course
WHERE Student.stu_no = SC.stu_no AND SC.sc_score < 60 AND SC.course_no = Course.course_no
	  AND SC.sc_score != (-1) AND Student.stu_no = '1415243'