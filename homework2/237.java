
public class Solution {
    public void deleteNode(ListNode node) {
        if(node==null||node.next==null)  //节点不存在或为尾节点
        	return;
        node.val=node.next.val;  //去掉值
        node.next=node.next.next;   //变更后继节点
    }
}
       class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) { val = x; }
	   }