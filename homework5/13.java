public class Solution {
    public int romanToInt(String s) {
 int result=0;  //存储结果
	        char[] ch=s.toCharArray();//转换成字符数组，以便逐个字符处理
	        for(int i=0;i<ch.length;i++){
	        	if(ch[i]=='M')  //碰到M加1000
	        		result=result+1000;
	        	else if(ch[i]=='D')//碰到D加500
	        		result=result+500;
	        	else if(ch[i]=='L') //碰到X加50
	        		result=result+50;
	        	else if(ch[i]=='V')//碰到V加5
	        		result=result+5;
	        	else if(ch[i]=='C'){ //碰到C分类讨论
	        		if(i+1<ch.length){//防止越界
	        			if(ch[i+1]=='D'||ch[i+1]=='M') //CD或CM的情况，C要减100
	        				result=result-100;
	        			else
	        				result=result+100; //不是CD或CM的情况加100
	        		}
	        		else
	        			result=result+100;//C是最后一个字符，不是CD或CM的情况加100
	        	}
	        	else if(ch[i]=='X'){ //类似C的处理
	        		if(i+1<ch.length){
	        			if(ch[i+1]=='L'||ch[i+1]=='C')
	        				result=result-10;
	        			else
	        				result=result+10;
	        		}
	        		else
	        			result=result+10;
	        	}
	        	else if(ch[i]=='I'){//类似C的处理
	        		if(i+1<ch.length){
	        			if(ch[i+1]=='V'||ch[i+1]=='X')
	        				result=result-1;
	        			else
	        				result=result+1;
	        		}
	        		else
	        			result=result+1;
	        	}
	        }
	        return result;
    }
}