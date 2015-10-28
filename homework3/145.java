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
        TreeNode p=root; //用来判断根节点的左右节点有没有被访问
	    	TreeNode t;
	        List<Integer> list=new ArrayList<Integer>();
	        Stack<TreeNode> s=new Stack<TreeNode>();  //初始化栈
	        if(root==null)
	        	return list;
	        s.push(root);   //放入根
	        while(!s.empty()){  //栈中无元素，遍历结束
	        	//t=s.pop();  
	        	t=s.peek();   //取栈顶节点
	        	if(t.left==null&&t.right==null||t.left==p||t.right==p){ //无左右子节点或左右子节点被访问过了
	        		list.add(t.val);
	        		p=t;    //重置最近被访问过的节点位置
	        		s.pop();  //出栈
	        	}
	        	else{
	        	if(t.right!=null)  //是否有右子节点
	        		s.push(t.right);  //右子节点入栈
	        	if(t.left!=null)   //是否有左子节点
	        		s.push(t.left);  //左子节点入栈
	        }
	        }
	        return list;
    }
}