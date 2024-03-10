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
    int nodeCount;

    public int goodNodes(TreeNode root) {
        if(root == null) return 0;

        traverse(root, root.val);

        return nodeCount;
    }

    public void traverse(TreeNode node, int max){
        if(node == null) return;

        traverse(node.left, Math.max(node.val, max));
        traverse(node.right, Math.max(node.val, max));

        if(node.val >= max){
            nodeCount++;
        }
    }
}
/*
 Approach: DFS traversal with carying of max value on the path 
  and then if node.val >= than max_value so far reached on the path
  we increment counter as we treat such node as "good".
  Time Complexity: O(n) as we visit every node
  Space Complexity: O(h) where h is heigh of tree, due to recursive nature of the solution and stack size
*/
