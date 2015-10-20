public class Solution {
    public int reverse(int x) {
        boolean negative = false;
        if(x<0) {negative =true;x = -x;}
        if(x==0) return x;
        while(x%10==0)
            x=x/10;
        int tempx=0;
        while(x>0)
        {
            tempx =tempx*10+ x%10;
            if(x/10>0&&tempx>Integer.MAX_VALUE/10)
                return 0;
            x=x/10;
        }
        if(negative)
            return -tempx;
        return tempx;
    }
}