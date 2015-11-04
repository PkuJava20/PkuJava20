
class MyQueue {
    Stack<Integer> MyStack = new Stack<Integer>();
    int count =0;
    // Push element x to the back of queue.
    public void push(int x) {
    	MyStack.push(x);
        count++;
    }

    // Removes the element from in front of queue.
    public void pop() {
    	if(MyStack.size()<=0)
    		return;
        Stack<Integer> TempStack = new Stack<Integer>();
        for(int i=1;i<count;i++)
        {
        	TempStack.push(MyStack.pop());
        }
        MyStack.clear();
        count--;
        for(int i=1;i<=count;i++)
        {
        	MyStack.push(TempStack.pop());
        }
    }

    // Get the front element.
    public int peek() {

    	if(MyStack.size()<=0)
    		return 0;
        Stack<Integer> TempStack = new Stack<Integer>();
        for(int i=1;i<count;i++)
        {
        	TempStack.push(MyStack.pop());
        }
        int temp = MyStack.peek();
        for(int i=1;i<count;i++)
        {
        	MyStack.push(TempStack.pop());
        }
		return temp;
    }

    // Return whether the queue is empty.
    public boolean empty() {
    	return MyStack.isEmpty();
    }
}