public class Solution {
    public void moveZeroes(int[] nums) {
        //����һ:�ܱ��İ취��ʱ�ն��ܴ�
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
        //�����������ٿռ���ʡ��һ��
        int cnt=0;
        int len=nums.length;
        for(int i=0;i<len;i++){
            if(nums[i]!=0){nums[cnt++]=nums[i];}
        }
        for(;cnt<len;cnt++)   nums[cnt]=0;
    }
     
}