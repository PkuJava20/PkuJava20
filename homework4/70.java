public class Solution {
    public int climbStairs(int n) {
       //斐波那契数列的应用
	        if(n<=0)
	        return 0;
	        if(n==1)
	        	return 1;
	        if(n==2)
	        	return 2;
	        int climbStairs[]=new int[n+1];  //存储爬i阶楼梯的不同的方式
	        climbStairs[0]=0;
	        climbStairs[1]=1;//爬1阶楼梯只有一种方法
	        climbStairs[2]=2;//爬2阶楼梯只有二种方法
	        for(int i=3;i<n+1;i++) //自底向上迭代计算
	        	climbStairs[i]=climbStairs[i-1]+climbStairs[i-2];//爬第i阶楼梯的方法数等于爬第i-1阶楼梯方法数与爬第i-2阶楼梯方法数的和
	        return climbStairs[n]; //返回爬n阶楼梯的方法数
    }
}