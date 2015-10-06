import java.util.Scanner;
public class second {
	    public String addBinary(String a, String b) {
	    	//System.out.println(a);
	    	//System.out.println(b);
	        int	carry=0;
	        char at[]=a.toCharArray();
	        char bt[]=b.toCharArray();
	    	int al=at.length;
	    	int bl=bt.length;
	    	int len=al>bl?al:bl;
	    	char c[]=new char[len+1];
	    	int i=len,j=al-1,k=bl-1;
	    	for(;k>=0&&j>=0;i--,j--,k--){
	    		int tmp=at[j]-'0'+bt[k]-'0'+carry;
	    		int t=tmp%2;
	    		carry=tmp/2;
	    		//tmp=tmp%2;
	    		c[i]=(char)(t+'0');
	    	}
	    	if(j==-1&&k!=-1){
	    		for(;k>=0;i--,k--){
	    			int tmp=bt[k]-'0'+carry;
		    		int t=tmp%2;
		    		carry=tmp/2;
		    		//tmp=tmp%2;
		    		c[i]=(char)(t+'0');
	    		}	
	    	}
	    	if(k==-1){
	    		for(;j>=0;i--,j--){
	    			int tmp=at[j]-'0'+carry;
		    		int t=tmp%2;
		    		carry=tmp/2;
		    		//tmp=tmp%2;
		    		c[i]=(char)(t+'0');
	    		}	
	    	}
	    	c[0]=(char)(carry+'0');
	    	if(c[0]=='0'){
	    		char []end=new char[len];
	    		for(j=0, i=1;i<=len;i++,j++)
	    			end[j]=c[i];
	    		String en=new String(end);
	    		
	    		return en;
	    	}
	    	
	    	String en=new String(c);
	    	return en;
	    }
	    public static void main(String[] args) {
	    	
	        Scanner in=new Scanner(System.in);
	        second AA=new second();
	    	String a=in.next();
	    	String b=in.next();
	    	String c=AA.addBinary(a,b);
	    	System.out.println(c);
	    	
	    }
}
