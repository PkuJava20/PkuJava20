public class Solution {
    public int titleToNumber(String s) {
   	 if(s.equals(""))
			 return 0;
		 if(s.equals(null))
			 return 0;
		 double sum=0;// �洢���
		 char[] ss=s.toCharArray();//���ַ���ת��Ϊ�ַ�����
	        for(int i=0;i<ss.length;i++){//�����ַ�
	        	int k=ss[i]-'A'+1;//��ĳ���ַ���Ӧ����ֵ
	        	//System.out.println(k);
	        	//sum=sum+k*(26^(ss.length-i-1));//ĳ���ַ���Ӧ����ֵ��������Ȩֵ�ӵ��������
	        	//System.out.println(ss.length-i-1);
	        	sum=sum+k*Math.pow(26, ss.length-i-1);//ĳ���ַ���Ӧ����ֵ��������Ȩֵ�ӵ��������
	        }
	        return (int)sum;
    }
}