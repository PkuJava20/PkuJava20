public class Solution {
    public void rotate(int[] nums, int k) {
        if(k>=nums.length)
			k=k%nums.length;
		if(k==0)
			return;
		Reverse(nums,0,nums.length-1);;
		Reverse(nums,0,k-1);
		Reverse(nums,k,nums.length-1);
    }
	public  void Reverse(int[] nums,int low,int high)
	{
		int temp;
		while(low<high)
		{
			temp = nums[low];
			nums[low] = nums[high];
			nums[high]= temp;
			low++;
			high--;
		}
	}
}