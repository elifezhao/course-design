package cn.examsystem.demo;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class VersionDialog extends JDialog {
	
	public VersionDialog() {
	this.setTitle("����汾");
	this.setLayout(null);
	this.setBounds(0, 0, 300, 200);
	this.setResizable(false);
	this.setLocationRelativeTo(null);
	
	Container con = this.getContentPane();
	JLabel text = new JLabel("��ǰ�汾Ϊ1.0");
	text.setBounds(95, 50, 150, 30);
	JButton exit = new JButton("ȷ��");
	exit.setBounds(110, 100, 60, 20);
	exit.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	});
	con.add(exit);
	con.add(text);
	this.setVisible(true);
	}
}
