class MyQueue {
  	//可以用两个栈模拟队列
	public Stack<Integer> s1=new Stack<Integer>(); //负责入队操作 
	public Stack<Integer> s2=new Stack<Integer>(); //负责出队操作
	 // Push element x to the back of queue.
    public void push(int x) {
        s1.push(x); //入队列
    }

    // Removes the element from in front of queue.
    public void pop() {
        if(s2.isEmpty()){ //s2为空，则把s1元素全部弹入s2
        	while(!s1.isEmpty())
        		s2.push(s1.pop());
        	s2.pop();  //出队
        }
        else
        	s2.pop(); //出队
    }

    // Get the front element.
    public int peek() {
    	  if(s1.isEmpty()&&s2.isEmpty())
          	return 0;
    	 if(s2.isEmpty()){//s2为空，则把s1元素全部弹入s2
         	while(!s1.isEmpty())
         		s2.push(s1.pop());
         	return s2.peek(); //s2的栈顶就是队头元素
         }
         else
         	return s2.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        if(s1.isEmpty()&&s2.isEmpty())  //两个栈全为空则队列为空
        	return true;
        return false;
    }
}