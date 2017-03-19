package cn.examsystem.demo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class MenuFrame extends JFrame {

	private int flag;

	public MenuFrame(int flag) {
		//用户身份标记作为参数传进来
		this.flag = flag;
		this.setSize(300, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("管理员界面");
		initFrame();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	public void initFrame() {

		// 创建菜单栏
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// 创建三个菜单
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu helpMenu = new JMenu("Help");

		// 增加结果菜单项
		JMenu tcpMenu = new JMenu("TCP");

		// 给相应的菜单添加菜单项
		JMenuItem openItem = new JMenuItem("open");
		JMenuItem saveItem = new JMenuItem("save");
		JMenuItem exitItem = new JMenuItem("exit");
		JCheckBoxMenuItem addItem = new JCheckBoxMenuItem("Add");
		JCheckBoxMenuItem delItem = new JCheckBoxMenuItem("Delete");
		JCheckBoxMenuItem findItem = new JCheckBoxMenuItem("Find");
		JCheckBoxMenuItem listItem = new JCheckBoxMenuItem("List");

		// 给菜单项添加加速器（快捷键）
		addItem.setAccelerator(KeyStroke.getKeyStroke("ctrl A"));
		delItem.setAccelerator(KeyStroke.getKeyStroke("ctrl D"));
		findItem.setAccelerator(KeyStroke.getKeyStroke("ctrl F"));
		listItem.setAccelerator(KeyStroke.getKeyStroke("ctrl L"));

		// 给菜单项添加监听器
		addItem.addActionListener(new myListener());
		delItem.addActionListener(new myListener());
		findItem.addActionListener(new myListener());
		listItem.addActionListener(new myListener());

		// 将菜单项添加到相应的菜单中
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(exitItem);
		editMenu.add(addItem);
		editMenu.add(delItem);
		editMenu.add(findItem);
		editMenu.add(listItem);

		// 将菜单添加到菜单栏
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(helpMenu);

		// 创建两个菜单项
		JCheckBoxMenuItem studentItem = new JCheckBoxMenuItem("查询");
		JCheckBoxMenuItem teacherItem = new JCheckBoxMenuItem("考试结果咨询");
		tcpMenu.add(studentItem);
		tcpMenu.add(teacherItem);

		// 菜单项禁用规则
		if (flag == 0) {
			teacherItem.setEnabled(false);
		} else if (flag == 1) {
			studentItem.setEnabled(false);
		}

		// 给菜单项注册监听器
		studentItem.addActionListener(new myListener());
		teacherItem.addActionListener(new myListener());

		// 将结果菜单添加到菜单栏
		menuBar.add(tcpMenu);

		// 将菜单项添加到菜单
		tcpMenu.add(studentItem);
		tcpMenu.add(teacherItem);

		helpMenu.addMenuListener(new MenuListener() {

			@Override
			public void menuSelected(MenuEvent e) {
				JOptionPane.showConfirmDialog(null, "当前版本号为1.0", "软件版本", JOptionPane.DEFAULT_OPTION);
			}

			@Override
			public void menuDeselected(MenuEvent e) {
			}

			@Override
			public void menuCanceled(MenuEvent e) {
			}
		});

	}

	public class myListener implements ActionListener {
		// 按下菜单项后，跳转到相应的界面
		@Override
		public void actionPerformed(ActionEvent e) {
			String itemName = e.getActionCommand();
			switch (itemName) {
			case "Add":
				new AddFrame();
				break;
			case "Delete":
				new DeleteFrame();
				break;
			case "Find":
				new FindFrame();
				break;
			case "List":
				new ListFrame();
				break;
			case "查询":
				new studentResult();
				break;
			case "考试结果咨询":
				new teacherResult();
				break;
			default:
				break;
			}
		}
	}
}
