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
    public boolean isSymmetric(TreeNode root) {
        if(root==null)    return true;
        return isSymmetricHelp(root.left,root.right);
        
    }
    public boolean isSymmetricHelp(TreeNode p,TreeNode q){
        if(p==null&&q==null)    return true;
        if(p==null&&q!=null||q==null&&p!=null)   return false;
        if(p.val!= q.val)    return false;
        else  return isSymmetricHelp(p.left,q.right)&&isSymmetricHelp(p.right,q.left);
    }
}