/*
 *�û�����
 *ѡ�񡢲�ѯ
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
	private JTable tab;        //������
	private Font fnt;
	private Vector<String> title = new Vector<String>();       				   //��ͷ
	private Vector<Vector<Object>> data = new Vector<Vector<Object>>();        //����
	public the_option()
	{
		
		jfa = new JFrame("�û�����");
		jpa = new re2JPanel();
		jpa1 = new re3JPanel();
		jbt= new JButton[6];	
		jbt[0]= new JButton("���ڳɼ���ѯ");
		jbt[1]= new JButton("�������ɼ���ѯ");
		jbt[2]= new JButton("���޲�ѯ");
		jbt[3]= new JButton("���ڿγ̲�ѯ");
		jbt[4]= new JButton("�ҵ���Ϣ");
		jbt[5]= new JButton("�˳�");
		fnt = new Font("����",Font.PLAIN,20);
		init();
	}
	public void init()
	{
		jfa.setSize(680,500);
		jpa.setLayout(null);
	    jpa1.setLayout(null);
		jfa.setLayout(null);
		jpa.setFocusable(true);       //�ɻ�ȡ����״̬
		jpa.setBounds(0, 0, 180, 500);
		jpa1.setBounds(180,0,550,500);
		for (int i=0;i<6;i++)
		{
			jbt[i].setFont(fnt);
			jbt[i].setForeground(Color.CYAN);
			jbt[i].setBorder(BorderFactory.createRaisedBevelBorder()); //��ť͹��
			jbt[i].setOpaque(false);            //ȥ����ť����
			jbt[i].setContentAreaFilled(false);
			jbt[i].addActionListener(new isActionListener2());   //����ע��
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
		//�������
		DefaultTableModel model  =  new DefaultTableModel(data,title);
		tab = new JTable(model);
		tab.setPreferredScrollableViewportSize(new Dimension(520, 400));
		JScrollPane jsp=new JScrollPane(tab);   //�����������
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); //����������
        jsp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setBounds(0,0,500,500);
		jpa1.removeAll();                //ȥ��ԭ�б�
		jpa1.repaint();					 //ˢ��
		tab.setRowHeight(30);            //���߶�
		tab.setGridColor(Color.black);   //�������ɫ
		jsp.setOpaque(false);            //��͸��
		jpa1.add(jsp);
		jfa.add(jpa1);
		jfa.repaint();                   //ˢ�½���
		jfa.setVisible(true);
	}
	/*
	 * ��ѯ�����¼�����
	 */
	private class isActionListener2 implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			linkDataBase db = new linkDataBase();        //�������ݿ�
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
							title.clear();            //��ͷ���
							data.clear();			  //�������
							try 
							{	title.add("�γ�����");
								title.add("�ɼ�");
								title.add("רҵƽ���ɼ�");
								title.add("רҵ������");
								String str1 = jfr.get_user();   //��ȡ�����˻�
								rs = db.get_score(str1);        //��ѯ�����
								while (rs.next())
								{
									Vector<Object> v = new Vector<Object>();								
									{										
										v.add(rs.getString(1));        //�ӽ������ȡ����
										v.add(rs.getString(2));
										v.add(rs.getString(3));
										v.add(rs.getString(4));	
									}
									data.add(v);
								}
								set_table();      //���ñ�
								
							}
							catch (Exception a) 
				        	{
								a.printStackTrace();
				        	}
					  	}break;
				case 1:{
							ResultSet rs = null;      
							title.clear();            //��ͷ���
							data.clear();			  //�������
							try 
							{
								title.add("ѧ��");
								title.add("�ļ��ɼ�");
								title.add("�����ɼ�");
								title.add("��ע");
								String str1 = jfr.get_user();   //��ȡ�����˻�
								rs = db.get_CTE46(str1);        //��ѯ�����
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
								set_table();      //���ñ�
							}
							catch (Exception a) 
				        	{
								a.printStackTrace();
				        	}
					    }break;
				case 2:{
							ResultSet rs = null;
							title.clear();            //��ͷ���
							data.clear();			  //�������
							try 
							{
								title.add("���޿�Ŀ");
								title.add("�ɼ�");
								title.add("����ѧ��");
								title.add("���޳ɼ�");
								String str1 = jfr.get_user();   //��ȡ�����˻�
								rs = db.get_repair(str1);        //��ѯ�����
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
								set_table();      //���ñ�
							}
							catch (Exception a) 
							{
								a.printStackTrace();
							}
			    		}break;
				case 3:{
							ResultSet rs = null;
							title.clear();            //��ͷ���
							data.clear();			  //�������
							try 
							{
								title.add("���ۿγ�");
								title.add("�Ͽ�ѧ��");
								String str1 = jfr.get_user();   //��ȡ�����˻�
								rs = db.get_course(str1);        //��ѯ�����
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
								set_table();      //���ñ�
							}
							catch (Exception a) 
							{
								a.printStackTrace();
							}
						}break; 
				case 4:{
							ResultSet rs = null;
							title.clear();            //��ͷ���
							data.clear();			  //�������
							try 
							{
								title.add("ѧ��");
								title.add("����");
								title.add("��ѧרҵ");
								title.add("����ѧԺ");
								String str1 = jfr.get_user();   //��ȡ�����˻�
								rs = db.get_myinfo(str1);        //��ѯ�����
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
								set_table();      //���ñ�
							}
							catch (Exception a) 
							{
								a.printStackTrace();
							}
	    				}break;
				case 5:{
					 		System.exit(0);			//�˳�ϵͳ	
				 	   }
				}
		}
	}
}






















































