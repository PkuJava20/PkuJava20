public class Solution {
    public void moveZeroes(int[] nums) {
    	int i=0,j=0;//i�洢�ӵ�ǰλ�������һ����������λ�ã�j�洢�ӵ�ǰλ�������һ�����λ��
          while(i<nums.length){
        	  while(nums[j]!=0&&j+1<nums.length){//��Ѱ��һ�����λ��
        		  j++;
        	  }
        	  if(nums[i]==0||i<=j)  //��Ѱj���������λ�ú�ĵ�һ����������λ��
        		  i++;
        	  if(i>=nums.length)  //����Խ�紦��
        		  return;
        	  if(nums[i]!=0&&i>j){ //������ͷ�������
        		  int tmp=nums[j];
        		  nums[j]=nums[i];
        		  nums[i]=tmp;
        		  i++; //�����������
        		  j++; //�����������
        	  }
          }
    }
}