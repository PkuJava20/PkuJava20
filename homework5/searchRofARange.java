import java.util.Scanner;
public class searchRofARange {
	 public int[] searchRange(int[] nums, int target) {
	        int i=0;int j=nums.length-1;
	        boolean flag=false;
	        int []result=new int[2];
	        int mid=0;
	        //找找看数组中是否有target这个数；
	        while(i<=j){
	        	 mid=(i+j)/2;
	        	if(nums[mid]==target){
	        		flag=true;
	        		break;
	        	}
	        	else if(nums[mid]>target){
	        		j=mid-1;
	        	}
	        	else{
	        		i=mid+1;
	        	}
	        }
	        while(flag){
	        	int first=mid;
	        	int end=mid;
	        	//如果有target这个数，往前搜索看看是不是还有等于target值得数
	        	for(int k=0;k<first;){
	        		int tmp=(k+first)/2;
	        		if(nums[tmp]==target) first=tmp;
	        		else k=tmp+1;
	        	}
	        	//如果有target这个数，往后搜索看看是不是还有等于target值得数
	        	for(int k=nums.length-1;k>end;){
	        		int tmp=(k+end+1)/2;       //加1：确保上取整
	        		if(nums[tmp]==target) end=tmp;
	        		else k=tmp-1;
	        	}
	        	result[0]=first;
	        	result[1]=end;
        		return result;
	        }
	        result[0]=-1;
        	result[1]=-1;
	           return result;
	    }
	 public static void main(String []agrs){
		 int []s={2,2};
		 int key=2;
		 int []ret=new int[2];
		 searchRofARange k=new searchRofARange();
		 ret=k.searchRange(s, key);
		 System.out.println(ret[0]+" "+ret[1]);
	 }
}
