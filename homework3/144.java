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
        
        List<Integer> list = new ArrayList<Integer>();
        if(root==null)
			return list;
        Stack listStack = new Stack();
        TreeNode temp;
        temp = root;
        listStack.push(temp);
         while(!listStack.isEmpty()){
        	temp = (TreeNode) listStack.pop();
        	list.add(temp.val);
        	if(temp.right!=null)
        		listStack.push(temp.right);
        	if(temp.left!=null)
        		listStack.push(temp.left);
        }
		return list;
    }
}