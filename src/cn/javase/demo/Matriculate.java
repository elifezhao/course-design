package cn.javase.demo;

import java.io.File;
import java.util.ArrayList;

public interface Matriculate {
	
	//���忼��Ҫ���¼ȡ�����ߺ͵��Ƴɼ�Ҫ�����ͷ���
	public static final double TOTAL_SCORE = 320; 
	public static final double SINGLE_SCORE = 60;
	
	//����¼ȡ�����������¼ȡ��׼�������Լ�ȥȷ��
	public abstract File meetStandard (ArrayList<Bachelor> bachelor);
}
