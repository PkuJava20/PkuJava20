public class Solution {
    public boolean isAnagram(String s, String t) {
       //����һ�ֱ��s��t�����Ƚϣ�leetcode����(���г�ʱ)
    	/*
        char ss[]=s.toCharArray();
        char tt[]=t.toCharArray();
        if(ss.length!=tt.length)
        	return false;
        for (int i = 0; i < ss.length; i++) { //��s�ַ�������
            for (int j = i+1; j < ss.length; j++) {
                if (ss[i] > ss[j]) {
                    char tmp = ss[i];
                    ss[i] = ss[j];
                    ss[j] = tmp;
                }
            }
        }
        for (int i = 0; i < tt.length; i++) { //��t�ַ�������
            for (int j = i+1; j < tt.length; j++) {
                if (tt[i] > tt[j]) {
                    char tmp = tt[i];
                    tt[i] = tt[j];
                    tt[j] = tmp;
                }
            }
        }
        for(int i=0;i<tt.length;i++){  //����Ƿ���anagram
        	if(tt[i]!=ss[i])
        		return false;
        }
        return true;
        */
    	
    	
    	//�������������¼�ַ���s��t�и���ĸ���ֵĴ���
    	 int judge[]=new int[26]; //����ֱ��Ӧ�洢a-z26����ĸ���ֵĴ���
    	 char ss[]=s.toCharArray();//�ַ���ת��Ϊ�ַ�����
         char tt[]=t.toCharArray();
         if(ss.length!=tt.length)  //���Ȳ��ȣ�����anagram
         	return false;
         for(int i=0;i<ss.length;i++){ //�����ַ�����
        	 judge[ss[i]-97]++;//s�ַ����г����ֵ���ĸjudge���������һ
        	 judge[tt[i]-97]--;//t�ַ����г����ֵ���ĸjudge���������һ
         }
         for(int i=0;i<judge.length;i++){ //��t��s��anagram��judge[26]��ȫ��0
        	if(judge[i]!=0)
        		return false;
         }
         return true;
    }
}