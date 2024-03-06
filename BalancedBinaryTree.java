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
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;

        return traverse(root).balanced;
    }

    public HeightBalanced traverse(TreeNode root) {
        if(root == null) return new HeightBalanced(-1, true);

        HeightBalanced left = traverse(root.left);
        HeightBalanced right = traverse(root.right);
        
        int height = Math.max(left.height, right.height) + 1;
        boolean childsBalanced = left.balanced && right.balanced;

        boolean curBalanced = childsBalanced && ( Math.abs(left.height - right.height) <= 1);

        return new HeightBalanced(height, curBalanced);
    }

    private static class HeightBalanced{
        int height;
        boolean balanced;

        public HeightBalanced(int height, boolean balanced){
            this.height = height;
            this.balanced = balanced;
        }
    }
}
/*
 Approach: DFS recursive  post-order traversal with backtracking, we are backtracking 
  heights of left/right subtrees and if they were balanced, then with
  the data about heights/balanced of childs we are identifying if current node is
  balanced and increment current node' height by 1

  Time Complexity: O(n)
  Space Complexity: O(n) call stack
*/
