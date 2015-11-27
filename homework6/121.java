public class Solution {
    public int maxProfit(int[] prices) {
   	 /*����һ������forѭ��O(n^2)���ӶȽ��leetcode��ʱ*/
		 //int max=Integer.MIN_VALUE;//�������
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
		 
		 
		 /*������ʱ�临�Ӷ�ΪO(n)*/
		 if(prices.length==0||prices.length==1)
			 return 0;
		 int maxprofit=Integer.MIN_VALUE;  //�洢�������
		 int max[]=new int[prices.length];//�ֱ����ڴ洢prices[2..n],prices[3..n]...prices[n..n]��ÿ����������ֵ,��max[i]��ʾprices[i..n]�е����ֵ
		 max[prices.length-1]=prices[prices.length-1];
		 for(int i=prices.length-2;i>0;i--){  //�Ӻ���ǰ��max[]
			 if(prices[i]>max[i+1]) //prices[i]����prices[i+1..n]�е����ֵ������prices[i]����prices[i..n]�е����ֵ
				 max[i]=prices[i];
			 else
				 max[i]=max[i+1];//����prices[i..n]�е����ֵ����prices[i+1..n]�е����ֵ
		 }
		 for(int i=0;i<prices.length-1;i++){ //�ֱ���prices[i+1..n]�е����ֵ��ȥprices[i]��ֵ
			 int profit=max[i+1]-prices[i];
			 if(profit>maxprofit)  //�������µ��������͸����������
				 maxprofit=profit;
		 }
		 if(maxprofit>0)
		 return maxprofit;
		 return 0;
    }
}