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
	/*方法一leetcode超时
    public boolean isBalanced(TreeNode root) {
    	//boolean judge;
        if(root==null)
        	return true;
        int leftHeight=getHeight(root.left,1);
        int rightHeight=getHeight(root.right,1);
        if(leftHeight-rightHeight>1||rightHeight-leftHeight>1)
        	return false;
        else 
            return isBalanced(root.left)&&isBalanced(root.right);
    }
    public static int getHeight(TreeNode node,int deepth){
    	if(node==null)
    		return deepth;
    	return getHeight(node.left,deepth+1)>getHeight(node.right,deepth+1)?getHeight(node.left,deepth+1):getHeight(node.right,deepth+1);
    }*/


       //方法二
  HashMap<TreeNode, Integer> heights = new HashMap<TreeNode, Integer>();//用来存放左右子树的高度
	 public boolean isBalanced(TreeNode root) {
		 if(root==null){//为空的节点是平衡的而且高度定义为1
			 heights.put(null,0);
			 return true;
		 }
		 if(isBalanced(root.left)&&isBalanced(root.right)){//若当前节点的左右子树都是平衡的
			 if(Math.abs(heights.get(root.left)-heights.get(root.right))>1){//左右子树高度差大于一则不是平衡树
				 return false;
			 }
			 else{//否则是平衡树
				 int newHeight=Math.max(heights.get(root.left),heights.get(root.right)) + 1;//当前节点的高度为左右子树高度较大者加一
				 heights.put(root, newHeight);//更新当前节点的高度
				 return true;
			 }
		 }
		 else
			 return false;//若当前节点的左右子树不都是平衡的，则这棵树不是平衡的
	 }
}
