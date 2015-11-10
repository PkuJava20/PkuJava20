public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
    	List<Integer> list = new ArrayList<Integer>();
    	if(nums.length<3)
    	    return result;
    	Arrays.sort(nums);
    	int low =0;
    	int high = 0;
    	for(int i=0;i<nums.length;i++)
    	{
    		if(i>0)
    		{
    			if(nums[i]==nums[i-1])
    				continue;
    		}
    	    low =i+1;
    	    high = nums.length-1;
    		while(low<high)
    		{
    		    int sum =nums[low]+nums[i]+nums[high];
    		    if(sum==0)
    		    {
    			    list.add(nums[i]);
    			    list.add(nums[low]);
    			    list.add(nums[high]); 
    			    result.add(list);
    			    list= new ArrayList<Integer>();
    		        low++;
    		        while(low<high&&nums[low]==nums[low-1]) 
    		            low++;
    		        high--;
    		        while(low<high&&nums[high]==nums[high+1]) 
    		            high--;
    		    }
    		    if(sum<0){
    		        low++;
    		    }
    		    if(sum>0){
    		        high--;
    		    }
    		}
    	}
    	return result;
    }
   
}