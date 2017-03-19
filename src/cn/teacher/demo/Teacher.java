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

	// 教师的属性简化为：姓名、职称、研究方向、所带研究生数目等.
	private String name;
	private String title;
	private String dire;
	private int num;
	private ArrayList<Bachelor> master = new ArrayList<Bachelor>();
	
	//获取存放学生信息的文件
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

		// 判断考生是否上线。上线标准是：总分达到要求，单科成绩不低于60分。上线的学生存到另外一个集合中去。
		for (Iterator<Bachelor> it = bachelor.iterator(); it.hasNext();) {
			Bachelor b = (Bachelor) it.next();
			if (b.getTotalPoint() >= TOTAL_SCORE && b.getPolity() >= SINGLE_SCORE && b.getEnglish() >= SINGLE_SCORE
					&& b.getMath() >= SINGLE_SCORE && b.getProfession() >= SINGLE_SCORE) {
				master.add(b);
			}
		}

		// 输出参加考试的学生名单
		System.out.println("以下是参加考试的学生名单");
		for (Iterator<Bachelor> it = bachelor.iterator(); it.hasNext();) {
			Bachelor b = (Bachelor) it.next();
			System.out.println(b.getName() + "    " + b.getSex() + "    " + b.getSchool());
		}

		// 输出考研上线的学生名单并输出各科考试成绩
		System.out.println("以下是考研上线的学生名单以及他们的各科成绩");
		System.out.println("名字" + "    " + "毕业院校" + "   " + "政治成绩" + "   " + "英语成绩" + "    " + "数学成绩" + "    " + "专业成绩"
				+ "" + "   " + "总分");
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
			// 写入结束标志方便读取
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
