import java.util.ArrayList;


public class MyQueue {
	  ArrayList<Integer>  stack1=new ArrayList<Integer>();   //主栈
		ArrayList<Integer>  stack2=new ArrayList<Integer>();   //辅助栈
		int len1=-1;
		int len2=-1;
		// Push element x to the back of queue.
	    public void push(int x) {
	         len1++;
	        stack1.add(x);
	       
	    }

	    // Removes the element from in front of queue.
	    public void pop() {
	    	//当主栈中只有一个元素的时候的直接弹出
	        if(len1==0){
	        	stack1.remove(0);
	        	len1--;
	        }
	        else{
	        	//当主栈中不只一个元素的时候，先将后len1-1逆序保留到辅助栈中
	        	for(int i=len1;i>0;i--){
	        		int t=stack1.get(i);
	        		stack2.add(t);
	        		len2++;
	        	}
	        	//主栈清空
	        	stack1.clear();
	        	len1=-1;
	        	//将辅助栈里面的值逆序压到主栈中
	        	for(int i=len2;i>=0;i--){
	        		int t=stack2.get(i);
	        		stack1.add(t);
	        		len1++;
	        	}
	        	stack2.clear();
	        	len2=-1;
	        }
	    }

	    // Get the front element.
	    public int peek() {
	    	int result=0;
	    	if(len1==0){
	        	return stack1.get(len1);
	        }
	        else{
	        	int i1=0;
	        	for( i1=len1;i1>0;i1--){
	        		int t=stack1.get(i1);
	        		stack2.add(t);
	        		len2++;
	        	}
	        	//这步是关键步骤：必须先将值赋值给result,并且要记得再将他压到辅助栈中
	        	
	        	result=stack1.get(i1);
	        	int t=stack1.get(i1);
	        	stack2.add(t);
	        	len2++;
	        	stack1.clear();
	        	len1=-1;
	        	for(int i=len2;i>=0;i--){
	        		 t=stack2.get(i);
	        		stack1.add(t);
	        		len1++;
	        	}
	        	stack2.clear();
	        	len2=-1;
	        }
	    	return result;
	    }

	    // Return whether the queue is empty.
	    public boolean empty() {
	        return stack1.isEmpty();
	    }
}
