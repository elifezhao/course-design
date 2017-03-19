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

	// ������ǩ����ť���ı���
	private JLabel jL1 = null;
	private JLabel jL2 = null;
	private JTextField textField = null;
	private JTextArea area = null;
	// ��ȡ���ѧ���ļ���
	public Examinee examinee = Examinee.getInstance();
	public ArrayList<Bachelor> bachelors = (ArrayList<Bachelor>) examinee.getList();

	public teacherResult() {
		this.setTitle("���Խ����ѯ");
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
			// ����������
			teacherServer = new ServerSocket(10000);
			// ���տͻ���
			Socket s = teacherServer.accept();
			// ��ȡ�ͻ��˵�IO��
			in = s.getInputStream();
			out = s.getOutputStream();
			// ���տͻ��˷����Ŀ�������
			String name = null;
			byte[] buf = new byte[1024];
			int len = 0;
			len = in.read(buf);
			name = new String(buf, 0, len);
			textField.setText(textField.getText() + name);
			// ���������ҳ�������Ϣ�������ͻ���
			for (Bachelor b : bachelors) {
				if (b.getName().equals(name)) {
					String title = '\n' + "����" + "     " + "�Ա�" + "   " + "ѧУ" + "   " + "����" + "     " + "Ӣ��" + "     "
							+ "��ѧ" + "      " + "רҵ" + "    " + "�ܷ�" + '\n';
					String result = b.getName() + "      " + b.getSex() + "       " + b.getSchool() + "    "
							+ b.getPolity() + "    " + b.getEnglish() + "     " + b.getMath() + "      "
							+ b.getProfession() + "    " + b.getTotalPoint();
					String message = title + result;
					out.write(message.getBytes());
				}
			}
			area.setText(name + "ͬѧ��ѯ�ɼ�,�ѷ����ɼ���");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ��ʼ������
	private void initFrame() {
		// �������
		Container con = this.getContentPane();

		jL1 = new JLabel("��");
		jL1.setBounds(20, 20, 150, 30);
		jL1.setFont(new Font("΢���ź�", Font.BOLD, 18));

		jL2 = new JLabel("��ͨ�������:");
		jL2.setBounds(150, 20, 150, 30);
		jL2.setFont(new Font("΢���ź�", Font.BOLD, 18));

		// ��ʼ���ı���
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
