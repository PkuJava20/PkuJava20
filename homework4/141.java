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
        if(head.next==null) //ֻ��ͷ�ڵ��û�л�
        	return false;
        ListNode fast=head.next; //�ߵĿ�Ľڵ�һ��������
        ListNode slow=head;  //�ߵ�����һ����һ��
        while(fast!=slow){  //�����ڵ����������л�
        	slow=slow.next;  //������һ��
        	if(fast.next!=null)
        		fast=fast.next; //�����һ��
        	else 
        		return false;  //�ߵ���β˵��û�л�
        	if(fast==slow)   //�����һ��������˵���л�
        		return true;  
        	if(fast.next!=null)  //����ߵڶ���
        		fast=fast.next;
        	else 
        		return false;  //�ߵ���β˵��û�л�
        }
        return true;
    }
}