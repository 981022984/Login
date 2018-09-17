/*
 * 登录界面
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
	public static String user;      //账号
	private String password;        //密码
	public JFrameMain()
	{
		//构造函数，显示界面
		jfrMain = new JFrame("系统登录");
		jpaFirst = new re1JPanel();
		jte_user = new JTextField("输入账户",20);
		jpa_password = new JPasswordField(20);
		jch_holdpass = new JCheckBox("记住密码");
		jch_autologin = new JCheckBox("自动登录");
		jbu_login = new JButton("登录");
		init();
		jfrMain.setVisible(true);
	}
	public JFrameMain(int i)
	{
		//构造函数重载，不显示界面
		jfrMain = new JFrame("火星移民系统");
		jpaFirst = new re1JPanel();
		jte_user = new JTextField("输入账户",30);
		jpa_password = new JPasswordField(30);
		jla_forgetpass = new JLabel("忘记密码");
		jch_holdpass = new JCheckBox("记住密码");
		jch_autologin = new JCheckBox("自动登录");
		jbu_login = new JButton("登录");
		init();
	}
	public void init() 
	{
		jfrMain.setSize(450,340);
		jfrMain.setResizable(false);
		jpaFirst.setLayout(null);
		jpaFirst.setFocusable(true);     //可获取焦点状态
		
		jte_user.addFocusListener(new isFocusListener());	
		jpa_password.addFocusListener(new isFocusListener());
		jpa_password.setEchoChar('\0');                  //密码框显示明文
		jpa_password.setText("输入密码");
		jte_user.setBounds(120,130,200,25);
		jpa_password.setBounds(120,160,200,25);
		jch_holdpass.setBounds(120,190,80,30);
		jch_holdpass.setOpaque(false);                  //JCheckBox透明
		jch_autologin.setBounds(240,190,80,30);
		jch_autologin.setOpaque(false);
		jbu_login.addActionListener(new isActionListener());  //监听事件注册
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
		//获取登录账号
		return user;
	}
	
	/*
	 * 文本框焦点事件
	 */
	private class isFocusListener extends FocusAdapter
	{
		/*
		 * (non-Javadoc)
		 * @see java.awt.event.FocusAdapter#focusGained(java.awt.event.FocusEvent)
		 * 获得焦点时
		 * 获得焦点 && 没有账户
		 * 获得焦点 && 有账户
		 * 获得焦点 && 没有密码
		 * 获得焦点 && 有密码
		 */
		public void focusGained(FocusEvent e) 
		{
			if(jte_user == (JTextField)e.getSource() && jte_user.getText().equals("输入账户"))
			{
				jte_user.setText("");
			}
			else if(jte_user == (JTextField)e.getSource() && jte_user.getText().equals("输入账户") == false ) {}
			else if(jpa_password == (JPasswordField)e.getSource() && jpa_password.getText().trim().equals("输入密码"))
			{
				jpa_password.setEchoChar('*');                  //密码框内容显示为‘*’
				jpa_password.setText("");
			}
			else if(jpa_password == (JPasswordField)e.getSource() && jpa_password.getText().trim().equals("输入密码") == false) {}
		}
		/*
		 * 失去焦点时
		 * 失去焦点&&没有输入账户
		 * 失去焦点&&输入了账户
		 * 失去焦点&&没有输入密码
		 * 失去焦点&&输入了密码
		 */
		public void focusLost(FocusEvent e) 
		{
			if(jte_user == (JTextField)e.getSource() && jte_user.getText().trim().equals("")) 
			{      //trim()  去掉字符串首尾字符
				jte_user.setText("输入账户");
			}
			else if(jte_user == (JTextField)e.getSource() && jte_user.getText().trim().equals("")== false)
			{
				user = jte_user.getText();
			}
			else if(jpa_password == (JPasswordField)e.getSource() && jpa_password.getText().trim().equals("")) 
			{
				jpa_password.setEchoChar('\0');                  //密码框显示明文
				jpa_password.setText("输入密码");
			}
			else if(jpa_password == (JPasswordField)e.getSource() && jpa_password.getText().trim().equals("")== false)
			{
				password = jpa_password.getText();
			}
		}
	}
	
	/*
	 * 点击登录时
	 */
	private class isActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			user = jte_user.getText();  //获取账户
			linkDataBase db = new linkDataBase();			              
			if(jbu_login == (JButton)e.getSource() && user.length() <= 8) 
			{
				//用户登录
				password = jpa_password.getText();     //获取输入的密码
				String str2 = db.get_stupassword(user);//获取正确密码
				if(str2.equals(password))
				{
					//JOptionPane.showMessageDialog(null,"登录成功");
					jfrMain.dispose();      //关闭登录界面
					new the_option();       //打开用户界面
				}
				else
					JOptionPane.showMessageDialog(null,"账号或密码错误，请重新输入!","警告",JOptionPane.WARNING_MESSAGE);
			}
			else if(jbu_login == (JButton)e.getSource() && user.length() > 8)
			{
				//管理员登录
				password = jpa_password.getText();     //获取输入的密码
				String str2 = db.get_mngpassword(user);//获取正确密码
				if(str2.equals(password))
				{
					jfrMain.dispose();      //关闭登录界面
					new the_manager();      //打开管理员界面
				}
				else
					JOptionPane.showMessageDialog(null,"账号或密码错误，请重新输入!","警告",JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}

















