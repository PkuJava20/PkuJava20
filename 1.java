import java.util.Arrays;
import java.util.Scanner;
public class two {
     public static int[] twoSum(int[] nums, int target) {
    	 int []b=new int[nums.length];
    	 for(int i=0;i<nums.length;i++)  b[i]=nums[i];
    	 Arrays.sort(nums);
    	 int index1=0,index2=nums.length-1;
    	 int []c=new int[2];
    	 while(index1<index2){
    		 if(nums[index1]+nums[index2]==target)  {
    			
    			break;
    		 }
    		 else if(nums[index1]+nums[index2]>target)  index2--;
    		 else index1++;
    	 }
    	 int key1=nums[index1];
    	 int key2=nums[index2];
    	 System.out.println(key1+"  "+key2);
    	 int k,t;
    	 for( k=0;k<nums.length;k++){
    		 if(b[k]==key1||b[k]==key2) { index1=k;
    		 System.out.println(b[0]);
    		break;}
    	 }
    	 for(t=k+1;t<nums.length;t++){
    		 if(b[t]==key1||b[t]==key2) { index2=t;
    		break;}
    	 }
    	 c[0]=index1+1;
			c[1]=index2+1;	
      // System.out.println("index1="+(index1+1)+"index2="+(index2+1));
        return c;
    }
     public static void main(String[] args) {
    	   int n;
    	    Scanner in=new Scanner(System.in);
    	    n=in.nextInt();
    	    int []a=new int[n];
    	 	for(int i=0;i<n;i++){
    	 		int t=in.nextInt();
    	 		 a[i]=t;
    	 	}
    	 	
    	 	int target=in.nextInt();
    	 	//two  tmp=new two();
    	 	twoSum(a,target);
    	 	in.close();
    	   }

}
