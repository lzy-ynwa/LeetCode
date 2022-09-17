package Solution;

public class Solution124 {
    public static void main(String[] args){
        TreeNode node = new TreeNode(-3);
        System.out.println(maxPathSum(node));
    }

    public static int maxPathSum(TreeNode root) {
        TreeNodeWithSum newRoot = transform(root);
        return maxPathSum(newRoot);
    }

    private static int maxPathSum(TreeNodeWithSum root){
        if(root == null){
            return Integer.MIN_VALUE;
        }

        int sumWithNode = root.val;
        if(root.left != null && root.left.sum > 0){
            sumWithNode += root.left.sum;
        }
        if(root.right != null && root.right.sum > 0){
            sumWithNode += root.right.sum;
        }
        int leftSum = maxPathSum(root.left);
        int rightSum = maxPathSum(root.right);
        int sumWithoutNode = Math.max(leftSum, rightSum);
        return Math.max(sumWithoutNode, sumWithNode);
    }

    private static TreeNodeWithSum transform(TreeNode node){
        if(node == null){
            return null;
        }

        TreeNodeWithSum left = transform(node.left);
        TreeNodeWithSum right = transform(node.right);
        TreeNodeWithSum cur = new TreeNodeWithSum(node.val);
        cur.left = left;
        cur.right = right;
        if(node.left == null && node.right == null){
            cur.sum = cur.val;
        }else if(node.left == null){
            cur.sum = Math.max(cur.val, right.sum + cur.val);
        }else if(node.right == null){
            cur.sum = Math.max(cur.val, left.sum + cur.val);
        }else{
            cur.sum = Math.max(cur.val, Math.max(left.sum, right.sum) + cur.val);
        }
        return cur;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static class TreeNodeWithSum {
        int val;
        int sum;
        TreeNodeWithSum left;
        TreeNodeWithSum right;

        TreeNodeWithSum(int val){
            this.val = val;
        }
    }
}
