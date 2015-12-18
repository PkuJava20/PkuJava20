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
        if(root==null)//leetcodeҪ�����Ҳ�ǶԳ���
        	return true;
        return judgeSymmetric(root.left,root.right); //�����Һ��ӵݹ��ж�
    }
    public static boolean judgeSymmetric(TreeNode root1,TreeNode root2){
    	if(root1==null&&root2==null)//���Һ��Ӷ�Ϊ����Ϊ�Գ���
    		return true;
    	if(root1==null||root2==null)//���Һ���һ��Ϊ��һ����Ϊ�գ����ǶԳ���
    		return false;
    	if(root1.val!=root2.val)//���Һ���ֵ����ȣ����ǶԳ���
    		return false;
    	return judgeSymmetric(root1.left,root2.right)&&judgeSymmetric(root1.right,root2.left);//�ݹ��ж�root1�����Ӻ�root2���Һ��ӣ��Լ�root1���Һ��Ӻ�root2�������Ƿ�Գ�
    }
}