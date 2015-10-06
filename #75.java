public class Solution {
    public void sortColors(int[] nums) {
        int length = nums.length;
        if(length<=1)
           return;
        int i=0;
        int j=0;
        int k=0;
        for(int n=0;n<length;n++)
        {
            switch(nums[n])
            {
                case 0:
                    nums[k++]=2;
                    nums[j++]=1;
                    nums[i++]=0;
                    break;
                case 1:
                    nums[k++]=2;
                    nums[j++]=1;
                    break;
                case 2:
                    nums[k++]=2;
                    break;
                default:
                    break;
            }
        }
    }
}