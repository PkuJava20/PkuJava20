public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
   	for(int i=m-1;i>=0;i--) //��nums1��nums1[0..m-1]�Ƶ�nums1[n..m+n-1]��Ϊnums2�ĺϲ��ṩǰn����λ��
    		nums1[i+n]=nums1[i];
    	int i=0;//nums1��ָ��
    	int j=0;//nums2��ָ��
    	int t=0;//�ϲ�Ԫ�ش�ŵ�λ��
    	while(i<m&&j<n){ //�������ʱȽ�nums1��nums2���飬�ĸ�С�ĸ��ͷ���nums[t]���λ��
    		if(nums1[i+n]<=nums2[j]){
    			//System.out.println(nums1[i+n]);
    			nums1[t]=nums1[i+n];
    			i++;
    		}
    		else{
    			nums1[t]=nums2[j];
    			j++;
    		}
    		//System.out.println(nums1[t]);
    		t++;//�ṩ��һ����λ������źϲ�Ԫ��
    	}
    	while(j<n){ //nums2���黹��һ���ֽϴ��Ԫ��û�кϲ���nums1��
    		nums1[m+j]=nums2[j]; //�����ǽӵ�nums1��ĩβ
    		j++;
    	}
    }
}