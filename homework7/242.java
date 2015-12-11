public class Solution {
    public boolean isAnagram(String s, String t) {
       //方法一分别对s和t排序后比较，leetcode报错(运行超时)
    	/*
        char ss[]=s.toCharArray();
        char tt[]=t.toCharArray();
        if(ss.length!=tt.length)
        	return false;
        for (int i = 0; i < ss.length; i++) { //对s字符串排序
            for (int j = i+1; j < ss.length; j++) {
                if (ss[i] > ss[j]) {
                    char tmp = ss[i];
                    ss[i] = ss[j];
                    ss[j] = tmp;
                }
            }
        }
        for (int i = 0; i < tt.length; i++) { //对t字符串排序
            for (int j = i+1; j < tt.length; j++) {
                if (tt[i] > tt[j]) {
                    char tmp = tt[i];
                    tt[i] = tt[j];
                    tt[j] = tmp;
                }
            }
        }
        for(int i=0;i<tt.length;i++){  //检查是否是anagram
        	if(tt[i]!=ss[i])
        		return false;
        }
        return true;
        */
    	
    	
    	//方法二用数组记录字符串s和t中各字母出现的次数
    	 int judge[]=new int[26]; //数组分别对应存储a-z26个字母出现的次数
    	 char ss[]=s.toCharArray();//字符串转换为字符数组
         char tt[]=t.toCharArray();
         if(ss.length!=tt.length)  //长度不等，不是anagram
         	return false;
         for(int i=0;i<ss.length;i++){ //遍历字符数组
        	 judge[ss[i]-97]++;//s字符串中出出现的字母judge数组次数加一
        	 judge[tt[i]-97]--;//t字符串中出出现的字母judge数组次数减一
         }
         for(int i=0;i<judge.length;i++){ //若t和s是anagram，judge[26]中全是0
        	if(judge[i]!=0)
        		return false;
         }
         return true;
    }
}