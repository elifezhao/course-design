package cn.examsystem.demo;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cn.javase.demo.Bachelor;
import cn.javase.demo.Examinee;

public class AddFrame extends JFrame {

	// 创建文本框
	private JTextField[] textField = new JTextField[10];
	// 创建标签
	private JLabel[] jL = new JLabel[8];
	// 创建确认和取消按钮
	private JButton enterButton = null;
	private JButton cancleButton = null;

	// 获取存放考研学生的集合
	public Examinee examinee = Examinee.getInstance();
	public ArrayList<Bachelor> bachelors = (ArrayList<Bachelor>) examinee.getList();

	// 获取存放学生信息的磁盘文件
	private File file = examinee.getBachelorsFile();
	private ObjectOutputStream oos = null;

	public AddFrame() {
		this.setTitle("增加考生记录操作界面");
		this.setBackground(Color.cyan);
		this.setLayout(null);
		this.setBounds(0, 0, 550, 400);
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

	// 初始化窗体
	private void initFrame() {
		// 创建面板
		Container con = this.getContentPane();
		// 创建并初始化标签
		jL[0] = new JLabel("请输入姓名");
		jL[1] = new JLabel("请输入性别");
		jL[2] = new JLabel("请输入专业");
		jL[3] = new JLabel("请输入毕业院校");
		jL[4] = new JLabel("政治");
		jL[5] = new JLabel("数学");
		jL[6] = new JLabel("英语");
		jL[7] = new JLabel("专业课程");
		// 设置标签位置
		jL[0].setBounds(50, 30, 80, 30);
		jL[1].setBounds(50, 70, 80, 30);
		jL[2].setBounds(50, 110, 80, 30);
		jL[3].setBounds(50, 150, 100, 30);
		jL[4].setBounds(30, 225, 80, 30);
		jL[5].setBounds(130, 225, 80, 30);
		jL[6].setBounds(230, 225, 80, 30);
		jL[7].setBounds(330, 225, 80, 30);
		// 初始化文本框
		textField[0] = new JTextField();
		textField[1] = new JTextField();
		textField[2] = new JTextField();
		textField[3] = new JTextField();
		textField[4] = new JTextField();
		textField[5] = new JTextField();
		textField[6] = new JTextField();
		textField[7] = new JTextField();
		textField[8] = new JTextField();
		textField[9] = new JTextField();
		// 设置文本框位置
		textField[0].setBounds(150, 30, 200, 30);
		textField[1].setBounds(150, 70, 200, 30);
		textField[2].setBounds(150, 110, 200, 30);
		textField[3].setBounds(150, 150, 200, 30);
		textField[4].setBounds(30, 260, 50, 30);
		textField[5].setBounds(130, 260, 50, 30);
		textField[6].setBounds(230, 260, 50, 30);
		textField[7].setBounds(330, 260, 50, 30);
		textField[8].setBounds(30, 200, 500, 8);
		textField[9].setBounds(30, 300, 500, 8);
		textField[8].setEditable(false);
		textField[9].setEditable(false);
		// 初始化确认和取消按钮
		enterButton = new JButton("确认");
		enterButton.setBounds(80, 320, 100, 30);
		cancleButton = new JButton("取消");
		cancleButton.setBounds(280, 320, 100, 30);
		// 监听按钮
		enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 获取文本框的内容
				String name = textField[0].getText().trim().toString();
				String sex = textField[1].getText().trim().toString();
				String major = textField[2].getText().trim().toString();
				String school = textField[3].getText().trim().toString();
				double polity = Double.parseDouble(textField[4].getText().trim().toString());
				double english = Double.parseDouble(textField[5].getText().trim().toString());
				double math = Double.parseDouble(textField[6].getText().trim().toString());
				double profession = Double.parseDouble(textField[7].getText().trim().toString());
				double totalPoint = polity + english + math + profession;
				String total = String.valueOf(totalPoint);
				// 初始化新加入的学生
				Bachelor bachelor = new Bachelor(name, sex, major, school, totalPoint, polity, english, math,
						profession);
				// 添加新学生到集合
				bachelors.add(bachelor);

				// 将学生写入磁盘文件存起来
					//防止重复写入文件的头信息header
				if (file.exists()) {
					file.delete();
					try {
						file.createNewFile();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				try {
					if (oos == null) {
						oos = new ObjectOutputStream(new FileOutputStream(file, true));
					}
					// 将新集合里所有的学生信息写入文件
					for (Bachelor b : bachelors) {
						oos.writeObject(b);
						oos.flush();
					}
					// 写入结束标志方便读取
					oos.writeObject(null);
				} catch (IOException e1) {
					e1.printStackTrace();
				}	finally {
					if(oos!=null) {
						try {
							oos.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}

				for (JTextField text : textField) {
					text.setText("");
				}
			}

		});

		cancleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});

		for (JTextField text : textField) {
			con.add(text);
		}
		for (JLabel jLabel : jL) {
			con.add(jLabel);
		}
		con.add(enterButton);
		con.add(cancleButton);
	}

	// 隐藏窗口
	public void close() {
		this.setVisible(false);
	}
}
