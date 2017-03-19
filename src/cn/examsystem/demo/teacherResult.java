package cn.examsystem.demo;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cn.javase.demo.Bachelor;
import cn.javase.demo.Examinee;

public class teacherResult extends JFrame {

	// 创建标签、按钮、文本框
	private JLabel jL1 = null;
	private JLabel jL2 = null;
	private JTextField textField = null;
	private JTextArea area = null;
	// 获取存放学生的集合
	public Examinee examinee = Examinee.getInstance();
	public ArrayList<Bachelor> bachelors = (ArrayList<Bachelor>) examinee.getList();

	public teacherResult() {
		this.setTitle("考试结果咨询");
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

		returnResult();
	}

	private void returnResult() {

		InputStream in = null;
		OutputStream out = null;
		ServerSocket teacherServer = null;
		try {
			// 创建服务器
			teacherServer = new ServerSocket(10000);
			// 接收客户端
			Socket s = teacherServer.accept();
			// 获取客户端的IO流
			in = s.getInputStream();
			out = s.getOutputStream();
			// 接收客户端发来的考生姓名
			String name = null;
			byte[] buf = new byte[1024];
			int len = 0;
			len = in.read(buf);
			name = new String(buf, 0, len);
			textField.setText(textField.getText() + name);
			// 根据姓名找出考试信息并发给客户端
			for (Bachelor b : bachelors) {
				if (b.getName().equals(name)) {
					String title = '\n' + "名字" + "     " + "性别" + "   " + "学校" + "   " + "政治" + "     " + "英语" + "     "
							+ "数学" + "      " + "专业" + "    " + "总分" + '\n';
					String result = b.getName() + "      " + b.getSex() + "       " + b.getSchool() + "    "
							+ b.getPolity() + "    " + b.getEnglish() + "     " + b.getMath() + "      "
							+ b.getProfession() + "    " + b.getTotalPoint();
					String message = title + result;
					out.write(message.getBytes());
				}
			}
			area.setText(name + "同学查询成绩,已反馈成绩！");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 初始化窗体
	private void initFrame() {
		// 创建面板
		Container con = this.getContentPane();

		jL1 = new JLabel("与");
		jL1.setBounds(20, 20, 150, 30);
		jL1.setFont(new Font("微软雅黑", Font.BOLD, 18));

		jL2 = new JLabel("沟通结果如下:");
		jL2.setBounds(150, 20, 150, 30);
		jL2.setFont(new Font("微软雅黑", Font.BOLD, 18));

		// 初始化文本框
		textField = new JTextField();
		textField.setBounds(60, 20, 80, 30);
		area = new JTextArea();
		area.setBounds(10, 60, 470, 200);

		con.add(jL1);
		con.add(jL2);
		con.add(textField);
		con.add(area);
	}

}
