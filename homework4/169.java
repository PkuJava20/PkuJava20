public class Solution {
    public int majorityElement(int[] nums) {
         int count=0; //��¼һ������Գ��ִ���
        int num=0;  //��¼ʹcount����0����
        for(int i=0;i<nums.length;i++){
        	if(count==0){
        		count++;
        		num=nums[i];//��ʱ��¼��Դ�������0����	
        	}
        	else if(count>0){
        		if(nums[i]==num) //����ͬcount+1
        			count++;
        		else
        			count--;  //��ͬcount-1
        	}
        }
        return num;  //�������������Դ�������0������Ȼ�ǳ��ִ�������һ�����
    }
}