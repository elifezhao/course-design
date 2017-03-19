package cn.javase.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.channels.FileLockInterruptionException;
import java.util.ArrayList;

import cn.teacher.demo.Teacher;

public class Examinee {

	private static Examinee examinee = null;
	private static ArrayList<Bachelor> bachelors = new ArrayList<Bachelor>();

	// �������ѧ����Ϣ�Ĵ����ļ�
	private static File file_bachelors = new File("bachelors.txt");
	private static File file_masters = new File("master.txt");
	// ���� ObjectOutputStream����
	public static ObjectOutputStream oos = null;

	static {
		if (!file_bachelors.exists()) {
			try {
				file_bachelors.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		bachelors.add(new Bachelor("С��", "��", "�Ź�", "���", 350, 90, 90, 90, 80));
		bachelors.add(new Bachelor("С��", "Ů", "��Ժ", "���", 320, 70, 99, 91, 59));
		bachelors.add(new Bachelor("С��", "��", "����", "����", 319, 70, 95, 96, 60));
		bachelors.add(new Bachelor("С��", "Ů", "��ѧԺ", "�廪", 310, 80, 80, 80, 70));
		bachelors.add(new Bachelor("С��", "Ů", "����", "����", 355, 95, 88, 92, 80));
		bachelors.add(new Bachelor("С��", "Ů", "ʦԺ", "�ô�", 302, 85, 85, 52, 80));

		try {
			//��ѧ������д���ļ�
			for (Bachelor bachelor : bachelors) {
				if (oos == null) {
					oos = new ObjectOutputStream(new FileOutputStream(file_bachelors, true));
				}
				oos.writeObject(bachelor);
			}
			// д�������־�����ȡ
			oos.writeObject(null);
			if (oos != null) {
				oos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File getBachelorsFile() {
		return file_bachelors;
	}
	
	public static File getMastersFile() {
		return file_masters;
	}

	private Examinee() {
		super();
	}

	public static Examinee getInstance() {
		if (examinee == null) {
			examinee = new Examinee();
		}
		return examinee;
	}

	public ArrayList<Bachelor> getList() {
		return bachelors;
	}
	
	public static void main(String[] args) {
		
	}
}
