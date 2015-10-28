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
    public List<Integer> postorderTraversal(TreeNode root) {
        TreeNode p=root; //�����жϸ��ڵ�����ҽڵ���û�б�����
	    	TreeNode t;
	        List<Integer> list=new ArrayList<Integer>();
	        Stack<TreeNode> s=new Stack<TreeNode>();  //��ʼ��ջ
	        if(root==null)
	        	return list;
	        s.push(root);   //�����
	        while(!s.empty()){  //ջ����Ԫ�أ���������
	        	//t=s.pop();  
	        	t=s.peek();   //ȡջ���ڵ�
	        	if(t.left==null&&t.right==null||t.left==p||t.right==p){ //�������ӽڵ�������ӽڵ㱻���ʹ���
	        		list.add(t.val);
	        		p=t;    //������������ʹ��Ľڵ�λ��
	        		s.pop();  //��ջ
	        	}
	        	else{
	        	if(t.right!=null)  //�Ƿ������ӽڵ�
	        		s.push(t.right);  //���ӽڵ���ջ
	        	if(t.left!=null)   //�Ƿ������ӽڵ�
	        		s.push(t.left);  //���ӽڵ���ջ
	        }
	        }
	        return list;
    }
}