/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
       if(root == null || subRoot == null) return false; 

       return traverse(root, subRoot);
    }

    private boolean traverse(TreeNode root, TreeNode subRoot){
        if(root == null) return false; 
        

        boolean left = traverse(root.left, subRoot);
        boolean right = traverse(root.right, subRoot);

        boolean isSame = isSame(root, subRoot);

        return (left || right) || isSame;
    }

    private boolean isSame(TreeNode t1, TreeNode t2){
        if(t1==null && t2==null) return true;
        if(t1==null || t2==null) return false;
        if(t1.val != t2.val) return false;

        boolean left = isSame(t1.left, t2.left);
        boolean right = isSame(t1.right, t2.right);
        
        return left && right;
    }
}
/* 
 Approach: Recursive DFS where we try to compare each sub tree with our target sub-tree
   Time Complexity: O(n * m) where n is number of node in base tree and m 
   is a size of target sub tree
   Space Complexity: O(n)
*/ 
