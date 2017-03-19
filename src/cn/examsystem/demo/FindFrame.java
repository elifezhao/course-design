package cn.examsystem.demo;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cn.javase.demo.Bachelor;
import cn.javase.demo.Examinee;

public class FindFrame extends JFrame {

	// 创建标签、按钮、文本框
	private JLabel jL = null;
	private JButton enterButton = null;
	private JTextField textField1 = null;
	private JTextArea area = null;
	// 获取存放学生的集合
	public Examinee examinee = Examinee.getInstance();
	public ArrayList<Bachelor> bachelors = (ArrayList<Bachelor>) examinee.getList();

	// 初始化窗体
	public FindFrame() {
		this.setTitle("学生信息查询");
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
		jL = new JLabel("请输入查询姓名：");
		jL.setBounds(20, 20, 150, 30);
		jL.setFont(new Font("微软雅黑", Font.BOLD, 16));
		// 初始化文本框
		textField1 = new JTextField();
		textField1.setBounds(150, 20, 200, 30);
		area = new JTextArea();
		area.setBounds(10, 60, 470, 200);
		// 初始化按钮
		enterButton = new JButton("确认");
		enterButton.setBounds(380, 20, 60, 30);
		// 监听按钮
		enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = textField1.getText().trim().toString();
				for (Bachelor b : bachelors) {
					if (b.getName().equals(name)) {
						area.setText("名字" + "     " + "性别" + "   " + "学校" + "   " + "政治" + "     " + "英语" + "     "
								+ "数学" + "      " + "专业" + "    " + "总分");
						area.setText(area.getText() + '\n' + b.getName() + "      " + b.getSex() + "       "
								+ b.getSchool() + "    " + b.getPolity() + "    " + b.getEnglish() + "     "
								+ b.getMath() + "      " + b.getProfession() + "    " + b.getTotalPoint());
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
