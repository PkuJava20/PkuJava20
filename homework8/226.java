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
    public TreeNode invertTree(TreeNode root) {
        if(root==null)return root;
        if(root.left==null&&root.right==null) return root;
        Queue<TreeNode> nodeQueue= new LinkedList<TreeNode>();
        nodeQueue.offer(root);
        while(!nodeQueue.isEmpty())
        {
            TreeNode temp = nodeQueue.poll();
            if(temp.left!=null||temp.right!=null){
                TreeNode temp2 =temp.left;
                temp.left = temp.right;
                temp.right = temp2;
                if(temp.left!=null) nodeQueue.offer(temp.left);
                if(temp.right!=null) nodeQueue.offer(temp.right);
            }
        }
        return root;
    }
}