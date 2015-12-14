public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        
        if(n==0)    return;
        if(m==0){
            for(int k=0;k<n;k++)   nums1[k]=nums2[k];
            return;
        }
        int i=0;
        int j=0;
        while(j<n){
            while(nums1[i]<=nums2[j]&&i<m)  i++;//这里面加一个i的判断非常必要，否则会出现数组指针越界
            
                for(int k=m;k>i;k--)   nums1[k]=nums1[k-1];
                nums1[i]=nums2[j];
                j++;
                m++;
            
        }
        
    }
}