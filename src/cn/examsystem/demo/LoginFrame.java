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
	//�û���ݱ��
	private int flag;

	public LoginFrame() {

		this.setTitle("������Ϣ����ϵͳ��¼����");
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
		// �������
		Container contentPane = this.getContentPane();
		// ������ǩ
		jL1 = new JLabel("�������û���");
		jL1.setBounds(90, 110, 150, 30);
		jL2 = new JLabel("����������");
		jL2.setBounds(90, 160, 150, 30);
		jL1.setFont(new Font("΢���ź�", Font.BOLD, 16));
		jL2.setFont(new Font("΢���ź�", Font.BOLD, 16));
		// �����û����������ı���
		textField = new JTextField();
		textField.setBounds(200, 110, 250, 30);
		passwordField = new JPasswordField();
		passwordField.setBounds(200, 160, 250, 30);
		// ����ȷ�Ϻ����ð�ť
		loginEnter = new JButton("ȷ��");
		loginEnter.setBounds(150, 220, 80, 30);
		resetButton = new JButton("����");
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
					JOptionPane.showMessageDialog(null, "�û������������", "��¼ʧ��", JOptionPane.ERROR_MESSAGE);
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
		// �����������ӵ������
		contentPane.add(jL1);
		contentPane.add(jL2);
		contentPane.add(loginEnter);
		contentPane.add(resetButton);
		contentPane.add(passwordField);
		contentPane.add(textField);
	}

	// ���ش��ڣ���ʾ����Ľ���
	public void close() {
		this.setVisible(false);
	}

	public static void main(String[] args) {
		JFrame loginFrame = new LoginFrame();
	}
}
