public class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root==null)   return true;
        if(abs(depth(root.left)-depth(root.right))>1 )  return false;
        return  isBalanced(root.left)&& isBalanced(root.right);
        
    }
    public int depth(TreeNode root){
        if(root==null)   return 0;
        return  max(depth(root.left),depth(root.right))+1;
    }
    public int max(int x,int y){
        return x>y?x:y;
    }
    public int abs(int x){
        return x>0?x:-x;
    }
}