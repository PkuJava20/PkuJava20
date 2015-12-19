public class Solution {
    public int countPrimes(int n) {
                int count =0;
        if( n==1||n==2) return 0;
        for(int i =2;i<n;i++){
        	if(isPrimes(i))
        		count++;
        }
        return count;
    }
    public static boolean isPrimes(int n){
    	if(n==1) return false;
    	if(n==2||n==3||n==5) return true;
    	int divider =5;
        if (n%2==0||n%3==0||n%5==0)
            return false;
        int sqrtn=(int)Math.sqrt((double)n);
    	while(divider<=sqrtn){
            if ( n%divider==0) 
                return false;
             divider+=2;
            if ( n%divider==0) 
               return false;
            divider+=4;
    	}
    	return true;
    }
}