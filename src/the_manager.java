/*
 * ����Ա����
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
		jfa = new JFrame("����Ա����");
		jfc = new JFileChooser(new File("."));
		jpa = new re5JPanel();
		jpa1 = new re4JPanel();
		jbt= new JButton[5];
		jtf = new JTextField("ѡ��Ҫ������ļ�");
		jbt[0]= new JButton("����ѧ����Ϣ");
		jbt[1]= new JButton("����γ���Ϣ");
		jbt[2]= new JButton("����ɼ���Ϣ");
		jbt[3]= new JButton("�˳�");
		jbt[4]= new JButton("����");
		fnt = new Font("����",Font.PLAIN,20);
		init();
	}
	public void init()
	{
		jfa.setSize(680,500);
		jpa.setLayout(null);
	    jpa1.setLayout(null);
		jfa.setLayout(null);
		jpa.setFocusable(true);    //�ɻ�ȡ����״̬
		jpa.setBounds(0, 0, 180, 500);
		jpa1.setBounds(180,0,550,500);
		jtf.setBounds(0,200,400,30);
		jbt[4].setBounds(400,200,80,30);
		for (int i=0;i<5;i++)
		{
			jbt[i].setFont(fnt);
			jbt[i].setForeground(Color.BLACK);
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
		//��ť�¼���Ӧ
		public void actionPerformed(ActionEvent e) 
		{
			linkDataBase db = new linkDataBase();	  //�������ݿ�			
			File f = null;                            //ѡ����ļ���������
			String path1 = null;                      //�ļ�·��
			String path2 = null;					  //�ļ�·��
			int i = 0;
			for( i = 0; i < 5; i++)
			{
				if(jbt[i] == (JButton)e.getSource())
					break;
			}
			if( i<= 2 )
			{
				//ѡ��Ҫ������ļ�
				int status = jfc.showOpenDialog(jbt[i]);
				if(status == JFileChooser.APPROVE_OPTION)   //������򿪡�
				{
					f = jfc.getSelectedFile();
					path1 = f.getPath();
					jtf.setText(path1);
				}
			}
			
			else if(i == 3)
			{
				//�˳�ϵͳ
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

















































