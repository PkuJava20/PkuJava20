import java.util.ArrayList;


public class MyStack {
   ArrayList<Integer>  queue1=new ArrayList<Integer>();    //����
   ArrayList<Integer>  queue2=new ArrayList<Integer>();    //������
   int len1=-1;     //�±��ʼ��
   int len2=-1;     //�±��ʼ��
// Push element x onto stack.
   public void push(int x) {
	   queue1.add(x);
       len1++;
   }

   // Removes the element on top of the stack.
   public void pop() {
	   //�����ӵ�ǰlen1-1����ȫ��ѹ������ջ��
       for(int i=0;i<len1;i++){
    	   int t=queue1.get(i);
    	   queue2.add(t);
    	   len2++;   
       }
       //�������
       queue1.clear();
       len1=-1;
       //�������������Ԫ��ȫ��ѹ����������ȥ
       for(int i=0;i<=len2;i++){
    	   int t=queue2.get(i);
    	   queue1.add(t);
    	   len1++;
       }
       //���������
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
