
public class titletonumber {
    public int titleToNumber(String s) {
        char []tmp=s.toCharArray();
        int sum=0;
        for(int i=0;i<tmp.length;i++){
        	sum+=(tmp[i]-'A'+1)*jie(tmp.length-1-i);
        }
        return sum;
    }
    public int jie(int i){
    	if(i==0)   return 1;
    	int sum=1;
    	for(int t=1;t<=i;t++)   sum*=26;
    	return sum;
    }
    public static void main(String [] agrs){
    	titletonumber test=new titletonumber ();
    	String s="A";
    	int result=test.titleToNumber(s);
    	System.out.println(result);
    }
}
