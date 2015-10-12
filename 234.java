/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head==null||head.next ==null)
            return true; 
        ListNode slow=head;
        ListNode fast=head;
        while(fast!=null&&fast.next!=null)
        {
        	slow = slow.next;
        	fast = fast.next.next;
        }
        if(fast!=null) slow = slow.next;
        slow = Reverse(head,slow);
        while(slow!=null)
        {
        	if(slow.val!=head.val)
        		return false;
        	slow = slow.next;
        	head = head.next;
        }
        return true;
    }
    public  ListNode Reverse(ListNode head,ListNode middle)
	{
		while(head.next!=middle)
			head = head.next;
		ListNode temp = null;
		while(middle.next!=null)
		{
			temp = middle.next;
			middle.next= temp.next;
			temp.next = head.next;
			head.next = temp;
		}
		return head.next;
	}
}