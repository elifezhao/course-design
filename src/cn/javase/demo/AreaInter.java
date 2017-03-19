package cn.javase.demo;

/**
 * Copyright (c) 2016 深圳大学信工电子
 * 
 * ALL right reserved
 * 
 * 作者：简生
 * 
 * 完成日期2016年4月6日
 * 
 * 建立一个图形接口，声明一个面积函数，圆形和矩形都实现这个借口，并得出两个图形的面积。
 * 注：体现面向对象的特征，对数值进行判断，用异常处理。不合法的数值要出现“这个数值是非法的"提示，
 *    不再进行运算。
 */
public class AreaInter 
{
	public static void main(String[] args)
	{
       AreaCount c=new Circle(2);
       System.out.println("对应圆的面积为："+c.area());
       AreaCount s=new Square(4,2);
       System.out.println("对应矩形的面积为："+s.area());
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
			throw new AreaFailException("这个数值是非法的");
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
			throw new AreaFailException("这个数值是非法的");
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