public class Solution {
	public boolean isHappy(int n) {
	        List<Integer> list=new ArrayList(); 
	        while(true){
	        int m=getSum(n);
	        if(m==1)   //��happy number
	        	return true;
	        else{
	        	for(int j=0;j<list.size();j++){  //���ƽ�����Ƿ�����ѭ�����жϲ���happy number
	        		if(m==list.get(j))
	        			return false;
	        	}  
	        	list.add(m);//�洢ƽ����
	        	n=m;
	        }
	        }
	    }
	   public static int getSum(int n){  //���λ���͵�ƽ��
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
