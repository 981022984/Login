/*
 * �������
 */
import java.awt.*;
import java.sql.ResultSet;
import javax.swing.*;
public class Main {
	public static void main(String[] args)
	{
		//�ı�swing�ؼ����Ϊ��ǰwindows���
		try 
		{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) 
		{
            e.printStackTrace();
        }
		new JFrameMain();        //��¼����
	}
}
