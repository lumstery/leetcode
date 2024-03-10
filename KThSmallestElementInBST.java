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
    private int result = 0;
    private int count = 0;

    public int kthSmallest(TreeNode root, int k) {
        count = k;
        traverse(root);
        return result;
    }

    public void traverse(TreeNode n){
        if(n == null) return;
        if(n.left != null) traverse(n.left);
        count--;
        if(count == 0){
            result = n.val;
            return;
        }
        if(n.right != null) traverse(n.right);
    }
}
/*
  Approach: In order DFS  traversal / recursive
  
  As we have BST, smallest element will be always the left-most element,
  2nd smallest will be on 2nd left-most position.

  So with in-order traversal we dig down to left childs untill we reach end,
  then we visit current node ( decrement counter )
  and checking if counter is == 0 , in other words we've reached k-th left-most element.
  then after visiting current element we move to right child, and proceed digging down to left-most until we reach leaf 

  Time Complexity: O(n) where n is number of nodes
  Space Complexity: O(h) where h is heigh of the tree ( recur stack size )

*/
