public class Solution {
    public boolean canWinNim(int n) {
        //�Ӻ���ǰ�ƣ�˭��ռ�ݵ�����4k+1(k��������Ȼ��)��λ�ã�˭����Ӯ
        int i=n % 4; //������˼·���ĸ�ʯͷΪһ�飬��n��4ȡ��
        if(i==0)  //�Է�����ռ�ݵ�����4k+1(k��������Ȼ��)��λ��
        	return false; //�ҷ�����
        else  //�ҷ�����ռ�ݵ�����4k+1(k��������Ȼ��)��λ��
        	return true;  //�ҷ���Ӯ
    }
}
