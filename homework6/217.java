public class Solution {
    public boolean containsDuplicate(int[] nums) {
          	    /*����һleetcode��ʱ*/
	        //HashSet<Integer> hm=new HashSet<Integer>();//����Hashset���Զ�ȥ�ؼ���������Ƿ����ظ�Ԫ��
	        //if(nums.length==0||nums.length==1)
	        	//return false;
	        //for(int i=0;i<nums.length;i++)
	        	//hm.add(nums[i]);  //������Ԫ��ȫ�ӵ�hashset��
	        //if(hm.size()==nums.length)  //��hashset���Ⱥ����鳤��һ������������û���ظ�Ԫ��
	        	//return false;
	        //return true;  //���Ȳ�һ����˵���������ظ�Ԫ��
	        
	        /*������*/
		   HashMap<Integer,Integer> hm=new HashMap<Integer,Integer>();//����hashmap�ж�
		   for(int i=0;i<nums.length;i++){//��������
			   if(!hm.containsKey(nums[i]))//hashmap��¼û�г��ֹ�������Ԫ��
				   hm.put(nums[i], 1);
			   else
				   return true;//����ͳ������ظ�Ԫ��
		   }
		   return false;//�����������鶼û�г����ظ�Ԫ��
    }
}