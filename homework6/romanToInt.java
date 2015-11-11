import java.util.Scanner;
public class romanToInt1 {
    public int romanToInt(String s) {
        int len=s.length();
        int tmp=0;
        if(len==1){
        	char[] a=s.toCharArray();
        	tmp=value(a[0]);
        	return tmp;
        }
        int sum=0;
        char[] str=s.toCharArray();
        if(len==2){
        	if(compare(str[len-2],str[len-1])==true){
            	sum=sum+value(str[len-2])+value(str[len-1]);
            }
            else{
            	sum=sum+value(str[len-1])-value(str[len-2]);
            }
        	return sum;
        }
        int i=0;
        if(len>2){
           for(i=0;i<len-2;){
        	if(compare(str[i],str[i+1])==true){
        		
        		if(str[i]!=str[i+1]){
        			sum+=value(str[i]);
        		i++;
        		}
        		else{      			
        		sum=sum+value(str[i])+value(str[i+1]);
        		i+=2;
        		}
        	}
        	else{
        	sum=sum+value(str[i+1])-value(str[i]);
        	i+=2;
        	}
        }
        }
        if(i==len-1)  sum+=value(str[len-1]);
        else{
        if(compare(str[len-2],str[len-1])==true){
        	sum=sum+value(str[len-2])+value(str[len-1]);
        }
        else{
        	sum=sum+value(str[len-1])-value(str[len-2]);
        }
        }
        return sum;
    }
    //返回字符a的坐标以便比较
    public int location(char a){
    	char[] s={'M','D','C','L','X','V','I'};
    	int i=0;
    	for( i=0;;i++){
    		if(s[i]==a)   break;
    	}
    	return i;
    }
    //比较字符所在的位置
    public boolean compare(char a,char b){
    	if(location(a)<=location(b))   return true;
    	else return false;
    }
    //返回字符的值
    public int value(char a){
    	int v=0;
    	if(a=='M')   v=1000;
    	if(a=='D')   v=500;
    	if(a=='C')   v=100;
    	if(a=='L')   v=50;
    	if(a=='X')   v=10;
    	if(a=='V')   v=5;
    	if(a=='I')   v=1;
    	return v;
    }
    public static void main(String []agrs) {
    	Scanner in=new Scanner(System.in);
    	String s=in.nextLine();
    	 romanToInt1 test=new  romanToInt1();
    	 int result=test.romanToInt(s);
    	 System.out.println(result);
    	
    }
}
