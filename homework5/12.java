public class Solution {
    public String intToRoman(int num) {
             //String aaaa,aaa,aa,a;  //�洢��λ����������
	         String roman[][]=new String[][]{{"","I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
	        		 {"","X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
	        		 {"","C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
	        		 {"","M", "MM", "MMM"}};   //�洢��������
                // switch(num/1000){  //�ж�ǧλ
	        // case 0:aaaa=roman[3][0];break;
	       //  case 1:aaaa=roman[3][1];break;
	        // case 2:aaaa=roman[3][2];break;
	        // case 3:aaaa=roman[3][3];break;
	       //  }
	         String result="";  //�洢���
	         int i=0;  //���ƴ�ȡ
	         while(num>0){
	        	 result=roman[i][num%10]+result;  //�Ӹ�λ��ʼ�ж�
	        	 num=num/10;  
	        	 i++;  //������һλ�ж�
	         }
	         return result;
    }
}