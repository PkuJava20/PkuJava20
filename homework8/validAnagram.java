public class Solution {
    public boolean isAnagram(String s, String t) {
        /*方法一：
         int len1=s.length();
        int len2=t.length();
        if(len1!=len2)  return false;
        else{
        	char [] sa=s.toCharArray();
        	char [] ta=t.toCharArray();
        	Arrays.sort(sa);
        	Arrays.sort(ta);
        	int i=0;
        	for(;i<len1;i++){
        		if(sa[i]!=ta[i])   break;
        	}
        	if(i==len1)   return true;
        	else return false;
        }
        */
        //方法二：公母牛法
        if(s.length()!=t.length())    return false;
        int[] res=new int[27];
        int cows=0,bulls=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==t.charAt(i))   bulls++;
            else{
                if(res[s.charAt(i)-'a']++<0)   cows++;
                if(res[t.charAt(i)-'a']-->0)   cows++;
            }
        }
        if(bulls+cows==s.length())   return true;
        else return false;
    }
}