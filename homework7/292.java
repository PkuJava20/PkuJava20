public class Solution {
    public boolean canWinNim(int n) {
        //从后往前推，谁能占据倒数第4k+1(k是任意自然数)个位置，谁就稳赢
        int i=n % 4; //由上述思路，四个石头为一组，即n对4取余
        if(i==0)  //对方总能占据倒数第4k+1(k是任意自然数)个位置
        	return false; //我方稳输
        else  //我方总能占据倒数第4k+1(k是任意自然数)个位置
        	return true;  //我方稳赢
    }
}
