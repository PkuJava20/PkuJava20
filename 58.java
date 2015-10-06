import java.util.Scanner;
public class three {
 public int lengthOfLastWord(String s) {
	 
	 String []st=s.split(" ");
	 int n=st.length;
	 if(n>0)
	 return st[n-1].length();
	 else return 0;
    }
   public static void main(String[] args) {
 	//System.out.println("请随便输入一些内容:");
     Scanner in=new Scanner(System.in);
 	String s=in.nextLine();
 	three way=new three();
 	int t=way.lengthOfLastWord(s);
 	System.out.println(t);
 	//return 0;
 	in.close();
   }
}
