public class Solution {
    public boolean isPowerOfTwo(int n) {
               if(n==0) 
	        	return false;
	        int count=0;//统计二进制n中1的个数
	        while(n>0){
	        	int j=n&1;//求二进制n的最右一位是否为1
	        	count=count+j;//统计二进制n中1的个数
	        	n=n>>1;//n右移一位
	        }
	        if(count==1)//任何2的n次方的二进制表示中所有位中只有一个1
	        	return true;
	        return false;
    }
}