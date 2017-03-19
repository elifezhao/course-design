package cn.examsystem.demo;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cn.javase.demo.Bachelor;
import cn.javase.demo.Examinee;
import cn.javase.demo.Matriculate;
import cn.teacher.demo.Teacher;

public class ListFrame extends JFrame {

	// 创建标签
	private JLabel jL1 = null;
	private JLabel jL2 = null;
	// 创建文本框
	private JTextArea[] textAreas = new JTextArea[4];

	// 创建存放学生的集合
	public ArrayList<Bachelor> bachelorList = new ArrayList<Bachelor>();
	public ArrayList<Bachelor> masterList = new ArrayList<Bachelor>();

	// 创建存放学生信息的磁盘文件
	public Examinee examinee = Examinee.getInstance();
	private File file_bachelor = null;
	private File file_master = null;
	private ObjectInputStream ois = null;

	// 初始化窗体
	public ListFrame() {
		this.setTitle("考研情况记录");
		this.setBackground(Color.cyan);
		this.setLayout(null);
		this.setBounds(0, 0, 540, 500);
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
		// 初始化标签
		jL1 = new JLabel("参加考试学生名单");
		jL2 = new JLabel("拟录取学生名单");
		jL1.setFont(new Font("微软雅黑", Font.BOLD, 16));
		jL2.setFont(new Font("微软雅黑", Font.BOLD, 16));
		// 设置标签位置
		jL1.setBounds(220, 10, 150, 40);
		jL2.setBounds(220, 235, 150, 40);
		// 初始化文本框
		textAreas[0] = new JTextArea();
		textAreas[1] = new JTextArea();
		textAreas[2] = new JTextArea();
		textAreas[3] = new JTextArea();
		// 设置文本框位置
		textAreas[0].setBounds(10, 50, 510, 150);
		textAreas[1].setBounds(10, 220, 510, 8);
		textAreas[2].setBounds(10, 275, 510, 8);
		textAreas[3].setBounds(10, 300, 510, 150);

		con.add(jL1);
		con.add(jL2);
		for (JTextArea textArea : textAreas) {
			textArea.setEditable(false);
			con.add(textArea);
		}
		// 从文件中读取信息
		getStudentInfo();
		// 输出结果
		print();
	}

	private void getStudentInfo() {
		// 获取存放参加考试学生信息的文件
		file_bachelor = examinee.getBachelorsFile();
		// 创建关联存放参加考试学生文件的读取流
		try {
			if (ois == null) {
				ois = new ObjectInputStream(new FileInputStream(file_bachelor));
			}
			Object obj_bachelor = null;
			while ((obj_bachelor = ois.readObject()) != null) {
				bachelorList.add((Bachelor) obj_bachelor);
			}
			// 调用教师改卷的功能得到录取结果文件
			Teacher teacher = null;
			if (teacher == null) {
				teacher = new Teacher();
			}
			file_master = teacher.meetStandard(bachelorList);
			// 创建关联录取学生文件的读取流
			ois = new ObjectInputStream(new FileInputStream(file_master));
			Object obj_master = null;
			while ((obj_master = ois.readObject()) != null) {
				masterList.add((Bachelor) obj_master);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void print() {
		textAreas[0].setText("名字" + "     " + "性别" + "    " + "学校" + "      " + "政治" + "     " + "英语" + "        "
				+ "数学" + "      " + "专业" + "     " + "总分");
		for (Bachelor b : bachelorList) {
			textAreas[0].setText(textAreas[0].getText() + '\n' + b.getName() + "      " + b.getSex() + "       "
					+ b.getSchool() + "      " + b.getPolity() + "     " + b.getEnglish() + "        " + b.getMath()
					+ "      " + b.getProfession() + "     " + b.getTotalPoint());
		}

		textAreas[3].setText("名字" + "     " + "性别" + "    " + "学校" + "    " + "政治" + "     " + "英语" + "     " + "数学"
				+ "      " + "专业" + "    " + "总分");

		for (Bachelor m : masterList) {
			textAreas[3].setText(textAreas[3].getText() + '\n' + m.getName() + "      " + m.getSex() + "       "
					+ m.getSchool() + "     " + m.getPolity() + "     " + m.getEnglish() + "     " + m.getMath()
					+ "      " + m.getProfession() + "    " + m.getTotalPoint());
		}
	}
}
