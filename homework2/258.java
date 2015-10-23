
public class Solution {
    public int addDigits(int num) {
        while(num>=10){           //大于1位数就继续各位相加的过程
        	num=getsum(num);
        }
        return num;      //返回结果
    }
    public static int getsum(int num){  //求整数各位相加的和
    	int m,result=0;
    	while(num!=0){
    		m=num%10;
    		num=num/10;
    		result=result+m;
    	}
    	return result;
    }
 /* public int addDigits(int num){
	       return (num-1)%9+1;       //经过对多个输入输出的研究发现了输出满足这个规律，O(1)复杂度的算法
     }*/
}
