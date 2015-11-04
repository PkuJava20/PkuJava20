class MyStack {
    Queue<Integer> MyQueue = new LinkedList<Integer>();
    Queue<Integer> MyQueue2 = new LinkedList<Integer>();
    int top=0;
    // Push element x onto stack.
    public void push(int x) {
        MyQueue.add(x);
        MyQueue2.add(x);
        top++;
    }

    // Removes the element on top of the stack.
    public void pop() {
        int temp;
        MyQueue= new LinkedList<Integer>();
        for(int i=1;i<top;i++){
        	temp=MyQueue2.poll();
            MyQueue.add(temp);
        }
        if(top==1)
        {
        	 MyQueue2.poll();
        	 MyQueue.poll();
        }
        MyQueue2.clear();
        for(int element : MyQueue)
        {
        	MyQueue2.add(element);
        }
        top--;
    }

    // Get the top element.
    public int top() {
        int temp =0;
        if(MyQueue.size()>0)
        { 
            for(int i=1;i<top;i++){
                MyQueue2.poll();
            }
            temp = MyQueue2.poll();
            MyQueue2.clear();
            for(int element : MyQueue)
            {
            	MyQueue2.add(element);
            }
        }
        return temp;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return MyQueue.isEmpty();
    }
}