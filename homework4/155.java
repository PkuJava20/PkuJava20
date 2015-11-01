class MinStack {
	public Stack<Integer> s=new Stack<Integer>();  //题目中要求的栈
	public Stack<Integer> min=new Stack<Integer>(); //存较小的几个数，栈顶存最小数，方便getMin
	 public void push(int x) {
		 if(min.isEmpty())
			 min.push(x);
		 else if(x<=min.peek())  //更新最小数
			 min.push(x);
	        s.push(x);   //入栈
	    }

	    public void pop() {
	    	if(!s.isEmpty()){
	    		//if(s.peek()==min.peek()){ //对两个int为什么不能用==而必须用equals()判相等，很疑惑！！！
	    		//	min.pop();
	    			//System.out.println("ppupokuypkup");}
	    		if(s.peek().equals(min.peek())) //若最小数出栈，min中要相应出栈
	    			min.pop();
	        s.pop(); //出栈
	    	}
	    	//System.out.println(s.peek()+" "+min.peek());
	    }

	    public int top() {
	    	if(!s.isEmpty())
	        return s.peek(); //取栈顶元素
	    	else return 0;
	    }

	    public int getMin() { 
	    	if(!min.isEmpty())
	        return min.peek(); //min栈顶元素即为s栈最小元素
	    	else
	    		return 0;
	    }
}
