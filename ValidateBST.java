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
    public boolean isValidBST(TreeNode root) {
        return traverse(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean traverse(TreeNode root, long lower, long upper){
       if(root == null) return true;

       int val = root.val;
       
       if (val <= lower || val >= upper) {
            return false;
       }

       boolean leftValid = traverse(root.left, lower, val);
       boolean rightValid = traverse(root.right, val, upper);

       return leftValid && rightValid;
    }
}
/*
 Approach: DFS traversal where for every node, we check 
 that  its value
   1. always < than upper limit 
   2. always > than lower limit

 Then for left sub-tree we are making sure that it's smaller than current el
    -> by passing current element's value as upper boundary

 For right sub-tree we are making sure taht it's bigger tahn current el
    -> by passing current element's value as lower boundary 

 Time Complexity: O(n) where n is nuber of nodes
 Space Complexity: O(h) where h is height of the tree 
*/
