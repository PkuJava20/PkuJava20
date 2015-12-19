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
        
		List<TreeNode> list = new ArrayList<TreeNode>();
		int count =0;
		if(root==null) return true;
		list.add(root);
		if(root.left==null){
		    if(root.right!=null)return false;
		    else return true;
		}
		else{
		    if(root.right==null) return false;
		}
		if(root.left.val!=root.right.val) return false;
		while(count<list.size())
		{
			int tempCount=0;
			int temp =list.size();
			for(;count<temp;count++)
			{
				TreeNode tempNode = list.get(count);
				if(tempNode.left!=null){
					list.add(tempNode.left);
					tempCount++;
				}
				if(tempNode.right!=null){
					list.add(tempNode.right);
					tempCount++;
				}
			}
			if(tempCount%2!=0) return false;
			if(list.size()>temp){
				int low = count;
				int high = list.size()-1;
				while(low<high)
				{
					if(!nodeisSymmetric(list.get(low),list.get(high)))
						return false;
					low++;
					high--;
				}
			}
		}
        return true;
    }
    public  boolean nodeisSymmetric (TreeNode n1,TreeNode n2){

		if(n1.val!=n2.val)
			return false;
		if(n1.left!=null){
			if(n2.right==null||n1.left.val!=n2.right.val)
				return false;
		}else{
			if(n2.right!=null)
				return false;
		}
		if(n1.right!=null){
			if(n2.left==null||n1.right.val!=n2.left.val)
				return false;
		}else{
			if(n2.left!=null)
				return false;
		}
		return true;
	}
}