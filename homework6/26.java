public class Solution {
    public int removeDuplicates(int[] nums) {
           int numsLong=1;//��¼������ĳ���
	        //int charLong=0;//��ʱ��¼ĳ������Ԫ�س��ֵĳ��ȣ������һ��Ԫ��ǰ��
	        if(nums.length==0||nums.length==1)
	        	return nums.length;
	        int number=nums[0];  //�ݴ�ĳ������Ԫ�����ڱȽ��Ƿ�����ظ�Ԫ��
	        //if(nums.length==0||nums.length==1)
	        	//return nums.length;
	        for(int i=1;i<nums.length;i++){
	        	if(nums[i]!=number){  //�����µ�Ԫ��
	        		number=nums[i];  //�������ڱȽ��Ƿ�����ظ�Ԫ�ص�ֵ
	        		nums[numsLong]=nums[i]; //ȥ����һ��Ԫ�ص������ظ�
	        		numsLong++;//�����鳤�ȼ�һ
	        	}
	        }
	        return numsLong;
    }
}