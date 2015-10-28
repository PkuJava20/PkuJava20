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
    public List<Integer> preorderTraversal(TreeNode root) {
        //TreeNode p;
	    	TreeNode t;
	        List<Integer> list=new ArrayList<Integer>();
	        Stack<TreeNode> s=new Stack<TreeNode>();  //��ʼ��ջ
	        if(root==null)
	        	return list;
	        s.push(root);   //������ڵ�
	        while(!s.empty()){  //ջ����Ԫ�أ���������
	        	t=s.pop();  //���ڵ��ջ
	        	list.add(t.val);
	        	if(t.right!=null)  //�Ƿ���������
	        		s.push(t.right);  //���ӽڵ���ջ
	        	if(t.left!=null)   //�Ƿ���������
	        		s.push(t.left);  //��������ջ
	        }
	        return list;
    }
}