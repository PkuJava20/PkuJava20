public class Solution {
    public int myAtoi(String str) {
        if(str.length()==0||str==null) //是否为空串
		   return 0;
	   String str1=str.trim(); //去除字符串左右空格
	   int flag=1;  //符号位
	   double result=0;//整数溢出判断关键
	   int j=0;
	   int judge=2;//控制连续正负符号返回0
	//   while(j<str1.length()){        //过滤开头的不是数字和正负号
		   if(((str1.charAt(0)>'9')||(str1.charAt(0)<'0'))&&(str1.charAt(0)!='-')&&(str1.charAt(0)!='+'))
			   return 0;
			//   j++;
		//   else 
			//   break;
	 //  }
	  // System.out.println(j);   调试
	   for(int i=j;i<str1.length();i++){    //转换成int 
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
	   if(result>Integer.MAX_VALUE)   //整数越界
		   return Integer.MAX_VALUE;
	   if(result<Integer.MIN_VALUE)
		   return Integer.MIN_VALUE;
	   else 
		   return (int)result;
    }
}