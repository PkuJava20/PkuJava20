public class Solution {
    public int trailingZeroes(int n) {//����˼������n!�����г����ĺ�������5�ĸ����������Ŀ����n!�������ĸ���
    	/*����һ:��1��nÿ���������Һ�������5�ĸ����������ܺͣ����leetcode��ʱ
    	if(n<=0)
    		return 0;
    	int count5=0;
        int p=0;
        int t=0;
        for(int i=1;i<=n;i++){
        	p=i%5;
        	t=i;
        	while(p==0){
        		count5++;
        		t=t/5;
        		p=t%5;
        	}
        }
        return count5;
        */
    	
    	/*������:�ֱ���n!�к�������5^1,5^2,5^3...�ĸ�������ͣ������Ȼ��ʱ
    	if(n<=0)
    		return 0;
    	int mi=1;
    	int count5=n/(int)Math.pow(5, mi);
    	int sum5=0;
    	while(count5!=0){
    		sum5=sum5+count5;
    		mi++;
    		count5=n/(int)Math.pow(5, mi);
    	}
    	return sum5;
    	*/
    	
    	//������˼��ͷ��������ƣ�ֻ������5�ĸ߽��ݴε����Ӹ�����������5�ĵͽ��ݴεĻ����ϣ���ʱ�临�ӶȽ��ͺܶ�
    	if(n<=0)
    		return 0;
    	int count5=n/5;//��������5^1�ĸ���
    	int sum5=0;//��������5�ĸ���
    	while(count5!=0){//��n������5��ĳ���߽��ݴ���������¾��˳��жϣ��ҵ�����������5�ĸ���
    		sum5=sum5+count5;//����5��ĳ���ݴ����Ӹ���
    		count5=count5/5;//��5�ĸ���һ���ݴ����Ӹ���
    	}
    	return sum5;
    }
}