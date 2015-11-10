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
                 if(head==null)//头结点为空
			 return head;
		 if(head.next==null)
			 return head;
	        ListNode p1=head;  //用双指针解题，他们始终指向相邻结点
	        ListNode p2=head.next;
	        while(p2.next!=null||p1.val==p2.val){ //两个指针指向链表最后两个结点且它们的值不一样就退出循环
	        	if(p1.val==p2.val){  //出现重复结点
	        		if(p2.next!=null){  //防止越界
	        			//去除重复结点
	        			p1.next=p2.next;  
	        			p2=p2.next;
	        		}
	        		else{  //这是两个指针指向链表最后两个结点且它们的值一样的情况
	        			p1.next=null;//舍弃最后一个结点
	        			return head; //返回结果
	        		}
	        	}
	        	else{  //两个指针指向链表的两个相邻结点且它们的值不一样的情况
	        		//两指针均向后移一次
	        		p1=p2;
	        		p2=p2.next;
	        	}
	        }
	        return head;//返回结果
    }
}