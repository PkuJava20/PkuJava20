public class majorityElement {
    public int majorityElement(int[] nums) {
       int  mainelem=nums[0];
        int count=1;
        //遍历出整个数组中很能成为主元素的值
        for(int i=1;i<nums.length;i++){
        	if(count==0){      //如果前i-1个数中主元素的个数是0，那么就把第i个数暂视为主元素
        		count++;
        		mainelem=nums[i];
        		continue;
        	}
        	if(nums[i]==mainelem)  count++;
        	else count--;
        }
        count=0;
        for(int i=0;i<nums.length;i++){
        	if(nums[i]==mainelem) count++;
        }
        if(count>(nums.length)/2) return mainelem;
        else return -1;
    }
    public static void main(String []agrs){
    	int []test={2,2,2,3,3,3,3};
    	 majorityElement tmp=new  majorityElement();
    	 int mainelem=tmp.majorityElement(test);
    	 System.out.println(mainelem);
    }
}
