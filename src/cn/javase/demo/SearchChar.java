package cn.javase.demo;
/*
 * 在一个类中编写一个方法，这个方法搜索一个字符数组中是否存在某个字符，
 * 如果存在，则返回这个字符在字符数组中第一次出现的位置（序号从0开始计算），
 * 否则，返回-1。要搜索的字符数组和字符都以参数形式传递传递给该方法，
 * 如果传入的数组为null，应抛出IllegalArgumentException异常。
 * 在类的main方法中以各种可能出现的情况测试验证该方法编写得是否正确，
 * 例如，字符不存在，字符存在，传入的数组为null等。
 */
public class SearchChar
{
	public static void main(String[] args)
	{
       char[] c=new char[]{'a','b','c','f','e','f'};
       int i=search(c,'d');
       System.out.println(i);
      
       int j=search(c,'f');
       System.out.println(j);
       // search(null,'a');
        
        int x=2;
        System.out.println((x++)/3);
	}
  
	public static int search(char[] arr, char c) 
	{
		if (arr == null)
		{
			throw new IllegalArgumentException("该数组为空，非法");
		}
		for (int k = 0; k < arr.length; k++)
		{
			if (arr[k] ==c) 
			{
				return k;
			}
		}
		return -1;
	}
}

