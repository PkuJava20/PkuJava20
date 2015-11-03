import java.util.ArrayList;
public class minstack {
	ArrayList<Integer>   list=new ArrayList<Integer>();
	ArrayList<Integer>   min=new ArrayList<Integer>();
	int len=0;
	int minlen=0;
	//int len=list.size()-1;
	int minvalue=Integer.MAX_VALUE;
   public void push(int x) {
	   list.add(x);
	   len++;
	   if(x<=minvalue)  {
		  
		   min.add(x);
		   minlen++;
		   minvalue=x;
	   }    
    }

    public void pop() {
    	if(len==0) return;
        int t=list.remove(len-1);
        if(t==minvalue){
        	min.remove(minlen-1);
        	minlen--;
        	if(minlen!=0){
        	minvalue=min.get(minlen-1);
        	}
        	else
        	minvalue=Integer.MAX_VALUE;
        }
        len--;
    }

    public int top() {
        if(len!=0)  
         return list.get(len-1);
        return Integer.MAX_VALUE;
    }

    public int getMin() {
    	if(minlen==0)   return Integer.MAX_VALUE;
    	
        return min.get(minlen-1);
    }
}
