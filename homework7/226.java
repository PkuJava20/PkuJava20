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
      public TreeNode invertTree(TreeNode root) {//用先序遍历做
        if(root==null)  
            return null;
        //交换左右孩子
        TreeNode node=root.left;  
        root.left=root.right;
        root.right=node;
        invertTree(root.left);  //对左孩子递归遍历
        invertTree(root.right);  //对右孩子递归遍历
        return root;
    }
}
