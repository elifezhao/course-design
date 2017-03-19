package cn.examsystem.demo;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cn.javase.demo.Bachelor;
import cn.javase.demo.Examinee;

public class DeleteFrame extends JFrame {
	// 创建标签和相应的文本框
	private JLabel jL = null;
	private JTextField textField = null;
	// 创建确认和取消按钮
	private JButton enterButton = null;
	private JButton cancleButton = null;

	// 获取存放学生的集合
	public Examinee examinee = Examinee.getInstance();
	public ArrayList<Bachelor> bachelors = (ArrayList<Bachelor>) examinee.getList();

	// 获取存放学生信息的磁盘文件
	private File file = examinee.getBachelorsFile();
	private ObjectOutputStream oos = null;

	// 初始化窗体
	public DeleteFrame() {
		this.setTitle("学生删除界面");
		this.setBackground(Color.cyan);
		this.setLayout(null);
		this.setBounds(0, 0, 450, 300);
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
		jL = new JLabel("请输入待删除学生姓名:");
		jL.setBounds(15, 50, 200, 30);
		jL.setFont(new Font("微软雅黑", Font.BOLD, 16));
		// 初始化文本框
		textField = new JTextField();
		textField.setBounds(190, 50, 200, 30);
		// 初始化按钮
		enterButton = new JButton("确认");
		cancleButton = new JButton("取消");
		enterButton.setBounds(100, 130, 100, 30);
		cancleButton.setBounds(250, 130, 100, 30);
		// 监听按钮
		enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText().trim().toString();
				int flag = JOptionPane.showConfirmDialog(null, "是否真的要删除这条记录？", "请确认", JOptionPane.YES_NO_OPTION);
				if (flag == 0) {
					ArrayList<Bachelor> removeList = new ArrayList<Bachelor>();
					for (Bachelor bachelor : bachelors) {
						if (bachelor.getName().equals(name)) {
							removeList.add(bachelor);
						}
					}
					bachelors.removeAll(removeList);
				}
				// 当删除成功后，修改配置文件
				try {
					if (file.exists()) {
						file.delete();
						file.createNewFile();
					}
					if (oos == null) {
						oos = new ObjectOutputStream(new FileOutputStream(file, true));
					}

					// 将新集合里所有的学生信息写入文件
					for (Bachelor bachelor : bachelors) {
						oos.writeObject(bachelor);
					}
					// 写入结束标志方便读取
					oos.writeObject(null);
				} catch (IOException e1) {
					e1.printStackTrace();
				} finally {
					if(oos!=null){
						try {
							oos.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		cancleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});

		con.add(jL);
		con.add(textField);
		con.add(enterButton);
		con.add(cancleButton);
	}

	// 隐藏窗口
	public void close() {
		this.setVisible(false);
	}
}
