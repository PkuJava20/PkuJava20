/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
       	TreeNode t=root;
	        List<Integer> list=new ArrayList<Integer>();
	        Stack<TreeNode> s=new Stack<TreeNode>();  //��ʼ��ջ
	        if(root==null)
	        	return list;
	        s.push(root);   //������ڵ�
	        while(!s.empty()){  //ջ����Ԫ�أ���������
	        	while(t.left!=null){   //���ӽڵ�ȫ��ջ
	        		s.push(t.left);
	        		t=t.left;   //ָ�����һ�����ӽڵ�
	        	}
	        	if(t.right==null){  //���ҽڵ�
	        		list.add(t.val);  //������
	        		s.pop();  //��ջ
	        		if(!s.empty()){  //������һ��������ջ�Ѿ����ˣ�˵������������������
	        		t=s.peek();  
	        		t.left=null;  //��ڵ��Ѿ����ʹ����ÿ�
	        		}
	        		else 
	        			return list;
	        	}
	        	else{  //���ҽڵ�
	        		list.add(t.val);   //������
	        		s.pop();   //��ջ
	        		s.push(t.right);  //�ҽڵ���ջ
	        		t=t.right;
	        	}
	        }
	        return list;
    }
}