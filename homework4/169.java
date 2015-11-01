public class Solution {
    public int majorityElement(int[] nums) {
         int count=0; //记录一个数相对出现次数
        int num=0;  //记录使count大于0的数
        for(int i=0;i<nums.length;i++){
        	if(count==0){
        		count++;
        		num=nums[i];//暂时记录相对次数大于0的数	
        	}
        	else if(count>0){
        		if(nums[i]==num) //数相同count+1
        			count++;
        		else
        			count--;  //不同count-1
        	}
        }
        return num;  //遍历完数组后相对次数大于0的数必然是出现次数大于一半的数
    }
}