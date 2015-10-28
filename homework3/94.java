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
    public List<Integer> inorderTraversal(TreeNode root) {
       	TreeNode t=root;
	        List<Integer> list=new ArrayList<Integer>();
	        Stack<TreeNode> s=new Stack<TreeNode>();  //初始化栈
	        if(root==null)
	        	return list;
	        s.push(root);   //放入根节点
	        while(!s.empty()){  //栈中无元素，遍历结束
	        	while(t.left!=null){   //左孩子节点全入栈
	        		s.push(t.left);
	        		t=t.left;   //指向最后一个左孩子节点
	        	}
	        	if(t.right==null){  //无右节点
	        		list.add(t.val);  //读数据
	        		s.pop();  //出栈
	        		if(!s.empty()){  //遍历完一个子树而栈已经空了，说明整个树都遍历完了
	        		t=s.peek();  
	        		t.left=null;  //左节点已经访问过，置空
	        		}
	        		else 
	        			return list;
	        	}
	        	else{  //有右节点
	        		list.add(t.val);   //读数据
	        		s.pop();   //出栈
	        		s.push(t.right);  //右节点入栈
	        		t=t.right;
	        	}
	        }
	        return list;
    }
}