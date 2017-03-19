package cn.examsystem.demo;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {

	private JTextField textField = null;
	private JPasswordField passwordField = null;

	private JLabel jL1 = null;
	private JLabel jL2 = null;

	private JButton loginEnter = null;
	private JButton resetButton = null;

	public JDialog dialog = null;
	//用户身份标记
	private int flag;

	public LoginFrame() {

		this.setTitle("考研信息管理系统登录界面");
		this.setBackground(Color.cyan);
		this.setLayout(null);
		this.setBounds(0, 0, 550, 400);
		initFrame();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void initFrame() {
		// 创建面板
		Container contentPane = this.getContentPane();
		// 创建标签
		jL1 = new JLabel("请输入用户名");
		jL1.setBounds(90, 110, 150, 30);
		jL2 = new JLabel("请输入密码");
		jL2.setBounds(90, 160, 150, 30);
		jL1.setFont(new Font("微软雅黑", Font.BOLD, 16));
		jL2.setFont(new Font("微软雅黑", Font.BOLD, 16));
		// 创建用户名和密码文本框
		textField = new JTextField();
		textField.setBounds(200, 110, 250, 30);
		passwordField = new JPasswordField();
		passwordField.setBounds(200, 160, 250, 30);
		// 创建确认和重置按钮
		loginEnter = new JButton("确定");
		loginEnter.setBounds(150, 220, 80, 30);
		resetButton = new JButton("重置");
		resetButton.setBounds(300, 220, 80, 30);
		loginEnter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				String password = new String(passwordField.getPassword());
				if (username.equals("2014130202") && password.equals("123456")) {
					close();
					flag = 0;
					MenuFrame menuframe = new MenuFrame(flag);
				} else if (username.equals("admin") && password.equals("admin")) {
					close();
					flag = 1;
					MenuFrame menuframe = new MenuFrame(flag);
				} else {
					JOptionPane.showMessageDialog(null, "用户名或密码错误", "登录失败", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				passwordField.setText("");
			}
		});
		// 将所有组件添加到面板上
		contentPane.add(jL1);
		contentPane.add(jL2);
		contentPane.add(loginEnter);
		contentPane.add(resetButton);
		contentPane.add(passwordField);
		contentPane.add(textField);
	}

	// 隐藏窗口，显示另外的界面
	public void close() {
		this.setVisible(false);
	}

	public static void main(String[] args) {
		JFrame loginFrame = new LoginFrame();
	}
}
