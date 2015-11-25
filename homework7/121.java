public class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length<=1) return 0;
        int profit = 0;
        int min = prices[0];
        int max = prices[0];
        for(int i=1;i<prices.length;i++)
        {
        	if(max<prices[i]){
        		profit = profit + prices[i]-max;
        		max = prices[i];
        	}
        	if(prices[i]<min)
        		min = prices[i];
        	if(prices[i]-min>profit)
        	{
        		profit= prices[i]-min;
        		max = prices[i];
        	}
        }
        return profit;
    }
}