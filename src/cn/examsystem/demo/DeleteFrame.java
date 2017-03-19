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
	// ������ǩ����Ӧ���ı���
	private JLabel jL = null;
	private JTextField textField = null;
	// ����ȷ�Ϻ�ȡ����ť
	private JButton enterButton = null;
	private JButton cancleButton = null;

	// ��ȡ���ѧ���ļ���
	public Examinee examinee = Examinee.getInstance();
	public ArrayList<Bachelor> bachelors = (ArrayList<Bachelor>) examinee.getList();

	// ��ȡ���ѧ����Ϣ�Ĵ����ļ�
	private File file = examinee.getBachelorsFile();
	private ObjectOutputStream oos = null;

	// ��ʼ������
	public DeleteFrame() {
		this.setTitle("ѧ��ɾ������");
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
		// �������
		Container con = this.getContentPane();
		// ��ʼ����ǩ
		jL = new JLabel("�������ɾ��ѧ������:");
		jL.setBounds(15, 50, 200, 30);
		jL.setFont(new Font("΢���ź�", Font.BOLD, 16));
		// ��ʼ���ı���
		textField = new JTextField();
		textField.setBounds(190, 50, 200, 30);
		// ��ʼ����ť
		enterButton = new JButton("ȷ��");
		cancleButton = new JButton("ȡ��");
		enterButton.setBounds(100, 130, 100, 30);
		cancleButton.setBounds(250, 130, 100, 30);
		// ������ť
		enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText().trim().toString();
				int flag = JOptionPane.showConfirmDialog(null, "�Ƿ����Ҫɾ��������¼��", "��ȷ��", JOptionPane.YES_NO_OPTION);
				if (flag == 0) {
					ArrayList<Bachelor> removeList = new ArrayList<Bachelor>();
					for (Bachelor bachelor : bachelors) {
						if (bachelor.getName().equals(name)) {
							removeList.add(bachelor);
						}
					}
					bachelors.removeAll(removeList);
				}
				// ��ɾ���ɹ����޸������ļ�
				try {
					if (file.exists()) {
						file.delete();
						file.createNewFile();
					}
					if (oos == null) {
						oos = new ObjectOutputStream(new FileOutputStream(file, true));
					}

					// ���¼��������е�ѧ����Ϣд���ļ�
					for (Bachelor bachelor : bachelors) {
						oos.writeObject(bachelor);
					}
					// д�������־�����ȡ
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

	// ���ش���
	public void close() {
		this.setVisible(false);
	}
}
