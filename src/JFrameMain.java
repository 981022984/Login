/*
 * ��¼����
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Vector;
public class JFrameMain extends JFrame{
	private JFrame jfrMain;
	private JPanel jpaFirst;
	private JTextField jte_user;
	private JPasswordField jpa_password;
	private JLabel jla_forgetpass;
	private JCheckBox jch_holdpass;
	private JCheckBox jch_autologin;
	private JButton jbu_login;
	public static String user;      //�˺�
	private String password;        //����
	public JFrameMain()
	{
		//���캯������ʾ����
		jfrMain = new JFrame("ϵͳ��¼");
		jpaFirst = new re1JPanel();
		jte_user = new JTextField("�����˻�",20);
		jpa_password = new JPasswordField(20);
		jch_holdpass = new JCheckBox("��ס����");
		jch_autologin = new JCheckBox("�Զ���¼");
		jbu_login = new JButton("��¼");
		init();
		jfrMain.setVisible(true);
	}
	public JFrameMain(int i)
	{
		//���캯�����أ�����ʾ����
		jfrMain = new JFrame("��������ϵͳ");
		jpaFirst = new re1JPanel();
		jte_user = new JTextField("�����˻�",30);
		jpa_password = new JPasswordField(30);
		jla_forgetpass = new JLabel("��������");
		jch_holdpass = new JCheckBox("��ס����");
		jch_autologin = new JCheckBox("�Զ���¼");
		jbu_login = new JButton("��¼");
		init();
	}
	public void init() 
	{
		jfrMain.setSize(450,340);
		jfrMain.setResizable(false);
		jpaFirst.setLayout(null);
		jpaFirst.setFocusable(true);     //�ɻ�ȡ����״̬
		
		jte_user.addFocusListener(new isFocusListener());	
		jpa_password.addFocusListener(new isFocusListener());
		jpa_password.setEchoChar('\0');                  //�������ʾ����
		jpa_password.setText("��������");
		jte_user.setBounds(120,130,200,25);
		jpa_password.setBounds(120,160,200,25);
		jch_holdpass.setBounds(120,190,80,30);
		jch_holdpass.setOpaque(false);                  //JCheckBox͸��
		jch_autologin.setBounds(240,190,80,30);
		jch_autologin.setOpaque(false);
		jbu_login.addActionListener(new isActionListener());  //�����¼�ע��
		jbu_login.setBounds(120, 230, 200, 30);
		jfrMain.add(jpaFirst);
		jpaFirst.add(jte_user);
		jpaFirst.add(jpa_password);
		jpaFirst.add(jch_holdpass);
		jpaFirst.add(jch_autologin);
		jpaFirst.add(jbu_login);
		jfrMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//jfrMain.setVisible(true);
	}
	public String get_user()
	{
		//��ȡ��¼�˺�
		return user;
	}
	
	/*
	 * �ı��򽹵��¼�
	 */
	private class isFocusListener extends FocusAdapter
	{
		/*
		 * (non-Javadoc)
		 * @see java.awt.event.FocusAdapter#focusGained(java.awt.event.FocusEvent)
		 * ��ý���ʱ
		 * ��ý��� && û���˻�
		 * ��ý��� && ���˻�
		 * ��ý��� && û������
		 * ��ý��� && ������
		 */
		public void focusGained(FocusEvent e) 
		{
			if(jte_user == (JTextField)e.getSource() && jte_user.getText().equals("�����˻�"))
			{
				jte_user.setText("");
			}
			else if(jte_user == (JTextField)e.getSource() && jte_user.getText().equals("�����˻�") == false ) {}
			else if(jpa_password == (JPasswordField)e.getSource() && jpa_password.getText().trim().equals("��������"))
			{
				jpa_password.setEchoChar('*');                  //�����������ʾΪ��*��
				jpa_password.setText("");
			}
			else if(jpa_password == (JPasswordField)e.getSource() && jpa_password.getText().trim().equals("��������") == false) {}
		}
		/*
		 * ʧȥ����ʱ
		 * ʧȥ����&&û�������˻�
		 * ʧȥ����&&�������˻�
		 * ʧȥ����&&û����������
		 * ʧȥ����&&����������
		 */
		public void focusLost(FocusEvent e) 
		{
			if(jte_user == (JTextField)e.getSource() && jte_user.getText().trim().equals("")) 
			{      //trim()  ȥ���ַ�����β�ַ�
				jte_user.setText("�����˻�");
			}
			else if(jte_user == (JTextField)e.getSource() && jte_user.getText().trim().equals("")== false)
			{
				user = jte_user.getText();
			}
			else if(jpa_password == (JPasswordField)e.getSource() && jpa_password.getText().trim().equals("")) 
			{
				jpa_password.setEchoChar('\0');                  //�������ʾ����
				jpa_password.setText("��������");
			}
			else if(jpa_password == (JPasswordField)e.getSource() && jpa_password.getText().trim().equals("")== false)
			{
				password = jpa_password.getText();
			}
		}
	}
	
	/*
	 * �����¼ʱ
	 */
	private class isActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			user = jte_user.getText();  //��ȡ�˻�
			linkDataBase db = new linkDataBase();			              
			if(jbu_login == (JButton)e.getSource() && user.length() <= 8) 
			{
				//�û���¼
				password = jpa_password.getText();     //��ȡ���������
				String str2 = db.get_stupassword(user);//��ȡ��ȷ����
				if(str2.equals(password))
				{
					//JOptionPane.showMessageDialog(null,"��¼�ɹ�");
					jfrMain.dispose();      //�رյ�¼����
					new the_option();       //���û�����
				}
				else
					JOptionPane.showMessageDialog(null,"�˺Ż������������������!","����",JOptionPane.WARNING_MESSAGE);
			}
			else if(jbu_login == (JButton)e.getSource() && user.length() > 8)
			{
				//����Ա��¼
				password = jpa_password.getText();     //��ȡ���������
				String str2 = db.get_mngpassword(user);//��ȡ��ȷ����
				if(str2.equals(password))
				{
					jfrMain.dispose();      //�رյ�¼����
					new the_manager();      //�򿪹���Ա����
				}
				else
					JOptionPane.showMessageDialog(null,"�˺Ż������������������!","����",JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}

















