class MyQueue {
  	//����������ջģ�����
	public Stack<Integer> s1=new Stack<Integer>(); //������Ӳ��� 
	public Stack<Integer> s2=new Stack<Integer>(); //������Ӳ���
	 // Push element x to the back of queue.
    public void push(int x) {
        s1.push(x); //�����
    }

    // Removes the element from in front of queue.
    public void pop() {
        if(s2.isEmpty()){ //s2Ϊ�գ����s1Ԫ��ȫ������s2
        	while(!s1.isEmpty())
        		s2.push(s1.pop());
        	s2.pop();  //����
        }
        else
        	s2.pop(); //����
    }

    // Get the front element.
    public int peek() {
    	  if(s1.isEmpty()&&s2.isEmpty())
          	return 0;
    	 if(s2.isEmpty()){//s2Ϊ�գ����s1Ԫ��ȫ������s2
         	while(!s1.isEmpty())
         		s2.push(s1.pop());
         	return s2.peek(); //s2��ջ�����Ƕ�ͷԪ��
         }
         else
         	return s2.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        if(s1.isEmpty()&&s2.isEmpty())  //����ջȫΪ�������Ϊ��
        	return true;
        return false;
    }
}