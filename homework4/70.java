public class Solution {
    public int climbStairs(int n) {
       //쳲��������е�Ӧ��
	        if(n<=0)
	        return 0;
	        if(n==1)
	        	return 1;
	        if(n==2)
	        	return 2;
	        int climbStairs[]=new int[n+1];  //�洢��i��¥�ݵĲ�ͬ�ķ�ʽ
	        climbStairs[0]=0;
	        climbStairs[1]=1;//��1��¥��ֻ��һ�ַ���
	        climbStairs[2]=2;//��2��¥��ֻ�ж��ַ���
	        for(int i=3;i<n+1;i++) //�Ե����ϵ�������
	        	climbStairs[i]=climbStairs[i-1]+climbStairs[i-2];//����i��¥�ݵķ�������������i-1��¥�ݷ�����������i-2��¥�ݷ������ĺ�
	        return climbStairs[n]; //������n��¥�ݵķ�����
    }
}