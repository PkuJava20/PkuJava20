
public class Solution {
    public int addDigits(int num) {
        while(num>=10){           //����1λ���ͼ�����λ��ӵĹ���
        	num=getsum(num);
        }
        return num;      //���ؽ��
    }
    public static int getsum(int num){  //��������λ��ӵĺ�
    	int m,result=0;
    	while(num!=0){
    		m=num%10;
    		num=num/10;
    		result=result+m;
    	}
    	return result;
    }
 /* public int addDigits(int num){
	       return (num-1)%9+1;       //�����Զ������������о��������������������ɣ�O(1)���Ӷȵ��㷨
     }*/
}
