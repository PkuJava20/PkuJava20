public class Solution {
    public int titleToNumber(String s) {
        int num = 0;
    	for(int i =0;i<s.length();i++)
    	{
    		if(i!=0) num =num*26;
    		num+=((int)s.charAt(i)-64);
    	}
        return num;
    }
}