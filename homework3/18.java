public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
         List<List<Integer>> list5=new ArrayList<List<Integer>>();//����
	    	if(nums==null||nums.length==0)
	         	return list5;
	      for(int p=0;p<nums.length-1;p++){ //ð������
	    		for(int k=0;k<nums.length-p-1;k++){
	    			if(nums[k]>nums[k+1]){
	    				int tmp=nums[k+1];
	    				nums[k+1]=nums[k];
	    				nums[k]=tmp;
	    			}
	    		}
	    	}
	      for(int p=0;p<nums.length-3;p++){  //�̶���һ����nums[p]
	    	  if(p-1>=0&&nums[p-1]==nums[p]&&p<nums.length-3)   //nums[p]�ٴγ���ֱ���Թ�
	    			p++;  
	    	  int target2=target-nums[p];
	    	for(int i=p+1;i<nums.length-2;i++){  //�̶��ڶ�����nums[i]
	    		 if(i-1>=p+1&&nums[i-1]==nums[i]&&i<nums.length-2)   //nums[i]�ٴγ���ֱ���Թ�
		    			i++;  
	    		int j=i+1;  //��ָ��
	    		int end=nums.length-1; //βָ��
	    	    //System.out.print(j+" "+end);
	    		while(j<end){  //��βָ���غϾ��ҳ�nums[i]�����к������Ϊtarget������
	    			if(nums[i]+nums[j]+nums[end]<target2){ //��С��target����ָ�������Ҹ�����
	    				j++;
	    				while(j<end&&nums[j]==nums[j-1])  //��ͬ�����Թ�
	    					j++;
	    			}
	    			else if(nums[i]+nums[j]+nums[end]>target2){ //�ʹ���target��βָ�������Ҹ�С��
	    				end--;
	    				while(j<end&&nums[end]==nums[end+1])//��ͬ�����Թ�
	    					end--;
	    			}
	    			else{  //�ҵ���Ϊtarget�Ľ�
	    				List<Integer> list4=new ArrayList<Integer>();
	    				list4.add(nums[p]);
						list4.add(nums[i]);
	        			list4.add(nums[j]);
	        			list4.add(nums[end]);
	        			list5.add(list4);
	        			j++;
	        			end--;
	        			while(j<end&&nums[j]==nums[j-1])//��ͬ�����Թ�
	    					j++;
	        			while(j<end&&nums[end]==nums[end+1])//��ͬ�����Թ�
	    					end--;
	    			}
	    		}
	    	}
	      }
	    	return new ArrayList(new HashSet(list5));//��SETȥ��
    }
}