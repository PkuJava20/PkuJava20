/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null)
            return head;
        ListNode temp = head;
        while(temp.next!=null)
        {
            if(temp.val==temp.next.val){
                while(temp.val==temp.next.val){
                    if(temp.next.next==null){
                        temp.next=null;
                        break;
                    }
                    else
                        temp.next = temp.next.next;
                }  
            }
            else 
                temp=temp.next;
        }
        return head;
    }
}