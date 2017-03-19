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

	// ������ǩ����ť���ı���
	private JLabel jL = null;
	private JButton enterButton = null;
	private JTextField textField1 = null;
	private JTextArea area = null;
	// ��ȡ���ѧ���ļ���
	public Examinee examinee = Examinee.getInstance();
	public ArrayList<Bachelor> bachelors = (ArrayList<Bachelor>) examinee.getList();

	// ��ʼ������
	public FindFrame() {
		this.setTitle("ѧ����Ϣ��ѯ");
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
		// �������
		Container con = this.getContentPane();
		jL = new JLabel("�������ѯ������");
		jL.setBounds(20, 20, 150, 30);
		jL.setFont(new Font("΢���ź�", Font.BOLD, 16));
		// ��ʼ���ı���
		textField1 = new JTextField();
		textField1.setBounds(150, 20, 200, 30);
		area = new JTextArea();
		area.setBounds(10, 60, 470, 200);
		// ��ʼ����ť
		enterButton = new JButton("ȷ��");
		enterButton.setBounds(380, 20, 60, 30);
		// ������ť
		enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = textField1.getText().trim().toString();
				for (Bachelor b : bachelors) {
					if (b.getName().equals(name)) {
						area.setText("����" + "     " + "�Ա�" + "   " + "ѧУ" + "   " + "����" + "     " + "Ӣ��" + "     "
								+ "��ѧ" + "      " + "רҵ" + "    " + "�ܷ�");
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
