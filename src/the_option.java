/*
 *用户界面
 *选择、查询
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.util.Vector;

public class the_option extends JFrame
{
	private JFrame jfa;
	private JPanel jpa;
	private JPanel jpa1;
	private JButton []jbt;
	private JTable tab;        //表格组件
	private Font fnt;
	private Vector<String> title = new Vector<String>();       				   //表头
	private Vector<Vector<Object>> data = new Vector<Vector<Object>>();        //数据
	public the_option()
	{
		
		jfa = new JFrame("用户界面");
		jpa = new re2JPanel();
		jpa1 = new re3JPanel();
		jbt= new JButton[6];	
		jbt[0]= new JButton("本期成绩查询");
		jbt[1]= new JButton("四六级成绩查询");
		jbt[2]= new JButton("重修查询");
		jbt[3]= new JButton("下期课程查询");
		jbt[4]= new JButton("我的信息");
		jbt[5]= new JButton("退出");
		fnt = new Font("楷体",Font.PLAIN,20);
		init();
	}
	public void init()
	{
		jfa.setSize(680,500);
		jpa.setLayout(null);
	    jpa1.setLayout(null);
		jfa.setLayout(null);
		jpa.setFocusable(true);       //可获取焦点状态
		jpa.setBounds(0, 0, 180, 500);
		jpa1.setBounds(180,0,550,500);
		for (int i=0;i<6;i++)
		{
			jbt[i].setFont(fnt);
			jbt[i].setForeground(Color.CYAN);
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
		jbt[4].setBounds(0,132,180,30);
		jbt[5].setBounds(0,165,180,30);
		jfa.add(jpa);
		jfa.add(jpa1);
		jfa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfa.setVisible(true);
	}
	public void set_table()
	{
		//表格设置
		DefaultTableModel model  =  new DefaultTableModel(data,title);
		tab = new JTable(model);
		tab.setPreferredScrollableViewportSize(new Dimension(520, 400));
		JScrollPane jsp=new JScrollPane(tab);   //加入滚动窗格
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); //滚动条出现
        jsp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setBounds(0,0,500,500);
		jpa1.removeAll();                //去掉原有表
		jpa1.repaint();					 //刷新
		tab.setRowHeight(30);            //表格高度
		tab.setGridColor(Color.black);   //表格线颜色
		jsp.setOpaque(false);            //表透明
		jpa1.add(jsp);
		jfa.add(jpa1);
		jfa.repaint();                   //刷新界面
		jfa.setVisible(true);
	}
	/*
	 * 查询界面事件监听
	 */
	private class isActionListener2 implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			linkDataBase db = new linkDataBase();        //连接数据库
			JFrameMain jfr = new JFrameMain(1);
			int i = 0;
			for(i = 0; i < 6; i++)
			{
				if(jbt[i] == (JButton)e.getSource())
					break;
			}
			switch(i) 
			{
				case 0:{						
							ResultSet rs = null;
							title.clear();            //表头清空
							data.clear();			  //内容清空
							try 
							{	title.add("课程名称");
								title.add("成绩");
								title.add("专业平均成绩");
								title.add("专业内排名");
								String str1 = jfr.get_user();   //获取输入账户
								rs = db.get_score(str1);        //查询结果集
								while (rs.next())
								{
									Vector<Object> v = new Vector<Object>();								
									{										
										v.add(rs.getString(1));        //从结果集获取数据
										v.add(rs.getString(2));
										v.add(rs.getString(3));
										v.add(rs.getString(4));	
									}
									data.add(v);
								}
								set_table();      //设置表
								
							}
							catch (Exception a) 
				        	{
								a.printStackTrace();
				        	}
					  	}break;
				case 1:{
							ResultSet rs = null;      
							title.clear();            //表头清空
							data.clear();			  //内容清空
							try 
							{
								title.add("学号");
								title.add("四级成绩");
								title.add("六级成绩");
								title.add("备注");
								String str1 = jfr.get_user();   //获取输入账户
								rs = db.get_CTE46(str1);        //查询结果集
								while (rs.next())
								{
									Vector<Object> v = new Vector<Object>();								
									{										
										v.add(rs.getString(1));     
										v.add(rs.getString(2));
										v.add(rs.getString(3));
										v.add(rs.getString(4));	
									}
									data.add(v);
								}
								set_table();      //设置表
							}
							catch (Exception a) 
				        	{
								a.printStackTrace();
				        	}
					    }break;
				case 2:{
							ResultSet rs = null;
							title.clear();            //表头清空
							data.clear();			  //内容清空
							try 
							{
								title.add("重修科目");
								title.add("成绩");
								title.add("重修学期");
								title.add("重修成绩");
								String str1 = jfr.get_user();   //获取输入账户
								rs = db.get_repair(str1);        //查询结果集
								while (rs.next())
								{
									Vector<Object> v = new Vector<Object>();								
									{										
										v.add(rs.getString(1));
										v.add(rs.getString(2));
										v.add(rs.getString(3));
										v.add(rs.getString(4));	
									}
									data.add(v);
								}
								set_table();      //设置表
							}
							catch (Exception a) 
							{
								a.printStackTrace();
							}
			    		}break;
				case 3:{
							ResultSet rs = null;
							title.clear();            //表头清空
							data.clear();			  //内容清空
							try 
							{
								title.add("理论课程");
								title.add("上课学期");
								String str1 = jfr.get_user();   //获取输入账户
								rs = db.get_course(str1);        //查询结果集
								while (rs.next())
								{
									Vector<Object> v = new Vector<Object>();								
									{										
										v.add(rs.getString(1));
										v.add(rs.getString(2));
										//v.add(rs.getString(3));
										//v.add(rs.getString(4));	
									}
									data.add(v);
								}
								set_table();      //设置表
							}
							catch (Exception a) 
							{
								a.printStackTrace();
							}
						}break; 
				case 4:{
							ResultSet rs = null;
							title.clear();            //表头清空
							data.clear();			  //内容清空
							try 
							{
								title.add("学号");
								title.add("姓名");
								title.add("所学专业");
								title.add("所在学院");
								String str1 = jfr.get_user();   //获取输入账户
								rs = db.get_myinfo(str1);        //查询结果集
								while (rs.next())
								{
									Vector<Object> v = new Vector<Object>();								
									{										
										v.add(rs.getString(1));
										v.add(rs.getString(2));
										v.add(rs.getString(3));
										v.add(rs.getString(4));	
									}
									data.add(v);
								}
								set_table();      //设置表
							}
							catch (Exception a) 
							{
								a.printStackTrace();
							}
	    				}break;
				case 5:{
					 		System.exit(0);			//退出系统	
				 	   }
				}
		}
	}
}






















































