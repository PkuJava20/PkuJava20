public class Solution {
    public int countPrimes(int n) {
    	/*����һ:�����ж�С��n�����Ƿ�Ϊ���������leetcode��ʱ
    	if(n==0||n==1||n==2)
    		return 0;
    	int countPrimes=0;
    	boolean judgePrime=true;
        for(int i=2;i<n;i++){
        	for(int j=2;j<Math.sqrt(i);j++){
        		if(i%j==0){
        			judgePrime=false;
        			break;
        		}
        		judgePrime=true;
        	}
        	if(judgePrime)
        		countPrimes++;
        }
        return countPrimes;
        */
    	
    	//������:��С��n�ĺ���ȥ��������������
    	if(n==0||n==1||n==2)
    		return 0;
    	int result=n-2;//�����n-2������(1����)
    	boolean[] judgePrime=new boolean[n];//��ÿ��������ǣ�������true��������false
    	for(int i=0;i<judgePrime.length;i++)
    		judgePrime[i]=true; //��ʼȫ��Ϊ����
    	for(int i=2;i*i<n;i++){
    		if(judgePrime[i]){//�������������
    			for(int j=2;i*j<n;j++){
    				judgePrime[i*j]=false;//��i���������������Ϊ����
    			}
    		}
    	}
    	for(int i=2;i<judgePrime.length;i++){//������������ҳ�����
    		if(!judgePrime[i])
    			result--;//ÿ��һ�����������������һ
    	}
    	return result;
    }
}