public class Solution {
    public String intToRoman(int num) {
        String romanStr = "";
    	int count=0;
    	int temp=num;
    	if(temp<1||temp>3999)
    		return romanStr;
    	if(temp/1000!=0)
    	{
    		count =temp/1000;
    		for(int i=0;i<count;i++)
    			romanStr=romanStr+"M";
    		temp=temp%1000;
    	}
    	if(temp/100!=0)
    	{
        	count =temp/100;
    		if(count==4)
    			romanStr=romanStr+"CD";
        	else if(count==9)
    			romanStr=romanStr+"CM";
        	else
        	{
            	if(temp/500!=0)
            	{
        			romanStr=romanStr+"D";
        			temp=temp%500;
            	}
            	count =temp/100;
        		for(int i=0;i<count;i++)
        			romanStr=romanStr+"C";
            }
			temp=temp%100;
    	}
    	if(temp/10!=0)
    	{
    		count =temp/10;
    		if(count==4)
    			romanStr=romanStr+"XL";
        	else if(count==9)
    			romanStr=romanStr+"XC";
        	else
        	{
            	if(temp/50!=0)
            	{
        			romanStr=romanStr+"L";
        			temp=temp%50;
            	}
        		count =temp/10;
        		for(int i=0;i<count;i++)
        			romanStr=romanStr+"X";
            }
			temp=temp%10;
    	}
    	count =temp;
    	if(count==4)
			romanStr=romanStr+"IV";
    	else if(count==9)
			romanStr=romanStr+"IX";
    	else
    	{
        	if(temp/5!=0)
        	{
    			romanStr=romanStr+"V";
    			temp=temp%5;
        	}
        	count =temp;
    		for(int i=0;i<count;i++)
    			romanStr=romanStr+"I";
        }
		return romanStr;
    }
}