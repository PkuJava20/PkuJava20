public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int count=0;
        int j=0;
        int index =0;
        if(m==0) {
            for(int i=0;i<n;i++) {
                nums1[index] = nums2[i];
    		    index++;
            }
    	    return;
        }
        if(n==0) return;
        for(int i=0;i<n;i++){
            if(j<m){
            	if(nums2[i]>=nums1[index])
            	{
            		j++;
            		index++;i--;
            	}else
            	{
            		for(int num =m+count;num>index;num--)
            			nums1[num] =nums1[num-1];
            		nums1[index]=nums2[i];
            		index++;
            		count++;
            	}
            }
            else{
        		nums1[index]=nums2[i];
        		index++;
            }
        }
    }
}