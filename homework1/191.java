import java.util.Scanner;
public class two {
    public int hammingWeight(int n) {
        int cnt=0;
      //  Long m = (long) n;
       // int a;
        String str=Integer.toBinaryString(n);
        char []shu=str.toCharArray();
        int len=shu.length;
        for(int i=0;i<len;i++){
        	if(shu[i]=='1') cnt++;
        }
        
        return cnt;
    }
    public static void main(String[] args) {
    	//System.out.println("请随便输入一些内容:");
        Scanner in=new Scanner(System.in);
    	int n=in.nextInt();
    	two way=new two();
    	int t=way.hammingWeight(n);
    	System.out.println(t);
    	//System.out.println((-3)%2);
    	//return 0;
    	in.close();
    }
}
