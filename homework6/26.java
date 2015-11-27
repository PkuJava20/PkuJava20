public class Solution {
    public int removeDuplicates(int[] nums) {
           int numsLong=1;//记录新数组的长度
	        //int charLong=0;//暂时记录某个数组元素出现的长度，方便后一个元素前移
	        if(nums.length==0||nums.length==1)
	        	return nums.length;
	        int number=nums[0];  //暂存某个数组元素用于比较是否出现重复元素
	        //if(nums.length==0||nums.length==1)
	        	//return nums.length;
	        for(int i=1;i<nums.length;i++){
	        	if(nums[i]!=number){  //出现新的元素
	        		number=nums[i];  //更新用于比较是否出现重复元素的值
	        		nums[numsLong]=nums[i]; //去除上一个元素的所有重复
	        		numsLong++;//新数组长度加一
	        	}
	        }
	        return numsLong;
    }
}