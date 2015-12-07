public class Solution {
    public void moveZeroes(int[] nums) {
        int zeroIndex=0;
		int zeroCount=0;
		if(nums[0]==0){
			zeroIndex =0;
			zeroCount =1;
		}
		for(int i=1;i<nums.length;i++)
		{
			if(nums[i]==0)
			{
				if(zeroCount==0)
					zeroIndex =i;
				zeroCount++;
			}else
			{
				if(zeroCount>0){
					nums[zeroIndex]=nums[i];
					nums[i]=0;
					if(zeroCount>1)
						zeroIndex++;
					zeroCount--;
					i--;
				}
			}
		}
    }
}