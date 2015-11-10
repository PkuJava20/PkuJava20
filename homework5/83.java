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
                 if(head==null)//ͷ���Ϊ��
			 return head;
		 if(head.next==null)
			 return head;
	        ListNode p1=head;  //��˫ָ����⣬����ʼ��ָ�����ڽ��
	        ListNode p2=head.next;
	        while(p2.next!=null||p1.val==p2.val){ //����ָ��ָ���������������������ǵ�ֵ��һ�����˳�ѭ��
	        	if(p1.val==p2.val){  //�����ظ����
	        		if(p2.next!=null){  //��ֹԽ��
	        			//ȥ���ظ����
	        			p1.next=p2.next;  
	        			p2=p2.next;
	        		}
	        		else{  //��������ָ��ָ���������������������ǵ�ֵһ�������
	        			p1.next=null;//�������һ�����
	        			return head; //���ؽ��
	        		}
	        	}
	        	else{  //����ָ��ָ��������������ڽ�������ǵ�ֵ��һ�������
	        		//��ָ��������һ��
	        		p1=p2;
	        		p2=p2.next;
	        	}
	        }
	        return head;//���ؽ��
    }
}