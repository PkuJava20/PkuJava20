public class Solution {
    public int myAtoi(String str) {
        if(str.length()==0||str==null) //�Ƿ�Ϊ�մ�
		   return 0;
	   String str1=str.trim(); //ȥ���ַ������ҿո�
	   int flag=1;  //����λ
	   double result=0;//��������жϹؼ�
	   int j=0;
	   int judge=2;//���������������ŷ���0
	//   while(j<str1.length()){        //���˿�ͷ�Ĳ������ֺ�������
		   if(((str1.charAt(0)>'9')||(str1.charAt(0)<'0'))&&(str1.charAt(0)!='-')&&(str1.charAt(0)!='+'))
			   return 0;
			//   j++;
		//   else 
			//   break;
	 //  }
	  // System.out.println(j);   ����
	   for(int i=j;i<str1.length();i++){    //ת����int 
		  // System.out.println(str1.charAt(i));  
		   if(judge==0)
			   break;
		   if(str1.charAt(i)=='+'){
			   flag=1;
			   judge--;
		   }
		   else if(str1.charAt(i)=='-'){
			   flag=-1;
			   judge--;
		   }
		   else if(str1.charAt(i)<='9'&&str1.charAt(i)>='0')
			   result=result*10+Integer.parseInt(str1.charAt(i)+"");
		   else 
			   break;
	   }
	   result=flag*result;
	   //System.out.println(result);
	   if(result==0)
		   return 0;
	   if(result>Integer.MAX_VALUE)   //����Խ��
		   return Integer.MAX_VALUE;
	   if(result<Integer.MIN_VALUE)
		   return Integer.MIN_VALUE;
	   else 
		   return (int)result;
    }
}