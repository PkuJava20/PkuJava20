public class Solution {
    public void moveZeroes(int[] nums) {
    	int i=0,j=0;//i存储从当前位置往后第一个非零数字位置，j存储从当前位置往后第一个零的位置
          while(i<nums.length){
        	  while(nums[j]!=0&&j+1<nums.length){//找寻第一个零的位置
        		  j++;
        	  }
        	  if(nums[i]==0||i<=j)  //找寻j标明的零的位置后的第一个非零数字位置
        		  i++;
        	  if(i>=nums.length)  //数组越界处理
        		  return;
        	  if(nums[i]!=0&&i>j){ //交换零和非零数字
        		  int tmp=nums[j];
        		  nums[j]=nums[i];
        		  nums[i]=tmp;
        		  i++; //继续往后查找
        		  j++; //继续往后查找
        	  }
          }
    }
}