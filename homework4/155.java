class MinStack {
	public Stack<Integer> s=new Stack<Integer>();  //��Ŀ��Ҫ���ջ
	public Stack<Integer> min=new Stack<Integer>(); //���С�ļ�������ջ������С��������getMin
	 public void push(int x) {
		 if(min.isEmpty())
			 min.push(x);
		 else if(x<=min.peek())  //������С��
			 min.push(x);
	        s.push(x);   //��ջ
	    }

	    public void pop() {
	    	if(!s.isEmpty()){
	    		//if(s.peek()==min.peek()){ //������intΪʲô������==��������equals()����ȣ����ɻ󣡣���
	    		//	min.pop();
	    			//System.out.println("ppupokuypkup");}
	    		if(s.peek().equals(min.peek())) //����С����ջ��min��Ҫ��Ӧ��ջ
	    			min.pop();
	        s.pop(); //��ջ
	    	}
	    	//System.out.println(s.peek()+" "+min.peek());
	    }

	    public int top() {
	    	if(!s.isEmpty())
	        return s.peek(); //ȡջ��Ԫ��
	    	else return 0;
	    }

	    public int getMin() { 
	    	if(!min.isEmpty())
	        return min.peek(); //minջ��Ԫ�ؼ�Ϊsջ��СԪ��
	    	else
	    		return 0;
	    }
}
