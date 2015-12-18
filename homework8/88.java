public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
   	for(int i=m-1;i>=0;i--) //把nums1中nums1[0..m-1]移到nums1[n..m+n-1]，为nums2的合并提供前n个空位置
    		nums1[i+n]=nums1[i];
    	int i=0;//nums1的指针
    	int j=0;//nums2的指针
    	int t=0;//合并元素存放的位置
    	while(i<m&&j<n){ //遍历访问比较nums1和nums2数组，哪个小哪个就放在nums[t]这个位置
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
    		t++;//提供下一个空位置来存放合并元素
    	}
    	while(j<n){ //nums2数组还有一部分较大的元素没有合并到nums1中
    		nums1[m+j]=nums2[j]; //把它们接到nums1的末尾
    		j++;
    	}
    }
}