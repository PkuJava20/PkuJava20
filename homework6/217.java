public class Solution {
    public boolean containsDuplicate(int[] nums) {
          	    /*方法一leetcode超时*/
	        //HashSet<Integer> hm=new HashSet<Integer>();//利用Hashset的自动去重检查数组中是否有重复元素
	        //if(nums.length==0||nums.length==1)
	        	//return false;
	        //for(int i=0;i<nums.length;i++)
	        	//hm.add(nums[i]);  //将数组元素全加到hashset中
	        //if(hm.size()==nums.length)  //若hashset长度和数组长度一样，表明数组没有重复元素
	        	//return false;
	        //return true;  //长度不一样，说明数组有重复元素
	        
	        /*方法二*/
		   HashMap<Integer,Integer> hm=new HashMap<Integer,Integer>();//利用hashmap判断
		   for(int i=0;i<nums.length;i++){//遍历数组
			   if(!hm.containsKey(nums[i]))//hashmap记录没有出现过的数组元素
				   hm.put(nums[i], 1);
			   else
				   return true;//否则就出现了重复元素
		   }
		   return false;//遍历结束数组都没有出现重复元素
    }
}