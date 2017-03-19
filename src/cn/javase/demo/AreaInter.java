package cn.javase.demo;

/**
 * Copyright (c) 2016 ���ڴ�ѧ�Ź�����
 * 
 * ALL right reserved
 * 
 * ���ߣ�����
 * 
 * �������2016��4��6��
 * 
 * ����һ��ͼ�νӿڣ�����һ�����������Բ�κ;��ζ�ʵ�������ڣ����ó�����ͼ�ε������
 * ע������������������������ֵ�����жϣ����쳣�������Ϸ�����ֵҪ���֡������ֵ�ǷǷ���"��ʾ��
 *    ���ٽ������㡣
 */
public class AreaInter 
{
	public static void main(String[] args)
	{
       AreaCount c=new Circle(2);
       System.out.println("��ӦԲ�����Ϊ��"+c.area());
       AreaCount s=new Square(4,2);
       System.out.println("��Ӧ���ε����Ϊ��"+s.area());
	}
}

interface AreaCount 
{
	public abstract double area();
}

class Circle implements AreaCount
{
    private	double radius;
    private static final double PI=3.14;
    Circle (double radius)
    {
    	this.radius=radius;
    }
	public double area()
	{
		if(radius<0)
		{
			throw new AreaFailException("�����ֵ�ǷǷ���");
		}
		return  PI*radius*radius;
	}
}

class Square implements AreaCount
{
	double  length,width;
	Square(double length,double width)
	{
		this.length=length;
		this.width=width;
	}
	public double area()
	{
		if(length<0||width<0)
		{
			throw new AreaFailException("�����ֵ�ǷǷ���");
		}
		return 	length*width;		
	}
}


class AreaFailException extends RuntimeException
{
	AreaFailException()
	{
		super();
	}
	AreaFailException(String message)
	{
		super(message);
	}
}