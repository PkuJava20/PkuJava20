import java.util.Arrays;


public class containsduplicates {
	  public boolean containsDuplicate(int[] nums) {
	        Arrays.sort(nums);
		int i=0;
		for(i=1;i<nums.length;i++){
			if(nums[i]==nums[i-1])  break;
		}
	        if(i<nums.length)  return true;
	        else return false;
	    }
	  public static void main(String [] agrs){
		  containsduplicates test=new  containsduplicates();
		  int nums[]={1,1,2};
		  boolean flag=test.containsDuplicate(nums);
		  System.out.println(flag);
	  }
}
