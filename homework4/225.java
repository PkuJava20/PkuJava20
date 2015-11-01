class MyStack {
	public Queue<Integer> q1=new LinkedList<Integer>();
	public Queue<Integer> q2=new LinkedList<Integer>();
	//定义两个队列可以模拟栈
	  // Push element x onto stack.
    public void push(int x) { //push操作后一个队列非空，一个队列为空
        if(q1.isEmpty()){
        	q1.add(x);  //加入空的队列
        	while(!q2.isEmpty())   //把另一个队列所有元素加到这个队列
        		q1.add(q2.poll()); 
        }
        else if(q2.isEmpty()){
        	q2.add(x);//加入空的队列
        	while(!q1.isEmpty())//把另一个队列所有元素加到这个队列
        		q2.add(q1.poll());
        }
    }
    
    // Removes the element on top of the stack.
    public void pop() {  //非空的那个队列出队列
        if(!q1.isEmpty())
        	q1.poll();
        else if(!q2.isEmpty())
    	    q2.poll();
    }

    // Get the top element.
    public int top() { //非空的那个队列取队头元素
    	 if(!q1.isEmpty())
         	return q1.peek();
         else if(!q2.isEmpty())
     	    return q2.peek();
         else
        	 return 0;
    }

    // Return whether the stack is empty.
    public boolean empty() {  //两个队列都为空，栈就为空
        if(q1.isEmpty()&&q2.isEmpty())
        	return true;
        return false;
    }
}