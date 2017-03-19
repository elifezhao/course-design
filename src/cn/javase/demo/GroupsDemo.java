package cn.javase.demo;

import java.util.Scanner;
//导入学生类和教师类所在的包
import cn.student.demo.Student;
import cn.teacher.demo.Teacher;
import cn.student.demo.*;

public class GroupsDemo {
	public static void main(String[] args) {
		
		Student[][] group = new Student[3][];
		group[0] = new Student[3];
		group[1] = new Student[2];
		group[2] = new Student[4];
		// 对每个对象进行初始化
		group[0][0] = new Student("小赵", "信工", 1, 100);
		group[0][1] = new Student("小李", "计软", 1, 100);
		group[0][2] = new Student("小王", "机电", 1, 100);
		group[1][0] = new Student("小张", "文院", 2, 90);
		group[1][1] = new Student("小黄", "传院", 2, 90);
		group[2][0] = new Student("小吴", "生科", 3, 80);
		group[2][1] = new Student("小周", "法院", 3, 80);
		group[2][2] = new Student("小郭", "光电", 3, 80);
		group[2][3] = new Student("小杨", "师范", 3, 80);
		// 输出每个小组的基本情况：
		System.out.println("通讯小组的基本情况如下:");
		Student.show(group, 0);
		System.out.println("手持小组的基本情况如下:");
		Student.show(group, 1);
		System.out.println("多媒体小组的基本情况如下:");
		Student.show(group, 2);
		
//-------------------------我是第一次实验代码和第二次实验代码的分割线--------------------------		
        //创建导包中的教师对象（仅作导包的验证）
		Teacher t =new Teacher( "老赵","教授","通讯",5);
		//动态增加多条本科生对象
		Bachelor1[] b = new Bachelor1[3];
		b[0] = new Bachelor1("小吴", "生科", 3, 80, "老吴", 1000);
		b[1] = new Bachelor1("小周", "法院", 3, 80, "老周", 2000);
		b[2] = new Bachelor1("小郭", "光电", 3, 80, "老郭", 3000);
		//动态增加多条研究生对象
		Master[] m = new Master[3];
		m[0] = new Master("小赵", "信工", 1, 100,"通讯","老赵");
		m[1] = new Master("小张", "文院",2, 90, "手持", "老张");
		m[2] = new Master("小杨", "师范",  3, 80,"多媒体", "老杨");
		
		System.out.println();
		System.out.println("提示：输入1为查询本科生指导教师姓名，输入2为查询研究生导师姓名,输入其他数值则结束查询");
		//查询对应研究生导师姓名、本科生创新项目的指导教师姓名 
		inquiry(b, m);
	}

	//查询功能 
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
	
	//每个本科同学基本情况扩展以下信息：姓名  专业  项目号  项目得分  项目申请经费  指导教师
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
    //获取本科生指导教师
	public String getTeacher() {
		return "本科生" + this.getName() + "创新项目的指导教师是:" + this.bname;
	}
}

class Master extends Bachelor1 {
	//增加属性：研究方向、导师姓名
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
    //获取研究生导师
	public String getTeacher() {
		return "研究生" + this.getName() + "的导师是:" + this.mname;
	}
}


