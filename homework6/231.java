public class Solution {
    public boolean isPowerOfTwo(int n) {
               if(n==0) 
	        	return false;
	        int count=0;//ͳ�ƶ�����n��1�ĸ���
	        while(n>0){
	        	int j=n&1;//�������n������һλ�Ƿ�Ϊ1
	        	count=count+j;//ͳ�ƶ�����n��1�ĸ���
	        	n=n>>1;//n����һλ
	        }
	        if(count==1)//�κ�2��n�η��Ķ����Ʊ�ʾ������λ��ֻ��һ��1
	        	return true;
	        return false;
    }
}