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
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        
        HeightDiam rootNode = traverse(root);

        return rootNode.diameter;
    }

    private HeightDiam traverse(TreeNode node){
        if(node == null) return new HeightDiam(-1, 0);

        HeightDiam left = traverse(node.left);
        HeightDiam right = traverse(node.right);

        int height = Math.max(left.height, right.height) + 1;
        int diameter = Math.max(
            Math.max(left.diameter, right.diameter),
            (left.height + right.height + 2) 
        );

        return new HeightDiam(height, diameter);
    }

   private static class HeightDiam{
        int height;
        int diameter;
        
        public HeightDiam(int height, int diameter){
            this.height = height;
            this.diameter = diameter;
        }
   }
}
/*
 Approach: DFS with recursion that backtracks height of every node 
 + max diameter of node's child sub-trees
 
 When moving upwards we increase height by +1, at each level to the top
 and also we are comparing diameter of the sub-tree that includes current node
 vs max diameters of each left/right sub-trees. 

Time complexity: O(n)
Space complexity: O(n)

*/
