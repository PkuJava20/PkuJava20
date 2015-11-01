class MyStack {
	public Queue<Integer> q1=new LinkedList<Integer>();
	public Queue<Integer> q2=new LinkedList<Integer>();
	//�����������п���ģ��ջ
	  // Push element x onto stack.
    public void push(int x) { //push������һ�����зǿգ�һ������Ϊ��
        if(q1.isEmpty()){
        	q1.add(x);  //����յĶ���
        	while(!q2.isEmpty())   //����һ����������Ԫ�ؼӵ��������
        		q1.add(q2.poll()); 
        }
        else if(q2.isEmpty()){
        	q2.add(x);//����յĶ���
        	while(!q1.isEmpty())//����һ����������Ԫ�ؼӵ��������
        		q2.add(q1.poll());
        }
    }
    
    // Removes the element on top of the stack.
    public void pop() {  //�ǿյ��Ǹ����г�����
        if(!q1.isEmpty())
        	q1.poll();
        else if(!q2.isEmpty())
    	    q2.poll();
    }

    // Get the top element.
    public int top() { //�ǿյ��Ǹ�����ȡ��ͷԪ��
    	 if(!q1.isEmpty())
         	return q1.peek();
         else if(!q2.isEmpty())
     	    return q2.peek();
         else
        	 return 0;
    }

    // Return whether the stack is empty.
    public boolean empty() {  //�������ж�Ϊ�գ�ջ��Ϊ��
        if(q1.isEmpty()&&q2.isEmpty())
        	return true;
        return false;
    }
}