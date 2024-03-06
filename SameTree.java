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
    public boolean isSameTree(TreeNode p, TreeNode q) {
       // If both are null, they are the same
        if (p == null && q == null) return true;
        
        // If one is null and the other isn't, they are not the same
        if (p == null || q == null) return false;
        
        // If the values of the current nodes are not the same, trees are not the same
        if (p.val != q.val) return false;

        boolean left = isSameTree(p.left, q.left);
        boolean right = isSameTree(p.right, q.right);

        return left && right;
    }
}
/*
 Approach: Recusion that traverses both trees at the same time and compares node by node
 Time Complexity: O(n)
 Space Complexity: O(h) stack size equal to height of tree
*/
