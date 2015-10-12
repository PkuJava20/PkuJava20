import java.util.Scanner;
public class RemoveElement {
	 public int removeElement(int[] nums, int val) {
		 int cnt=nums.length;
	    for(int i=0;i<nums.length;i++)
	    { 
	    	if(nums[i]==val)  cnt--;
	    }
	    int []b=new int[cnt];
	    for(int i=0,j=0;i<nums.length;i++){
	    	if(nums[i]!=val){
	    		b[j++]=nums[i];
	    	}
	    }
	    //System.out.println(cnt);
	    //nums=new int[cnt];
	    for(int i=0;i<cnt;i++) nums[i]=b[i];
	  
	    return cnt;
	}
	
    public static void main(String[] args){
    	int n;
    	
    	
    	Scanner in=new Scanner(System.in);
    	int t;
    	 n=in.nextInt();
    	 int value=in.nextInt();
    	int []a=new int[n];
    	
    	for(int i=0;i<n;i++){
	 		t=in.nextInt();
	 		//if(t==-1)  break;
	 		a[i]=t;

    	}
		 RemoveElement tmp=new RemoveElement();
		 int len=tmp.removeElement(a,value);
		  for(int i=0;i<a.length;i++) System.out.print(a[i]);
		// System.out.println(len);
    }
}
