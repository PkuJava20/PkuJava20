
public class removeDuplicates {
	 public int removeDuplicates1(int[] nums) {
		  int len=0;
          if(nums.length==0)   return 0;
       
	        int tmp=nums[0]-1;
	        for(int i=0;i<nums.length;i++){
	        	if(tmp!=nums[i]) { 
	        		nums[len]=nums[i];
	        		len++;
	        	}
	        	tmp=nums[i];
	        }
	        //len--;
	        return len;
	    }
     public static void main(String []agrs){
    	 int []nums={1};
    	// System.out.println("****");
    	 removeDuplicates   test=new removeDuplicates();
    	 int len=test.removeDuplicates1( nums);
    	 System.out.println(len);
     }
}
