public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        /* ����һ����ʱ������
        List<Integer> list1=new ArrayList<Integer>();//��С�������
        List<Integer> list2=new ArrayList<Integer>();//����
        List<Integer> list3=new ArrayList<Integer>();//����������
       // List<Integer> list4=new ArrayList<Integer>(){{add(0);add(0);add(0);}};//��һ�����������������
        List<List<Integer>> list5=new ArrayList<List<Integer>>();//����
        if(nums==null||nums.length==0)
        	return list5;
        for(int i=0;i<nums.length;i++){   //�洢����
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
        if(list2.size()>=3){  //���������������
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
        		if(num1+num2==0&&list2.size()>0){   //һ��һСһ��
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
        			for(int o=0;o<i;o++){  //�������С���㣬�ڴ���0������
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
        			for(int o=i+1;o<list1.size();o++){    //�������С���㣬�ڴ���0������
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
        			for(int o=0;o<j;o++){  //������Ӵ����㣬��С��0������
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
        			for(int o=j+1;o<list3.size();o++){  //������Ӵ����㣬��С��0������
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
        return new ArrayList(new HashSet(list5));//ȥ��
        //return list5;*/

        //������
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
    	for(int i=0;i<nums.length-2;i++){ //�̶�nums[i]
    		if(i-1>=0&&nums[i-1]==nums[i])   //nums[i]�ٴγ���ֱ���Թ�
    			i++;       
    		int j=i+1;  //��ָ��
    		int end=nums.length-1; //βָ��
    	    //System.out.print(j+" "+end);
    		while(j<end){  //��βָ���غϾ��ҳ�nums[i]�����к������Ϊ�������
    			if(nums[i]+nums[j]+nums[end]<0){ //��С���㣬��ָ�������Ҹ�����
    				j++;
    					while(j<end&&nums[j-1]==nums[j])  //��ͬ�����Թ�
    					j++;
    			}
    			else if(nums[i]+nums[j]+nums[end]>0){ //�ʹ����㣬βָ�������Ҹ�С��
    				end--;
    				while(j<end&&nums[end]==nums[end+1])//��ͬ�����Թ�
    					end--;
    			}
    			else{  //�ҵ���Ϊ��Ľ�
    				List<Integer> list4=new ArrayList<Integer>();
					list4.add(nums[i]);
        			list4.add(nums[j]);
        			list4.add(nums[end]);
        			list5.add(list4);
        			j++;
        			end--;
        				while(j<end&&nums[j-1]==nums[j])//��ͬ�����Թ�
    					j++;
        			while(j<end&&nums[end]==nums[end+1])//��ͬ�����Թ�
    					end--;
    			}
    		}
    	}
    	return new ArrayList(new HashSet(list5));//��SETȥ��
    }
}