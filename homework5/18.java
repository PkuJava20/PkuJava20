public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
         List<List<Integer>> list5=new ArrayList<List<Integer>>();//存结果
	    	if(nums==null||nums.length==0)
	         	return list5;
	      for(int p=0;p<nums.length-1;p++){ //冒泡排序
	    		for(int k=0;k<nums.length-p-1;k++){
	    			if(nums[k]>nums[k+1]){
	    				int tmp=nums[k+1];
	    				nums[k+1]=nums[k];
	    				nums[k]=tmp;
	    			}
	    		}
	    	}
	      for(int p=0;p<nums.length-3;p++){  //固定第一个数nums[p]
	    	  if(p-1>=0&&nums[p-1]==nums[p]&&p<nums.length-3)   //nums[p]再次出现直接略过
	    			p++;  
	    	  int target2=target-nums[p];
	    	for(int i=p+1;i<nums.length-2;i++){  //固定第二个数nums[i]
	    		 if(i-1>=p+1&&nums[i-1]==nums[i]&&i<nums.length-2)   //nums[i]再次出现直接略过
		    			i++;  
	    		int j=i+1;  //首指针
	    		int end=nums.length-1; //尾指针
	    	    //System.out.print(j+" "+end);
	    		while(j<end){  //首尾指针重合就找出nums[i]的所有和它相加为target的数对
	    			if(nums[i]+nums[j]+nums[end]<target2){ //和小于target，首指针右移找更大数
	    				j++;
	    				while(j<end&&nums[j]==nums[j-1])  //相同的数略过
	    					j++;
	    			}
	    			else if(nums[i]+nums[j]+nums[end]>target2){ //和大于target，尾指针左移找更小数
	    				end--;
	    				while(j<end&&nums[end]==nums[end+1])//相同的数略过
	    					end--;
	    			}
	    			else{  //找到和为target的解
	    				List<Integer> list4=new ArrayList<Integer>();
	    				list4.add(nums[p]);
						list4.add(nums[i]);
	        			list4.add(nums[j]);
	        			list4.add(nums[end]);
	        			list5.add(list4);
	        			j++;
	        			end--;
	        			while(j<end&&nums[j]==nums[j-1])//相同的数略过
	    					j++;
	        			while(j<end&&nums[end]==nums[end+1])//相同的数略过
	    					end--;
	    			}
	    		}
	    	}
	      }
	    	return new ArrayList(new HashSet(list5));//用SET去重
    }
}