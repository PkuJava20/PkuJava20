public class Solution {
	public boolean isHappy(int n) {
	        List<Integer> list=new ArrayList(); 
	        while(true){
	        int m=getSum(n);
	        if(m==1)   //是happy number
	        	return true;
	        else{
	        	for(int j=0;j<list.size();j++){  //检查平方和是否陷入循环来判断不是happy number
	        		if(m==list.get(j))
	        			return false;
	        	}  
	        	list.add(m);//存储平方和
	        	n=m;
	        }
	        }
	    }
	   public static int getSum(int n){  //求各位数和的平方
		   int sum=0;
		   int m=0;
		   while(n/10!=0){
	        	m=n%10;
	        	n=n/10;
	        	sum=sum+m*m;
	        }
		   sum=sum+n*n;
		   return sum;
       }
}
