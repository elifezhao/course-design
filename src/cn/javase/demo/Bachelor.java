package cn.javase.demo;

import java.io.Serializable;

import cn.student.demo.Student;

public class Bachelor extends Student {
	
	//ÿ������ͬѧ����ǰ�Ļ�������չ���¿�����Ϣ����ҵԺУ�������ܷ֡����Ρ�Ӣ���ѧ��רҵ�γ̵ĵ��Ƴɼ�
	private String school;			//��ҵԺУ
	private double totalPoint;     	//�����ܷ�
	private double polity;			//���γɼ�
	private double english;			//Ӣ��ɼ�
	private double math;			//��ѧ�ɼ�
	private double profession;     	//רҵ�ɼ�
	
	public Bachelor() {
       super();
	}
	
	public Bachelor(String name, String sex,String major, String school, double totalPoint, double polity,
					double english, double math, double profession) {
		super(name,sex,major);
		this.school = school;
		this.totalPoint = totalPoint;
		this.polity = polity;
		this.english = english;
		this.math = math;
		this.profession = profession;
	}

	public String getSchool() {
		return school;
	}

	public double getTotalPoint() {
		return totalPoint;
	}

	public double getPolity() {
		return polity;
	}

	public double getEnglish() {
		return english;
	}

	public double getMath() {
		return math;
	}


	public double getProfession() {
		return profession;
	}
	
}