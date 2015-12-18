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
        if(root==null)//leetcode要求空树也是对称树
        	return true;
        return judgeSymmetric(root.left,root.right); //对左右孩子递归判断
    }
    public static boolean judgeSymmetric(TreeNode root1,TreeNode root2){
    	if(root1==null&&root2==null)//左右孩子都为空则为对称树
    		return true;
    	if(root1==null||root2==null)//左右孩子一个为空一个不为空，不是对称树
    		return false;
    	if(root1.val!=root2.val)//左右孩子值不相等，不是对称树
    		return false;
    	return judgeSymmetric(root1.left,root2.right)&&judgeSymmetric(root1.right,root2.left);//递归判断root1的左孩子和root2的右孩子，以及root1的右孩子和root2的左孩子是否对称
    }
}