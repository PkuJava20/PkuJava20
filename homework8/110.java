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
	/*����һleetcode��ʱ
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


       //������
  HashMap<TreeNode, Integer> heights = new HashMap<TreeNode, Integer>();//����������������ĸ߶�
	 public boolean isBalanced(TreeNode root) {
		 if(root==null){//Ϊ�յĽڵ���ƽ��Ķ��Ҹ߶ȶ���Ϊ1
			 heights.put(null,0);
			 return true;
		 }
		 if(isBalanced(root.left)&&isBalanced(root.right)){//����ǰ�ڵ��������������ƽ���
			 if(Math.abs(heights.get(root.left)-heights.get(root.right))>1){//���������߶Ȳ����һ����ƽ����
				 return false;
			 }
			 else{//������ƽ����
				 int newHeight=Math.max(heights.get(root.left),heights.get(root.right)) + 1;//��ǰ�ڵ�ĸ߶�Ϊ���������߶Ƚϴ��߼�һ
				 heights.put(root, newHeight);//���µ�ǰ�ڵ�ĸ߶�
				 return true;
			 }
		 }
		 else
			 return false;//����ǰ�ڵ����������������ƽ��ģ������������ƽ���
	 }
}
