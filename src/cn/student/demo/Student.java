package cn.student.demo;

import java.io.Serializable;

public class Student implements Serializable {
		private String name;    		//姓名
		private String major;			//专业
		private String sex;				//性别
		private int num;				//项目号
		private int score;				//项目得分
		
		public Student() {
			
		}
		
		
		public Student(String name, String major, int num, int score) {
			this.name=name;;
			this.major = major;
			this.num = num;
			this.score = score;
		}


		public Student(String name, String sex,String major) {
			this.name=name;
			this.sex = sex;
			this.major = major;
		}

		public static void show(Student[][] arr, int i) {
			System.out.println("名字" + "	" + "专业" + "	" + "项目号" + "	" + "项目得分");
			for (int j = 0; j < arr[i].length; j++) {
				System.out.println(arr[i][j].getName() + "	" + arr[i][j].major + "	" + arr[i][j].num 
									+ "	" + arr[i][j].score);
			}
		}

		public String getName() {
			return name;
		}
		
		public String getSex() {
			return sex;
		}
}
