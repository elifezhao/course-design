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
		//�û���ݱ����Ϊ����������
		this.flag = flag;
		this.setSize(300, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("����Ա����");
		initFrame();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	public void initFrame() {

		// �����˵���
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// ���������˵�
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu helpMenu = new JMenu("Help");

		// ���ӽ���˵���
		JMenu tcpMenu = new JMenu("TCP");

		// ����Ӧ�Ĳ˵���Ӳ˵���
		JMenuItem openItem = new JMenuItem("open");
		JMenuItem saveItem = new JMenuItem("save");
		JMenuItem exitItem = new JMenuItem("exit");
		JCheckBoxMenuItem addItem = new JCheckBoxMenuItem("Add");
		JCheckBoxMenuItem delItem = new JCheckBoxMenuItem("Delete");
		JCheckBoxMenuItem findItem = new JCheckBoxMenuItem("Find");
		JCheckBoxMenuItem listItem = new JCheckBoxMenuItem("List");

		// ���˵�����Ӽ���������ݼ���
		addItem.setAccelerator(KeyStroke.getKeyStroke("ctrl A"));
		delItem.setAccelerator(KeyStroke.getKeyStroke("ctrl D"));
		findItem.setAccelerator(KeyStroke.getKeyStroke("ctrl F"));
		listItem.setAccelerator(KeyStroke.getKeyStroke("ctrl L"));

		// ���˵�����Ӽ�����
		addItem.addActionListener(new myListener());
		delItem.addActionListener(new myListener());
		findItem.addActionListener(new myListener());
		listItem.addActionListener(new myListener());

		// ���˵�����ӵ���Ӧ�Ĳ˵���
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(exitItem);
		editMenu.add(addItem);
		editMenu.add(delItem);
		editMenu.add(findItem);
		editMenu.add(listItem);

		// ���˵���ӵ��˵���
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(helpMenu);

		// ���������˵���
		JCheckBoxMenuItem studentItem = new JCheckBoxMenuItem("��ѯ");
		JCheckBoxMenuItem teacherItem = new JCheckBoxMenuItem("���Խ����ѯ");
		tcpMenu.add(studentItem);
		tcpMenu.add(teacherItem);

		// �˵�����ù���
		if (flag == 0) {
			teacherItem.setEnabled(false);
		} else if (flag == 1) {
			studentItem.setEnabled(false);
		}

		// ���˵���ע�������
		studentItem.addActionListener(new myListener());
		teacherItem.addActionListener(new myListener());

		// ������˵���ӵ��˵���
		menuBar.add(tcpMenu);

		// ���˵�����ӵ��˵�
		tcpMenu.add(studentItem);
		tcpMenu.add(teacherItem);

		helpMenu.addMenuListener(new MenuListener() {

			@Override
			public void menuSelected(MenuEvent e) {
				JOptionPane.showConfirmDialog(null, "��ǰ�汾��Ϊ1.0", "����汾", JOptionPane.DEFAULT_OPTION);
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
		// ���²˵������ת����Ӧ�Ľ���
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
			case "��ѯ":
				new studentResult();
				break;
			case "���Խ����ѯ":
				new teacherResult();
				break;
			default:
				break;
			}
		}
	}
}
