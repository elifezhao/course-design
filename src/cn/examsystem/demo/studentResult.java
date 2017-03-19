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

	// 创建标签、按钮、文本框
	private JLabel jL = null;
	private JButton enterButton = null;
	private JTextField textField1 = null;
	private JTextArea area = null;
	
	// 初始化窗体
	public studentResult() {
		this.setTitle("考试成绩远程查询");
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
		// 创建面板
		Container con = this.getContentPane();
		jL = new JLabel("请输入你 的姓名：");
		jL.setBounds(20, 20, 150, 30);
		jL.setFont(new Font("微软雅黑", Font.BOLD, 16));
		// 初始化文本框
		textField1 = new JTextField();
		textField1.setBounds(150, 20, 200, 30);
		area = new JTextArea();
		area.setBounds(10, 60, 470, 200);
		// 初始化按钮
		enterButton = new JButton("查询");
		enterButton.setBounds(380, 20, 60, 30);
		// 监听按钮
		enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OutputStream out = null;
				InputStream in = null;
				String name = textField1.getText().trim().toString();
				Socket studentClient = null;
				try {
					// 获取客户端
					studentClient = new Socket("172.29.26.121", 10000);
					// 获取客户端的IO流
					in = studentClient.getInputStream();
					out = studentClient.getOutputStream();
					// 将查询的姓名发送给服务器
					out.write(name.getBytes());
					out.flush();
					// 接受服务器返回的考试结果
					area.setText("服务器反馈查询结果如下:");
					byte[] buf = new byte[1024];
					int len = 0;
					len = in.read(buf);
					area.setText(area.getText() + new String(buf, 0, len));
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} finally {
					//关闭资源
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
