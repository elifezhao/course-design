package cn.javase.demo;

import java.io.Serializable;

import cn.student.demo.Student;

public class Bachelor extends Student {
	
	//每个本科同学在以前的基础上扩展以下考研信息：毕业院校、考研总分、政治、英语、数学、专业课程的单科成绩
	private String school;			//毕业院校
	private double totalPoint;     	//考研总分
	private double polity;			//政治成绩
	private double english;			//英语成绩
	private double math;			//数学成绩
	private double profession;     	//专业成绩
	
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