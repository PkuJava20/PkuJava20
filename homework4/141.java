/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head==null)
        	return false;
        if(head.next==null) //只有头节点就没有环
        	return false;
        ListNode fast=head.next; //走的快的节点一次走两步
        ListNode slow=head;  //走的慢的一次走一步
        while(fast!=slow){  //快慢节点相遇表明有环
        	slow=slow.next;  //慢的走一步
        	if(fast.next!=null)
        		fast=fast.next; //快的走一步
        	else 
        		return false;  //走到链尾说明没有环
        	if(fast==slow)   //快的走一步后相遇说明有环
        		return true;  
        	if(fast.next!=null)  //快的走第二步
        		fast=fast.next;
        	else 
        		return false;  //走到链尾说明没有环
        }
        return true;
    }
}