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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    	if(root==null)
    		return root;
        if(p.val<root.val&&q.val<root.val) //若p和q数值都比root小，则它们的LCA一定在root左子树中
        	return lowestCommonAncestor(root.left,p,q); //递归在root左子树中查找p和q的LCA
        else if(p.val>root.val&&q.val>root.val)//若p和q数值都比root大，则它们的LCA一定在root右子树中
        	return lowestCommonAncestor(root.right,p,q); //递归在root右子树中查找p和q的LCA
        else
        	return root;  //否则要么是p和q中有一个是root，要么是p和q中有一个比root大另一个比root小，不管哪种情况root都是LCA
    }
}