public class Solution {
    public boolean isPalindrome(String s) {
        if(s==null)return false;
		for(int i=0,j=s.length()-1;i<j;i++,j--)
		{
			while(!Character.isLetterOrDigit(s.charAt(i))&&i<j)
				i++;
			while(!Character.isLetterOrDigit(s.charAt(j))&&i<j)
				j--;
			int temp = s.charAt(i) - s.charAt(j);

            if(temp != 0 && temp != 32 && temp != -32) return false;
		}
		return true;
    }
}