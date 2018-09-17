/*
 * 重写JPanel类，实现背景图片功能
 */
import java.awt.*;
import javax.swing.*;
class re1JPanel extends JPanel{
	//登录背景
	private Image image;
	private ImageIcon icon;
	public re1JPanel() {
		icon=new ImageIcon("src/p3.jpg");  
        image=icon.getImage(); 
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 
		g.drawImage(image, 0, 0, getWidth(),getHeight(),this);
	}
}
class re2JPanel extends JPanel
{
	//学生用户背景
	private Image image;
	private ImageIcon icon;
	public  re2JPanel() {
		icon=new ImageIcon("src/p12.jpg");  
        image=icon.getImage(); 
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 
		g.drawImage(image, 0, 0, getWidth(),getHeight(),this);
	}
}
class re3JPanel extends JPanel
{
	//学生用户背景
	private Image image;
	private ImageIcon icon;
	public  re3JPanel() {
		icon=new ImageIcon("src/p11.jpg");  
        image=icon.getImage(); 
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 
		g.drawImage(image, 0, 0, getWidth(),getHeight(),this);
	}
}
class re4JPanel extends JPanel
{
	//管理员界面背景
	private Image image;
	private ImageIcon icon;
	public  re4JPanel() {
		icon=new ImageIcon("src/p14.jpg");  
        image=icon.getImage(); 
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 
		g.drawImage(image, 0, 0, getWidth(),getHeight(),this);
	}
}
class re5JPanel extends JPanel
{
	//管理员界面背景
	private Image image;
	private ImageIcon icon;
	public  re5JPanel() {
		icon=new ImageIcon("src/p5.jpg");  
        image=icon.getImage(); 
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 
		g.drawImage(image, 0, 0, getWidth(),getHeight(),this);
	}
}
