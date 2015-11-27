public class Solution {
    public int titleToNumber(String s) {
   	 if(s.equals(""))
			 return 0;
		 if(s.equals(null))
			 return 0;
		 double sum=0;// 存储结果
		 char[] ss=s.toCharArray();//将字符串转换为字符数组
	        for(int i=0;i<ss.length;i++){//遍历字符
	        	int k=ss[i]-'A'+1;//求某个字符对应的数值
	        	//System.out.println(k);
	        	//sum=sum+k*(26^(ss.length-i-1));//某个字符对应的数值乘以所在权值加到最后结果中
	        	//System.out.println(ss.length-i-1);
	        	sum=sum+k*Math.pow(26, ss.length-i-1);//某个字符对应的数值乘以所在权值加到最后结果中
	        }
	        return (int)sum;
    }
}