package cn.examsystem.demo;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cn.javase.demo.Bachelor;
import cn.javase.demo.Examinee;

public class studentResult extends JFrame {

	// ������ǩ����ť���ı���
	private JLabel jL = null;
	private JButton enterButton = null;
	private JTextField textField1 = null;
	private JTextArea area = null;
	
	// ��ʼ������
	public studentResult() {
		this.setTitle("���Գɼ�Զ�̲�ѯ");
		this.setBackground(Color.cyan);
		this.setLayout(null);
		this.setBounds(0, 0, 500, 300);
		initFrame();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void initFrame() {
		// �������
		Container con = this.getContentPane();
		jL = new JLabel("�������� ��������");
		jL.setBounds(20, 20, 150, 30);
		jL.setFont(new Font("΢���ź�", Font.BOLD, 16));
		// ��ʼ���ı���
		textField1 = new JTextField();
		textField1.setBounds(150, 20, 200, 30);
		area = new JTextArea();
		area.setBounds(10, 60, 470, 200);
		// ��ʼ����ť
		enterButton = new JButton("��ѯ");
		enterButton.setBounds(380, 20, 60, 30);
		// ������ť
		enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OutputStream out = null;
				InputStream in = null;
				String name = textField1.getText().trim().toString();
				Socket studentClient = null;
				try {
					// ��ȡ�ͻ���
					studentClient = new Socket("172.29.26.121", 10000);
					// ��ȡ�ͻ��˵�IO��
					in = studentClient.getInputStream();
					out = studentClient.getOutputStream();
					// ����ѯ���������͸�������
					out.write(name.getBytes());
					out.flush();
					// ���ܷ��������صĿ��Խ��
					area.setText("������������ѯ�������:");
					byte[] buf = new byte[1024];
					int len = 0;
					len = in.read(buf);
					area.setText(area.getText() + new String(buf, 0, len));
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} finally {
					//�ر���Դ
					try {
						if (studentClient != null) {
							out.close();
							in.close();
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		con.add(jL);
		con.add(enterButton);
		con.add(textField1);
		con.add(area);
	}
}
