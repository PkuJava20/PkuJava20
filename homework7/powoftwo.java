
public class powoftwo {
	   public boolean isPowerOfTwo(int n) {
		   if(n<=0) return false;
	     int tmp=n&(n-1);
	     if(tmp==0)   return true;
	     return false;
	    }
	   public static void main(String[] agrs){
		   powoftwo test=new  powoftwo();
		   System.out.println(test.isPowerOfTwo(-8) );
	   }
}
