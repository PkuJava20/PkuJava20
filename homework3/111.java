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
    public int minDepth(TreeNode root) {
        int count=0;  //记录数最小深度
		if(root==null)
        	return 0;
		TreeNode t;
		TreeNode lastNode=root;  //标志每层最后节点，方便算层数
		Queue<TreeNode> q=new LinkedList<TreeNode>();
		q.add(root);
		while(!q.isEmpty()){
			t=q.poll();  //出队列
			if(t==lastNode){   //如果取到的是某层最后一个节点
				count++;  //层数加一
				if(t.left==null&&t.right==null)   //找到叶子节点
					break;  //退出
			    if(t.left!=null){
				    q.add(t.left);  //左子节点入队列
				    lastNode=t.left;  //更新最后节点位置
			}
			    if(t.right!=null){
				    q.add(t.right);  //右子节点入队列
				    lastNode=t.right; //更新最后节点位置
			}
			}
			else{  //如果取到的不是某层最后一个节点
				if(t.left==null&&t.right==null){  //找到叶子节点
					count++;
					break;  //退出
				}
			    if(t.left!=null)  
				    q.add(t.left);  //左子节点入队列
			    if(t.right!=null)
				    q.add(t.right);  //右子节点入队列
			}
		}
		return count;
    }
}