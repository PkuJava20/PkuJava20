import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class threeSum {
	    //从头开始遍历，并定义前后两个指针
    	 public List<List<Integer>> SavethreeSum(int[] num) {
    		    List<List<Integer>> result = new ArrayList<List<Integer>>();       
    		    Arrays.sort(num);
    		    for (int i=0; i < num.length; i++) {
    		        int start = i + 1, end = num.length-1;
    		        while (start < end) {// Two pointers
    		            int sum = num[i] + num[start] + num[end];                   
    		            if (sum == 0) {     
    		                result.add(Arrays.asList(num[i], num[start], num[end]));
    		                start++; end--;
    		                while((start < end) && num[start] == num[start-1]) start++;     //去重
    		                while((start < end) && num[end] == num[end+1]) end--;           //去重
    		            }
    		            else if (sum < 0) {
    		                start++;       
    		                while((start < end) && num[start] == num[start-1]) start++;     
    		            } else {            
    		                end--;
    		                while((start < end) && num[end] == num[end+1]) end--;                           
    		            }      
    		        }           
    		        while (i+1 < num.length && num[i+1] == num[i])                         //去重
    		            i++;            
    		    }        
    		    return result;
         }
     public static void main(String []argv){
    	 int []a={1,-1,0,0,5};
    	  threeSum three=new threeSum();
    	  List<List<Integer>>  list=three.SavethreeSum(a);
    	  System.out.println(list);
    	 
     }
}
