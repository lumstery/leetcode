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
    public TreeNode invertTree(TreeNode root) {
        LinkedList<TreeNode> q = new LinkedList<TreeNode>();

        if(root != null){
            q.add(root);
        }
        
        // Loop till queue is empty...
        while(!q.isEmpty()){
            // Dequeue front node..
            TreeNode node = q.poll();

            if(node.left != null){
                q.add(node.left);
            }

            if(node.right != null){
                q.add(node.right);
            }

            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

        }
        
        return root;
    }
}
/*
 Approach: iterative DFS 

  we add root to queue, and then poll nodes every iteration untill we have some
  
  we process every node in the way that we
    1. add left/right childs to queue
    2. swap left and right pointers

  Thus, due to the FIFO nature of queue we will always move to dephs first,
  traversing sub-trees to the deepest left child node 
  and then moving one level up and processing rigth child node.

 Time Complexity: O(n)
 Space Complexity: O(n)
*/
