import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class foursum {
	//���15�⣬��������Ƕһ��ѭ����ʱ�临�Ӷ�O��n3��
	 public List<List<Integer>> fourSum(int[] num,int target) {
		    List<List<Integer>> result = new ArrayList<List<Integer>>();       
		    Arrays.sort(num);
		    for (int k=0; k < num.length; k++) {
		    	for(int i=k+1;i<num.length;i++){
		        int start = i + 1, end = num.length-1;
		        int key=target-num[k];
		        while (start < end) {// Two pointers
		            int sum = num[i] + num[start] + num[end];                   
		            if (sum == key) {     
		                result.add(Arrays.asList(num[k],num[i], num[start], num[end]));
		                start++; end--;
		                while((start < end) && num[start] == num[start-1]) start++;     //ȥ��
		                while((start < end) && num[end] == num[end+1]) end--;           //ȥ��
		            }
		            else if (sum < key) {
		                start++;       
		                while((start < end) && num[start] == num[start-1]) start++;     
		            } else {            
		                end--;
		                while((start < end) && num[end] == num[end+1]) end--;                           
		            }      
		        }    
		        while (i+1 < num.length && num[i+1] == num[i])                         //ȥ��
		            i++;   
		    	}
		        while (k+1 < num.length && num[k+1] == num[k])                         //ȥ��
		            k++;            
		    }        
		    return result;
    }
	 public static void main(String []argv){
    	 int []a={1,-1,0,0,5};
    	 Scanner in=new Scanner(System.in);
    	 int key=in.nextInt();
    	  foursum four=new foursum();
    	  List<List<Integer>>  list=four.fourSum(a,key);
    	  System.out.println(list);
    	 
     }
}
