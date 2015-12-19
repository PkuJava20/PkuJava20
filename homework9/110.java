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
    public boolean isBalanced(TreeNode root) {
        if(root==null) return true;
    	int dValue =Math.abs(treeDepth(root.left)-treeDepth(root.right));
    	if(dValue<=1&&isBalanced(root.left)&&isBalanced(root.right))
    		return true;
    	else return false;
    }
    public  int treeDepth (TreeNode root){
    	if(root ==null) return 0;
    	int depth=0;
		List<TreeNode> list = new ArrayList<TreeNode>();
		if(root.left==null&&root.right==null) return 1;
		list.add(root);
		int count =0;
		while(count<list.size()){
			depth++;
			int temp =list.size();
			for(;count<temp;count++)
			{
				TreeNode tempNode = list.get(count);
				if(tempNode.left!=null){
					list.add(tempNode.left);
				}
				if(tempNode.right!=null){
					list.add(tempNode.right);
				}
			}
		}
    	return depth;
    }
}