/*
 * �������ݿ�
 * �������ݿ�
 * ��ȡ��ѯ�����
 * �޸�����
 * ��������
 */
import java.sql.*;
import javax.swing.JOptionPane;
public class linkDataBase {
	public Connection conn = null;
	public linkDataBase() 
	{
		try 
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        String url="jdbc:sqlserver://localhost:1433;databaseName=manager_score";
	        String user="sa";           //sa��������Ա
	        String password="981022984";//����
			conn = DriverManager.getConnection(url,user,password);
		}
		
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	public ResultSet get_stuno()
	{
		//��ȡ�˺�
		String tsql = "SELECT stu_no FROM Student";
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try 
		{
			pstmt = conn.prepareStatement(tsql);   
			rs = pstmt.executeQuery();			
		}
		catch (Exception e) 
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
		return rs;
	}
	public String get_stupassword(String str1)
	{
		//��ȡ����
		String tsql = "SELECT stu_password FROM Student WHERE stu_no = ?";
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String password = null;
		try 
		{
			pstmt = conn.prepareStatement(tsql);   //ִ��
			pstmt.setString(1, str1);
			rs = pstmt.executeQuery();             //�����
			rs.next();
			password = rs.getString(1);
		}
		catch (Exception e) 
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"�˺ų���,���������룡","��ʾ",JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
		return password;
	}
	public String get_mngpassword(String str1)
	{
		//��ȡ����
		String tsql = "SELECT manager_password FROM Manager WHERE manager_ID = ?";
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String password = null;
		try 
		{
			pstmt = conn.prepareStatement(tsql);   //ִ��
			pstmt.setString(1, str1);
			rs = pstmt.executeQuery();             //�����
			rs.next();
			password = rs.getString(1);
		}
		catch (Exception e) 
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"�˺ų���,���������룡","��ʾ",JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
		return password;
	}
	public ResultSet get_score(String str1)
	{
		//��ȡ���ڳɼ���Ϣ
		String tsql = "SELECT course_name AS ��Ŀ,sc_score AS �ɼ�,\r\n" + 
				"(SELECT AVG(S1.sc_score) \r\n" + 
				" FROM SC S1,SC S2\r\n" + 
				" WHERE S1.course_no = S2.course_no AND S1.major_no = S2.major_no AND S1.major_no = ST1.major_no\r\n" + 
				"	   AND S1.course_no = Course.course_no\r\n" + 
				" GROUP BY S1.course_no) AS רҵƽ���ɼ�,\r\n" + 
				"\r\n" + 
				"(SELECT ����\r\n" + 
				" FROM (SELECT DISTINCT S1.stu_no AS T_stu,course_name,S1.sc_score,DENSE_RANK() OVER(ORDER BY S1.sc_score DESC) AS ����\r\n" + 
				"       FROM SC S1,SC S2,Course C1\r\n" + 
				"       WHERE S1.course_no = S2.course_no AND S1.major_no = S2.major_no AND C1.course_no = S1.course_no\r\n" + 
				"	         AND S1.major_no = ST1.major_no AND C1.course_no = Course.course_no\r\n" + 
				"      ) T,Student\r\n" + 
				" WHERE Student.stu_no = T.T_stu AND Student.stu_no = ST1.stu_no\r\n" + 
				") AS ����\r\n" + 
				"\r\n" + 
				"FROM Student ST1,SC,Course\r\n" + 
				"WHERE ST1.stu_no = SC.stu_no AND SC.course_no = Course.course_no AND SC.sc_score != (-1) AND ST1.stu_no = ?";
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			try 
			{
				pstmt = conn.prepareStatement(tsql);   
				pstmt.setString(1, str1);
				rs = pstmt.executeQuery();			
			}
			catch (Exception e) 
	        {
	            e.printStackTrace();
	            System.out.println("��ѯ�������Ժ�����");
	        }
		return rs;
	}
	public ResultSet get_CTE46(String str1)
	{
		//��ȡ�������ɼ�
		String tsql = "SELECT Student.stu_no,CTE4,CTE6,remakers\r\n" + 
				"FROM Student,CTE46\r\n" + 
				"WHERE Student.stu_no = CTE46.stu_no AND Student.stu_no = ?";
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try 
		{
			pstmt = conn.prepareStatement(tsql);   
			pstmt.setString(1, str1);
			rs = pstmt.executeQuery();			
		}
		catch (Exception e) 
        {
            e.printStackTrace();
            System.out.println("��ѯ�������Ժ�����");
        }
		return rs;
	}
	public ResultSet get_repair(String str1)
	{
		//��ȡ������Ϣ
		String tsql = "SELECT course_name �γ�����,sc_score AS ���Գɼ�,\r\n" + 
				"(SELECT course_semeter+1\r\n" + 
				" FROM SC,Course C,Student ST\r\n" + 
				" WHERE ST.stu_no = SC.stu_no AND SC.sc_score < 60 AND SC.course_no = C.course_no\r\n" + 
				"	  AND Student.stu_no = ST.stu_no AND C.course_no = Course.course_no) AS ����ѧ��\r\n" + 
				",sc_rescore AS ���޳ɼ�\r\n" + 
				"FROM Student,SC,Course\r\n" + 
				"WHERE Student.stu_no = SC.stu_no AND SC.sc_score < 60 AND SC.course_no = Course.course_no\r\n" + 
				"	  AND SC.sc_score != (-1) AND Student.stu_no = ?";
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try 
		{
			pstmt = conn.prepareStatement(tsql);   
			pstmt.setString(1, str1);
			rs = pstmt.executeQuery();			
		}
		catch (Exception e) 
        {
            e.printStackTrace();
            System.out.println("��ѯ�������Ժ�����");
        }
		return rs;
	}
	public ResultSet get_course(String str1)
	{
		//��ȡ��ѧ�ڿγ���Ϣ
		String tsql = "SELECT course_name,course_semeter\r\n" + 
				"FROM Student,SC,Course\r\n" + 
				"WHERE Student.stu_no = SC.stu_no AND SC.course_no = Course.course_no AND Student.stu_no = ?\r\n" + 
				"	  AND course_semeter \r\n" + 
				"	  IN(SELECT course_semeter FROM SC,Course WHERE SC.course_no = Course.course_no AND SC.sc_score = (-1))";
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try 
		{
			pstmt = conn.prepareStatement(tsql);   
			pstmt.setString(1, str1);
			rs = pstmt.executeQuery();			
		}
		catch (Exception e) 
        {
            e.printStackTrace();
            System.out.println("��ѯ�������Ժ�����");
        }
		return rs;
	}
	public ResultSet get_myinfo(String str1)
	{
		//��ȡ�û���Ϣ
		String tsql = "SELECT stu_no,stu_name,major_name,dept_name\r\n" + 
				"FROM Student,Dept,Major\r\n" + 
				"WHERE Student.dept_no = Dept.dept_no AND Student.major_no = Major.major_no\r\n" + 
				"	  AND stu_no = ?";
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try 
		{
			pstmt = conn.prepareStatement(tsql);   
			pstmt.setString(1, str1);
			rs = pstmt.executeQuery();			
		}
		catch (Exception e) 
        {
            e.printStackTrace();
            System.out.println("��ѯ�������Ժ�����");
        }
		return rs;
	}
	public void insertData(String str1)
	{
		String tsql = "BULK INSERT test\r\n" + 
				"FROM 'E:\\ľͷ��\\Login\\sql\\test.txt'";
		PreparedStatement pstmt=null;
		try 
		{
			pstmt = conn.prepareStatement(tsql);   
			//pstmt.setString(1, str1);
			pstmt.executeUpdate();
		}
		catch (Exception e) 
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"�����ļ�����,������ѡ��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
            throw new RuntimeException(e);
        }
		JOptionPane.showMessageDialog(null,"����ɹ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
	}
}















