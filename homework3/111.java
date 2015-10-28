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
        int count=0;  //��¼����С���
		if(root==null)
        	return 0;
		TreeNode t;
		TreeNode lastNode=root;  //��־ÿ�����ڵ㣬���������
		Queue<TreeNode> q=new LinkedList<TreeNode>();
		q.add(root);
		while(!q.isEmpty()){
			t=q.poll();  //������
			if(t==lastNode){   //���ȡ������ĳ�����һ���ڵ�
				count++;  //������һ
				if(t.left==null&&t.right==null)   //�ҵ�Ҷ�ӽڵ�
					break;  //�˳�
			    if(t.left!=null){
				    q.add(t.left);  //���ӽڵ������
				    lastNode=t.left;  //�������ڵ�λ��
			}
			    if(t.right!=null){
				    q.add(t.right);  //���ӽڵ������
				    lastNode=t.right; //�������ڵ�λ��
			}
			}
			else{  //���ȡ���Ĳ���ĳ�����һ���ڵ�
				if(t.left==null&&t.right==null){  //�ҵ�Ҷ�ӽڵ�
					count++;
					break;  //�˳�
				}
			    if(t.left!=null)  
				    q.add(t.left);  //���ӽڵ������
			    if(t.right!=null)
				    q.add(t.right);  //���ӽڵ������
			}
		}
		return count;
    }
}