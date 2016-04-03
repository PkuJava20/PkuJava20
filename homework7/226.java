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
      public TreeNode invertTree(TreeNode root) {//�����������
        if(root==null)  
            return null;
        //�������Һ���
        TreeNode node=root.left;  
        root.left=root.right;
        root.right=node;
        invertTree(root.left);  //�����ӵݹ����
        invertTree(root.right);  //���Һ��ӵݹ����
        return root;
    }
}