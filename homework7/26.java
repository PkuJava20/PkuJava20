public class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0 )return 0;
		int count = 1;
		int temp=nums[0];
		for(int i =1;inums.length;i++)
		{
			if(nums[i]==temp)
				continue;
			else{
				temp=nums[i];
				nums[count] = temp;
				count++;
			}
		}
        return count;
    }
}