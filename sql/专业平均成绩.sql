SELECT AVG(S1.sc_score) 
FROM SC S1,SC S2
WHERE S1.course_no = S2.course_no AND S1.major_no = S2.major_no AND S1.major_no = '156'
GROUP BY S1.course_no