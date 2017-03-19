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

	// �����ı���
	private JTextField[] textField = new JTextField[10];
	// ������ǩ
	private JLabel[] jL = new JLabel[8];
	// ����ȷ�Ϻ�ȡ����ť
	private JButton enterButton = null;
	private JButton cancleButton = null;

	// ��ȡ��ſ���ѧ���ļ���
	public Examinee examinee = Examinee.getInstance();
	public ArrayList<Bachelor> bachelors = (ArrayList<Bachelor>) examinee.getList();

	// ��ȡ���ѧ����Ϣ�Ĵ����ļ�
	private File file = examinee.getBachelorsFile();
	private ObjectOutputStream oos = null;

	public AddFrame() {
		this.setTitle("���ӿ�����¼��������");
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

	// ��ʼ������
	private void initFrame() {
		// �������
		Container con = this.getContentPane();
		// ��������ʼ����ǩ
		jL[0] = new JLabel("����������");
		jL[1] = new JLabel("�������Ա�");
		jL[2] = new JLabel("������רҵ");
		jL[3] = new JLabel("�������ҵԺУ");
		jL[4] = new JLabel("����");
		jL[5] = new JLabel("��ѧ");
		jL[6] = new JLabel("Ӣ��");
		jL[7] = new JLabel("רҵ�γ�");
		// ���ñ�ǩλ��
		jL[0].setBounds(50, 30, 80, 30);
		jL[1].setBounds(50, 70, 80, 30);
		jL[2].setBounds(50, 110, 80, 30);
		jL[3].setBounds(50, 150, 100, 30);
		jL[4].setBounds(30, 225, 80, 30);
		jL[5].setBounds(130, 225, 80, 30);
		jL[6].setBounds(230, 225, 80, 30);
		jL[7].setBounds(330, 225, 80, 30);
		// ��ʼ���ı���
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
		// �����ı���λ��
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
		// ��ʼ��ȷ�Ϻ�ȡ����ť
		enterButton = new JButton("ȷ��");
		enterButton.setBounds(80, 320, 100, 30);
		cancleButton = new JButton("ȡ��");
		cancleButton.setBounds(280, 320, 100, 30);
		// ������ť
		enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ȡ�ı��������
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
				// ��ʼ���¼����ѧ��
				Bachelor bachelor = new Bachelor(name, sex, major, school, totalPoint, polity, english, math,
						profession);
				// �����ѧ��������
				bachelors.add(bachelor);

				// ��ѧ��д������ļ�������
					//��ֹ�ظ�д���ļ���ͷ��Ϣheader
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
					// ���¼��������е�ѧ����Ϣд���ļ�
					for (Bachelor b : bachelors) {
						oos.writeObject(b);
						oos.flush();
					}
					// д�������־�����ȡ
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

	// ���ش���
	public void close() {
		this.setVisible(false);
	}
}
