package cn.javase.demo;

import java.util.Scanner;
//����ѧ����ͽ�ʦ�����ڵİ�
import cn.student.demo.Student;
import cn.teacher.demo.Teacher;
import cn.student.demo.*;

public class GroupsDemo {
	public static void main(String[] args) {
		
		Student[][] group = new Student[3][];
		group[0] = new Student[3];
		group[1] = new Student[2];
		group[2] = new Student[4];
		// ��ÿ��������г�ʼ��
		group[0][0] = new Student("С��", "�Ź�", 1, 100);
		group[0][1] = new Student("С��", "����", 1, 100);
		group[0][2] = new Student("С��", "����", 1, 100);
		group[1][0] = new Student("С��", "��Ժ", 2, 90);
		group[1][1] = new Student("С��", "��Ժ", 2, 90);
		group[2][0] = new Student("С��", "����", 3, 80);
		group[2][1] = new Student("С��", "��Ժ", 3, 80);
		group[2][2] = new Student("С��", "���", 3, 80);
		group[2][3] = new Student("С��", "ʦ��", 3, 80);
		// ���ÿ��С��Ļ��������
		System.out.println("ͨѶС��Ļ����������:");
		Student.show(group, 0);
		System.out.println("�ֳ�С��Ļ����������:");
		Student.show(group, 1);
		System.out.println("��ý��С��Ļ����������:");
		Student.show(group, 2);
		
//-------------------------���ǵ�һ��ʵ�����͵ڶ���ʵ�����ķָ���--------------------------		
        //���������еĽ�ʦ���󣨽�����������֤��
		Teacher t =new Teacher( "����","����","ͨѶ",5);
		//��̬���Ӷ�������������
		Bachelor1[] b = new Bachelor1[3];
		b[0] = new Bachelor1("С��", "����", 3, 80, "����", 1000);
		b[1] = new Bachelor1("С��", "��Ժ", 3, 80, "����", 2000);
		b[2] = new Bachelor1("С��", "���", 3, 80, "�Ϲ�", 3000);
		//��̬���Ӷ����о�������
		Master[] m = new Master[3];
		m[0] = new Master("С��", "�Ź�", 1, 100,"ͨѶ","����");
		m[1] = new Master("С��", "��Ժ",2, 90, "�ֳ�", "����");
		m[2] = new Master("С��", "ʦ��",  3, 80,"��ý��", "����");
		
		System.out.println();
		System.out.println("��ʾ������1Ϊ��ѯ������ָ����ʦ����������2Ϊ��ѯ�о�����ʦ����,����������ֵ�������ѯ");
		//��ѯ��Ӧ�о�����ʦ������������������Ŀ��ָ����ʦ���� 
		inquiry(b, m);
	}

	//��ѯ���� 
	private static void inquiry(Bachelor1[] b, Master[] m) {
		Scanner in = new Scanner(System.in);
		for (int j = in.nextInt(); j == 1 || j == 2; j = in.nextInt()) {
			if (j == 1) {
				for (int i = 0; i < b.length; i++) {
					System.out.println(b[i].getTeacher());
				}
			} else if (j == 2) {
				for (int i = 0; i < m.length; i++) {
					System.out.println(m[i].getTeacher());
				}
			} else 
				return;
		}
	}

	
	

}

class Bachelor1 extends Student {
	
	//ÿ������ͬѧ���������չ������Ϣ������  רҵ  ��Ŀ��  ��Ŀ�÷�  ��Ŀ���뾭��  ָ����ʦ
	private String bname;
	private double money;
	Bachelor1() {
       super();
	}
	
	Bachelor1(String name, String major, int num, int score) {
        super(name,major,num,score);
	}

	Bachelor1(String name, String major, int num, int score, String bname, double money) {
		super(name, major, num, score);
		this.bname = bname;
		this.money = money;
	}
    //��ȡ������ָ����ʦ
	public String getTeacher() {
		return "������" + this.getName() + "������Ŀ��ָ����ʦ��:" + this.bname;
	}
}

class Master extends Bachelor1 {
	//�������ԣ��о����򡢵�ʦ����
	private String dire;
	private String mname;
	Master() {
		super();
	}
	
	Master(String name, String major, int num, int score, String dire, String mname) {
		super(name,major,num,score);
		this.dire = dire;
		this.mname = mname;
	}
    //��ȡ�о�����ʦ
	public String getTeacher() {
		return "�о���" + this.getName() + "�ĵ�ʦ��:" + this.mname;
	}
}


