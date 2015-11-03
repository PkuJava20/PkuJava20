import java.util.ArrayList;


public class MyStack {
   ArrayList<Integer>  queue1=new ArrayList<Integer>();    //主队
   ArrayList<Integer>  queue2=new ArrayList<Integer>();    //辅助队
   int len1=-1;     //下标初始化
   int len2=-1;     //下标初始化
// Push element x onto stack.
   public void push(int x) {
	   queue1.add(x);
       len1++;
   }

   // Removes the element on top of the stack.
   public void pop() {
	   //将主队的前len1-1个数全部压到辅助栈中
       for(int i=0;i<len1;i++){
    	   int t=queue1.get(i);
    	   queue2.add(t);
    	   len2++;   
       }
       //主队清空
       queue1.clear();
       len1=-1;
       //将辅助队里面的元素全部压到主队里面去
       for(int i=0;i<=len2;i++){
    	   int t=queue2.get(i);
    	   queue1.add(t);
    	   len1++;
       }
       //辅助队清空
       queue2.clear();
       len2=-1;
   }

   // Get the top element.
   public int top() {
       return queue1.get(len1);
   }

   // Return whether the stack is empty.
   public boolean empty() {
       return queue1.isEmpty();
   }
}
