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

	// ������ǩ
	private JLabel jL1 = null;
	private JLabel jL2 = null;
	// �����ı���
	private JTextArea[] textAreas = new JTextArea[4];

	// �������ѧ���ļ���
	public ArrayList<Bachelor> bachelorList = new ArrayList<Bachelor>();
	public ArrayList<Bachelor> masterList = new ArrayList<Bachelor>();

	// �������ѧ����Ϣ�Ĵ����ļ�
	public Examinee examinee = Examinee.getInstance();
	private File file_bachelor = null;
	private File file_master = null;
	private ObjectInputStream ois = null;

	// ��ʼ������
	public ListFrame() {
		this.setTitle("���������¼");
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
		// �������
		Container con = this.getContentPane();
		// ��ʼ����ǩ
		jL1 = new JLabel("�μӿ���ѧ������");
		jL2 = new JLabel("��¼ȡѧ������");
		jL1.setFont(new Font("΢���ź�", Font.BOLD, 16));
		jL2.setFont(new Font("΢���ź�", Font.BOLD, 16));
		// ���ñ�ǩλ��
		jL1.setBounds(220, 10, 150, 40);
		jL2.setBounds(220, 235, 150, 40);
		// ��ʼ���ı���
		textAreas[0] = new JTextArea();
		textAreas[1] = new JTextArea();
		textAreas[2] = new JTextArea();
		textAreas[3] = new JTextArea();
		// �����ı���λ��
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
		// ���ļ��ж�ȡ��Ϣ
		getStudentInfo();
		// ������
		print();
	}

	private void getStudentInfo() {
		// ��ȡ��Ųμӿ���ѧ����Ϣ���ļ�
		file_bachelor = examinee.getBachelorsFile();
		// ����������Ųμӿ���ѧ���ļ��Ķ�ȡ��
		try {
			if (ois == null) {
				ois = new ObjectInputStream(new FileInputStream(file_bachelor));
			}
			Object obj_bachelor = null;
			while ((obj_bachelor = ois.readObject()) != null) {
				bachelorList.add((Bachelor) obj_bachelor);
			}
			// ���ý�ʦ�ľ�Ĺ��ܵõ�¼ȡ����ļ�
			Teacher teacher = null;
			if (teacher == null) {
				teacher = new Teacher();
			}
			file_master = teacher.meetStandard(bachelorList);
			// ��������¼ȡѧ���ļ��Ķ�ȡ��
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
		textAreas[0].setText("����" + "     " + "�Ա�" + "    " + "ѧУ" + "      " + "����" + "     " + "Ӣ��" + "        "
				+ "��ѧ" + "      " + "רҵ" + "     " + "�ܷ�");
		for (Bachelor b : bachelorList) {
			textAreas[0].setText(textAreas[0].getText() + '\n' + b.getName() + "      " + b.getSex() + "       "
					+ b.getSchool() + "      " + b.getPolity() + "     " + b.getEnglish() + "        " + b.getMath()
					+ "      " + b.getProfession() + "     " + b.getTotalPoint());
		}

		textAreas[3].setText("����" + "     " + "�Ա�" + "    " + "ѧУ" + "    " + "����" + "     " + "Ӣ��" + "     " + "��ѧ"
				+ "      " + "רҵ" + "    " + "�ܷ�");

		for (Bachelor m : masterList) {
			textAreas[3].setText(textAreas[3].getText() + '\n' + m.getName() + "      " + m.getSex() + "       "
					+ m.getSchool() + "     " + m.getPolity() + "     " + m.getEnglish() + "     " + m.getMath()
					+ "      " + m.getProfession() + "    " + m.getTotalPoint());
		}
	}
}
