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
	        Stack<TreeNode> s=new Stack<TreeNode>();  //初始化栈
	        if(root==null)
	        	return list;
	        s.push(root);   //放入根节点
	        while(!s.empty()){  //栈中无元素，遍历结束
	        	t=s.pop();  //根节点出栈
	        	list.add(t.val);
	        	if(t.right!=null)  //是否有右子树
	        		s.push(t.right);  //右子节点入栈
	        	if(t.left!=null)   //是否有左子树
	        		s.push(t.left);  //左子树入栈
	        }
	        return list;
    }
}