
public class Solution {

    public int singleNumber(int[] nums) {
        if(nums==null||nums.length==0)
        	return 0;
        int result=nums[0];
        for(int i=1;i<nums.length;i++)  //所有数与的结果必然是那个singlenumber
        	result=nums[i]^result;
        return result;
    }

}
