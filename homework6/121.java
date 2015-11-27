public class Solution {
    public int maxProfit(int[] prices) {
   	 /*方法一用两个for循环O(n^2)复杂度结果leetcode超时*/
		 //int max=Integer.MIN_VALUE;//最大利润
	        //for(int i=0;i<prices.length-1;i++){
	        	//int profit=Integer.MIN_VALUE;
	        	//for(int j=i+1;j<prices.length;j++){
	        		//if(prices[j]-prices[i]>profit)
	        			//profit=prices[j]-prices[i];
	        	//}
	        	//if(profit>max)
	        		//max=profit;
	       // }
	        //return max;
		 
		 
		 /*方法二时间复杂度为O(n)*/
		 if(prices.length==0||prices.length==1)
			 return 0;
		 int maxprofit=Integer.MIN_VALUE;  //存储最大利润
		 int max[]=new int[prices.length];//分别用于存储prices[2..n],prices[3..n]...prices[n..n]的每个数组的最大值,如max[i]表示prices[i..n]中的最大值
		 max[prices.length-1]=prices[prices.length-1];
		 for(int i=prices.length-2;i>0;i--){  //从后向前求max[]
			 if(prices[i]>max[i+1]) //prices[i]大于prices[i+1..n]中的最大值，就用prices[i]更新prices[i..n]中的最大值
				 max[i]=prices[i];
			 else
				 max[i]=max[i+1];//否则prices[i..n]中的最大值等于prices[i+1..n]中的最大值
		 }
		 for(int i=0;i<prices.length-1;i++){ //分别求prices[i+1..n]中的最大值减去prices[i]的值
			 int profit=max[i+1]-prices[i];
			 if(profit>maxprofit)  //出现了新的最大利润就更新最大利润
				 maxprofit=profit;
		 }
		 if(maxprofit>0)
		 return maxprofit;
		 return 0;
    }
}