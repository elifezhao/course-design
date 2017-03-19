package cn.student.demo;

import java.io.Serializable;

public class Student implements Serializable {
		private String name;    		//����
		private String major;			//רҵ
		private String sex;				//�Ա�
		private int num;				//��Ŀ��
		private int score;				//��Ŀ�÷�
		
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
			System.out.println("����" + "	" + "רҵ" + "	" + "��Ŀ��" + "	" + "��Ŀ�÷�");
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
