
public class Solution {
    public void deleteNode(ListNode node) {
        if(node==null||node.next==null)  //�ڵ㲻���ڻ�Ϊβ�ڵ�
        	return;
        node.val=node.next.val;  //ȥ��ֵ
        node.next=node.next.next;   //�����̽ڵ�
    }
}
       class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) { val = x; }
	   }