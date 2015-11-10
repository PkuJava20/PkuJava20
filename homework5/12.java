public class Solution {
    public String intToRoman(int num) {
             //String aaaa,aaa,aa,a;  //存储各位的罗马数字
	         String roman[][]=new String[][]{{"","I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
	        		 {"","X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
	        		 {"","C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
	        		 {"","M", "MM", "MMM"}};   //存储罗马数字
                // switch(num/1000){  //判断千位
	        // case 0:aaaa=roman[3][0];break;
	       //  case 1:aaaa=roman[3][1];break;
	        // case 2:aaaa=roman[3][2];break;
	        // case 3:aaaa=roman[3][3];break;
	       //  }
	         String result="";  //存储结果
	         int i=0;  //控制存取
	         while(num>0){
	        	 result=roman[i][num%10]+result;  //从个位开始判断
	        	 num=num/10;  
	        	 i++;  //到更高一位判断
	         }
	         return result;
    }
}