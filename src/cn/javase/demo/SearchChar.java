package cn.javase.demo;
/*
 * ��һ�����б�дһ�������������������һ���ַ��������Ƿ����ĳ���ַ���
 * ������ڣ��򷵻�����ַ����ַ������е�һ�γ��ֵ�λ�ã���Ŵ�0��ʼ���㣩��
 * ���򣬷���-1��Ҫ�������ַ�������ַ����Բ�����ʽ���ݴ��ݸ��÷�����
 * ������������Ϊnull��Ӧ�׳�IllegalArgumentException�쳣��
 * �����main�������Ը��ֿ��ܳ��ֵ����������֤�÷�����д���Ƿ���ȷ��
 * ���磬�ַ������ڣ��ַ����ڣ����������Ϊnull�ȡ�
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
			throw new IllegalArgumentException("������Ϊ�գ��Ƿ�");
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

