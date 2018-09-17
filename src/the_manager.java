/*
 * 管理员界面
 */
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import java.sql.*;
import java.util.Scanner;
class the_manager extends JFrame
{
	private JFrame jfa;
	private JPanel jpa;
	private JPanel jpa1;
	private JButton []jbt;
	private JTextField jtf;
	private JFileChooser jfc;
	private Font fnt;
	public the_manager()
	{			
		jfa = new JFrame("管理员界面");
		jfc = new JFileChooser(new File("."));
		jpa = new re5JPanel();
		jpa1 = new re4JPanel();
		jbt= new JButton[5];
		jtf = new JTextField("选择要插入的文件");
		jbt[0]= new JButton("插入学生信息");
		jbt[1]= new JButton("插入课程信息");
		jbt[2]= new JButton("插入成绩信息");
		jbt[3]= new JButton("退出");
		jbt[4]= new JButton("插入");
		fnt = new Font("楷体",Font.PLAIN,20);
		init();
	}
	public void init()
	{
		jfa.setSize(680,500);
		jpa.setLayout(null);
	    jpa1.setLayout(null);
		jfa.setLayout(null);
		jpa.setFocusable(true);    //可获取焦点状态
		jpa.setBounds(0, 0, 180, 500);
		jpa1.setBounds(180,0,550,500);
		jtf.setBounds(0,200,400,30);
		jbt[4].setBounds(400,200,80,30);
		for (int i=0;i<5;i++)
		{
			jbt[i].setFont(fnt);
			jbt[i].setForeground(Color.BLACK);
			jbt[i].setBorder(BorderFactory.createRaisedBevelBorder()); //按钮凸起
		    jbt[i].setOpaque(false);            //去掉按钮背景
			jbt[i].setContentAreaFilled(false);
			jbt[i].addActionListener(new isActionListener2());   //监听注册
			jpa.add(jbt[i]);
		}
		jbt[0].setBounds(0,0,180,30);
		jbt[1].setBounds(0,33,180,30);
		jbt[2].setBounds(0,66,180,30);
		jbt[3].setBounds(0,99,180,30);
	    jpa1.add(jfc);
	    jpa1.add(jbt[4]);
	    jpa1.add(jtf);
		jfa.add(jpa);
		jfa.add(jpa1);
		jfa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfa.setVisible(true);
	}
	private class isActionListener2 implements ActionListener
	{
		//按钮事件响应
		public void actionPerformed(ActionEvent e) 
		{
			linkDataBase db = new linkDataBase();	  //连接数据库			
			File f = null;                            //选择的文件保存在这
			String path1 = null;                      //文件路径
			String path2 = null;					  //文件路径
			int i = 0;
			for( i = 0; i < 5; i++)
			{
				if(jbt[i] == (JButton)e.getSource())
					break;
			}
			if( i<= 2 )
			{
				//选择要导入的文件
				int status = jfc.showOpenDialog(jbt[i]);
				if(status == JFileChooser.APPROVE_OPTION)   //点击‘打开’
				{
					f = jfc.getSelectedFile();
					path1 = f.getPath();
					jtf.setText(path1);
				}
			}
			
			else if(i == 3)
			{
				//退出系统
				System.exit(0);
			}
			else  if(i == 4)
			{
				path2 = jtf.getText();
				path2.replaceAll( "\\\\ ",   "\\\\\\\\ ");
				db.insertData(path2);	
			}
		}
	}
}

















































