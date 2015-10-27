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
    public List<Integer> postorderTraversal(TreeNode root) {
         List<Integer> list = new ArrayList<Integer>();
		if(root==null)
			return list;

		List<Integer> visitFlag = new ArrayList();
		List<TreeNode> stack = new ArrayList();
		int top =-1;
        TreeNode temp;
        temp = root;
        do
        {
        	while(temp != null)
        	{
        		top++;
        		if(top>=stack.size())
        			stack.add(top, temp);
        		else
        			stack.set(top, temp);
        		temp = temp.left;
        		if(top>=visitFlag.size())
        			visitFlag.add(top, 0);
        		else
        			visitFlag.set(top, 0);
        	}
        	 while (visitFlag.get(top)==1)  
        	 { 
        		 list.add(stack.get(top).val);
        	 	top--; 
        	 	if(top==-1)
        	 		return list;
        	 }
        	 temp=stack.get(top); 
        	 if (visitFlag.get(top)==0)
        	 { 
        		 temp=temp.right;  
        		 visitFlag.set(top, 1);
        	 }
        }while(top!=-1);
		return list;
    }
}