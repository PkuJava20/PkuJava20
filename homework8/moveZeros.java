public class Solution {
    public void moveZeroes(int[] nums) {
        //方法一:很笨的办法：时空都很大
        /*
        int len=nums.length;
        int[] res=new int[len];
        int j=0;
        for(int i=0;i<len;i++){
            if(nums[i]!=0)   res[j++]=nums[i];
        }
        for(int i=0;i<len;i++)
        nums[i]=res[i];
        */
        //方法二：至少空间上省了一点
        int cnt=0;
        int len=nums.length;
        for(int i=0;i<len;i++){
            if(nums[i]!=0){nums[cnt++]=nums[i];}
        }
        for(;cnt<len;cnt++)   nums[cnt]=0;
    }
     
}