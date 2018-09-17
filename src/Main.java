/*
 * 程序入口
 */
import java.awt.*;
import java.sql.ResultSet;
import javax.swing.*;
public class Main {
	public static void main(String[] args)
	{
		//改变swing控件风格为当前windows风格
		try 
		{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) 
		{
            e.printStackTrace();
        }
		new JFrameMain();        //登录界面
	}
}
