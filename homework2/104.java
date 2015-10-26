
public class TreeNode {
	TreeNode left;
    TreeNode right;
    int val;
    TreeNode(int val){
        this.val=val;
    }
    //���ض����������
    static int getDepth(TreeNode root){
        if(root==null){
            return 0;
        }
        int left=getDepth(root.left);
        int right=getDepth(root.right);
        return left>right?left+1:right+1;
    }
 
    
    public static void main(String[] args) 
    {
        TreeNode root=new TreeNode(1);
        TreeNode left1=new TreeNode(2);
        TreeNode left2=new TreeNode(3);
        TreeNode right1=new TreeNode(4);
        //����һ����
        root.left=left1;
        left1.right=left2;
        root.right=right1;
       
        System.out.println("��������ǣ�"+getDepth(root));
    }
         
}
