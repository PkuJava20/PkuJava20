public class Solution {
    public int trailingZeroes(int n) {
     //  if(n==0)   return 1;
    	if(n<0)   n=-n;
    	int cnt0=0;
    	while(n>0){
    		cnt0+=n/5;
    		n/=5;
    	}
       return cnt0;
    }
}