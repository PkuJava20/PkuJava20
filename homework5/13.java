public class Solution {
    public int romanToInt(String s) {
        int length = s.length();
    	int num=0;
    	int count =0;
    	while(count <length){
    		switch (s.charAt(count))
    		{
    			case 'M':
					num+=1000;
					count++;
    				break;
    			case 'D':
					num+=500;
					count++;
    				break;
    			case 'C':
    				if(count+1<length)
    				{
    					if(s.charAt(count+1)=='M'){
    						num+=900;
    						count+=2;
    					}
    					else if(s.charAt(count+1)=='D'){
    						num+=400;
    						count+=2;
    					}
    					else{
    						num+=100;
    						count++;
    					}
    				}
					else{
						num+=100;
						count++;
					}
    				break;
    			case 'L':
					num+=50;
					count++;
    				break;
    			case 'X':
    				if(count+1<length)
    				{
    					if(s.charAt(count+1)=='C'){
    						num+=90;
    						count+=2;
    					}
    					else if(s.charAt(count+1)=='L'){
    						num+=40;
    						count+=2;
    					}
    					else{
    						num+=10;
    						count++;
    					}
    				}
					else{
						num+=10;
						count++;
					}
    				break;
    			case 'V':
					num+=5;
					count++;
    				break;
    			case 'I':
    				if(count+1<length)
    				{
    					if(s.charAt(count+1)=='X'){
    						num+=9;
    						count+=2;
    					}
    					else if(s.charAt(count+1)=='V'){
    						num+=4;
    						count+=2;
    					}
    					else{
    						num+=1;
    						count++;
    					}
    				}
					else{
						num+=1;
						count++;
					}
    				break;
    			default:
    				break;
    		}
    	}
        return num;
    }
}