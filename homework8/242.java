public class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()) return false;
        while(s.length()>0)
        {
            Character temp =s.charAt(0);
            s=s.replaceAll(temp.toString(),"");
            t=t.replaceAll(temp.toString(),"");
            if(s.length()!=t.length()) return false;
        }
        if(s.length()!=t.length()) return false;
        return true;
    }
}