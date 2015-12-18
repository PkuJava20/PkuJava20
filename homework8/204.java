public class Solution {
    public int countPrimes(int n) {
    	/*方法一:依次判断小于n的数是否为素数，结果leetcode超时
    	if(n==0||n==1||n==2)
    		return 0;
    	int countPrimes=0;
    	boolean judgePrime=true;
        for(int i=2;i<n;i++){
        	for(int j=2;j<Math.sqrt(i);j++){
        		if(i%j==0){
        			judgePrime=false;
        			break;
        		}
        		judgePrime=true;
        	}
        	if(judgePrime)
        		countPrimes++;
        }
        return countPrimes;
        */
    	
    	//方法二:把小于n的合数去掉来求素数个数
    	if(n==0||n==1||n==2)
    		return 0;
    	int result=n-2;//最多有n-2个素数(1不是)
    	boolean[] judgePrime=new boolean[n];//给每个数做标记，素数是true，合数是false
    	for(int i=0;i<judgePrime.length;i++)
    		judgePrime[i]=true; //初始全记为素数
    	for(int i=2;i*i<n;i++){
    		if(judgePrime[i]){//若这个数是素数
    			for(int j=2;i*j<n;j++){
    				judgePrime[i*j]=false;//将i的整数倍的数标记为合数
    			}
    		}
    	}
    	for(int i=2;i<judgePrime.length;i++){//遍历标记数组找出合数
    		if(!judgePrime[i])
    			result--;//每有一个合数，结果个数减一
    	}
    	return result;
    }
}