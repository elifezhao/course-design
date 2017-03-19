package cn.teacher.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import cn.javase.demo.Bachelor;
import cn.javase.demo.Examinee;
import cn.javase.demo.Matriculate;

public class Teacher implements Matriculate {

	// ��ʦ�����Լ�Ϊ��������ְ�ơ��о����������о�����Ŀ��.
	private String name;
	private String title;
	private String dire;
	private int num;
	private ArrayList<Bachelor> master = new ArrayList<Bachelor>();
	
	//��ȡ���ѧ����Ϣ���ļ�
	private Examinee examinee = Examinee.getInstance();
	private File file_masters = examinee.getMastersFile();
	private ObjectOutputStream oos = null;
	
	public Teacher() {
		super();
	}

	public Teacher(String name, String title, String dire, int num) {
		this.name = name;
		this.title = title;
		this.dire = dire;
		this.num = num;
	}
	
	public ArrayList<Bachelor> getMasters () {
		return master;
	}
	
	@Override
	public File meetStandard(ArrayList<Bachelor> bachelor) {

		// �жϿ����Ƿ����ߡ����߱�׼�ǣ��ִܷﵽҪ�󣬵��Ƴɼ�������60�֡����ߵ�ѧ���浽����һ��������ȥ��
		for (Iterator<Bachelor> it = bachelor.iterator(); it.hasNext();) {
			Bachelor b = (Bachelor) it.next();
			if (b.getTotalPoint() >= TOTAL_SCORE && b.getPolity() >= SINGLE_SCORE && b.getEnglish() >= SINGLE_SCORE
					&& b.getMath() >= SINGLE_SCORE && b.getProfession() >= SINGLE_SCORE) {
				master.add(b);
			}
		}

		// ����μӿ��Ե�ѧ������
		System.out.println("�����ǲμӿ��Ե�ѧ������");
		for (Iterator<Bachelor> it = bachelor.iterator(); it.hasNext();) {
			Bachelor b = (Bachelor) it.next();
			System.out.println(b.getName() + "    " + b.getSex() + "    " + b.getSchool());
		}

		// ����������ߵ�ѧ��������������ƿ��Գɼ�
		System.out.println("�����ǿ������ߵ�ѧ�������Լ����ǵĸ��Ƴɼ�");
		System.out.println("����" + "    " + "��ҵԺУ" + "   " + "���γɼ�" + "   " + "Ӣ��ɼ�" + "    " + "��ѧ�ɼ�" + "    " + "רҵ�ɼ�"
				+ "" + "   " + "�ܷ�");
		for (Bachelor b : master) {
			System.out.println(b.getName() + "  " + b.getSchool() + "    " + b.getPolity() + "   " + b.getEnglish()
					+ "   " + b.getMath() + "   " + b.getProfession() + "    " + b.getTotalPoint());
		}
		
		try{
			if(file_masters.exists()){
				file_masters.delete();
				file_masters.createNewFile();
			}
			if(oos==null){
				oos = new ObjectOutputStream(new FileOutputStream(file_masters,true));
			}
			for(Bachelor b : master) {
				oos.writeObject(b);
			}
			// д�������־�����ȡ
			oos.writeObject(null);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(oos!=null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return file_masters;
	}
}
