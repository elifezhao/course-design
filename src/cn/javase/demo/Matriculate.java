package cn.javase.demo;

import java.io.File;
import java.util.ArrayList;

public interface Matriculate {
	
	//定义考研要求的录取分数线和单科成绩要求的最低分数
	public static final double TOTAL_SCORE = 320; 
	public static final double SINGLE_SCORE = 60;
	
	//定义录取方法，具体的录取标准由子类自己去确定
	public abstract File meetStandard (ArrayList<Bachelor> bachelor);
}
