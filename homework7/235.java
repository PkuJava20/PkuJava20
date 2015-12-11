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
        if(p.val<root.val&&q.val<root.val) //��p��q��ֵ����rootС�������ǵ�LCAһ����root��������
        	return lowestCommonAncestor(root.left,p,q); //�ݹ���root�������в���p��q��LCA
        else if(p.val>root.val&&q.val>root.val)//��p��q��ֵ����root�������ǵ�LCAһ����root��������
        	return lowestCommonAncestor(root.right,p,q); //�ݹ���root�������в���p��q��LCA
        else
        	return root;  //����Ҫô��p��q����һ����root��Ҫô��p��q����һ����root����һ����rootС�������������root����LCA
    }
}