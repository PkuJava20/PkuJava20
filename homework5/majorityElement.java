public class majorityElement {
    public int majorityElement(int[] nums) {
       int  mainelem=nums[0];
        int count=1;
        //���������������к��ܳ�Ϊ��Ԫ�ص�ֵ
        for(int i=1;i<nums.length;i++){
        	if(count==0){      //���ǰi-1��������Ԫ�صĸ�����0����ô�Ͱѵ�i��������Ϊ��Ԫ��
        		count++;
        		mainelem=nums[i];
        		continue;
        	}
        	if(nums[i]==mainelem)  count++;
        	else count--;
        }
        count=0;
        for(int i=0;i<nums.length;i++){
        	if(nums[i]==mainelem) count++;
        }
        if(count>(nums.length)/2) return mainelem;
        else return -1;
    }
    public static void main(String []agrs){
    	int []test={2,2,2,3,3,3,3};
    	 majorityElement tmp=new  majorityElement();
    	 int mainelem=tmp.majorityElement(test);
    	 System.out.println(mainelem);
    }
}
