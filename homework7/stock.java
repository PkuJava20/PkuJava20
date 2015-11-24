
public class stock {
	public int maxProfit(int[] prices) {
        if(prices.length==0)   return 0;
        int max=0;
        int min=prices[0];
        for(int i=0;i<prices.length;i++)
        {
            min=min<prices[i]?min:prices[i];
            int tmp=prices[i]-min;
            max=max<tmp?tmp:max;
        }
        return max;
    }
	public static void main(String [] agrs){
		stock  test=new stock ();
		int[] nums={1};
		int max=test.maxProfit(nums);
		System.out.println(max);
	}
}
