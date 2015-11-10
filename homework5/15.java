public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        /* 方法一超过时间限制
        List<Integer> list1=new ArrayList<Integer>();//存小于零的数
        List<Integer> list2=new ArrayList<Integer>();//存零
        List<Integer> list3=new ArrayList<Integer>();//存大于零的数
       // List<Integer> list4=new ArrayList<Integer>(){{add(0);add(0);add(0);}};//存一组符合条件的三个数
        List<List<Integer>> list5=new ArrayList<List<Integer>>();//存结果
        if(nums==null||nums.length==0)
        	return list5;
        for(int i=0;i<nums.length;i++){   //存储数据
        	if(nums[i]<0)
        		list1.add(nums[i]);
        	if(nums[i]==0)
        		list2.add(nums[i]);
        	if(nums[i]>0)
        		list3.add(nums[i]);
        }
       // System.out.println(list1.size());
        //System.out.println(list2.size());
       // System.out.println(list3.size());
        if(list2.size()>=3){  //三个及以上零情况
        	List<Integer> list4=new ArrayList<Integer>();
        	list4.add(0);
			list4.add(0);
			list4.add(0);
        	list5.add(list4);
        }
        for(int i=0;i<list1.size();i++){
        	for(int j=0;j<list3.size();j++){
        		int num1=list1.get(i);
            	int num2=list3.get(j);
            	//System.out.println(num1+" "+num2);
        		if(num1+num2==0&&list2.size()>0){   //一大一小一零
        			List<Integer> list4=new ArrayList<Integer>();
        			//System.out.println(num1+" "+num2);
        			list4.add(num1);
        			list4.add(0);
        			list4.add(num2);
        			//System.out.print(list4.get(0)+" "+list4.get(1)+" "+list4.get(2));
        			list5.add(list4);
        			//System.out.println("sb");
        		        		 //System.out.print(list5.get(0).get(0)+list5.get(0).get(1)+list5.get(0).get(2));
        			}
        		
        		if(num1+num2>0){   
        			for(int o=0;o<i;o++){  //两数相加小于零，在大于0集合找
        				if(list1.get(i)+num1+num2==0){
        					if(list1.get(o)>num1){
        				    List<Integer> list4=new ArrayList<Integer>();
        					list4.add(num1);
                			list4.add(list1.get(o));
                			list4.add(num2);
                			list5.add(list4);
        					}
        				else{
        					List<Integer> list4=new ArrayList<Integer>();
        					list4.add(list1.get(o));
                			list4.add(num1);
                			list4.add(num2);
                			list5.add(list4);
        				}
        			}	
        			}
        			for(int o=i+1;o<list1.size();o++){    //两数相加小于零，在大于0集合找
        				if(list1.get(o)+num1+num2==0){
        					if(list1.get(o)>num1){
        					List<Integer> list4=new ArrayList<Integer>();
        					list4.add(num1);
                			list4.add(list1.get(o));
                			list4.add(num2);
                			list5.add(list4);
        				}		
        				else{
        					List<Integer> list4=new ArrayList<Integer>();
        					list4.add(list1.get(o));
                			list4.add(num1);
                			list4.add(num2);
                			list5.add(list4);
        				}
        			}		
        		}
        		}
        		if(num1+num2<0){
        			for(int o=0;o<j;o++){  //两数相加大于零，在小于0集合找
        				if(list3.get(o)+num1+num2==0){
        					if(list3.get(o)<num2){
        					List<Integer> list4=new ArrayList<Integer>();
        					list4.add(num1);
                			list4.add(list3.get(o));
                			list4.add(num2);
                			list5.add(list4);
        				}
        				
        				else{
        					List<Integer> list4=new ArrayList<Integer>();
        					list4.add(num1);
                			list4.add(num2);
                			list4.add(list3.get(o));
                			list5.add(list4);
        				}
        			}	
        			}
        			for(int o=j+1;o<list3.size();o++){  //两数相加大于零，在小于0集合找
        				if(list3.get(o)+num1+num2==0){
        					if(list3.get(o)<num2){
        						List<Integer> list4=new ArrayList<Integer>();
            					list4.add(num1);
                    			list4.add(list3.get(o));
                    			list4.add(num2);
                    			list5.add(list4);
            				}
        				
            				else{
            					List<Integer> list4=new ArrayList<Integer>();
            					list4.add(num1);
                    			list4.add(num2);
                    			list4.add(list3.get(o));
                    			list5.add(list4);
            				}
            			}		
        			}
        		}
        	}
        }
        return new ArrayList(new HashSet(list5));//去重
        //return list5;*/

        //方法二
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
    	for(int i=0;i<nums.length-2;i++){ //固定nums[i]
    		if(i-1>=0&&nums[i-1]==nums[i])   //nums[i]再次出现直接略过
    			i++;       
    		int j=i+1;  //首指针
    		int end=nums.length-1; //尾指针
    	    //System.out.print(j+" "+end);
    		while(j<end){  //首尾指针重合就找出nums[i]的所有和它相加为零的数对
    			if(nums[i]+nums[j]+nums[end]<0){ //和小于零，首指针右移找更大数
    				j++;
    					while(j<end&&nums[j-1]==nums[j])  //相同的数略过
    					j++;
    			}
    			else if(nums[i]+nums[j]+nums[end]>0){ //和大于零，尾指针左移找更小数
    				end--;
    				while(j<end&&nums[end]==nums[end+1])//相同的数略过
    					end--;
    			}
    			else{  //找到和为零的解
    				List<Integer> list4=new ArrayList<Integer>();
					list4.add(nums[i]);
        			list4.add(nums[j]);
        			list4.add(nums[end]);
        			list5.add(list4);
        			j++;
        			end--;
        				while(j<end&&nums[j-1]==nums[j])//相同的数略过
    					j++;
        			while(j<end&&nums[end]==nums[end+1])//相同的数略过
    					end--;
    			}
    		}
    	}
    	return new ArrayList(new HashSet(list5));//用SET去重
    }
}