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
    int maxValue;
    
    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        traverse(root);
        return maxValue;
    }
    
    
    public int traverse(TreeNode node){
        if(node == null)  return 0;
        
        int leftVal = Math.max( 0, traverse(node.left) );
        int rightVal = Math.max( 0, traverse(node.right) );
        
        maxValue = Math.max(maxValue, node.val + leftVal + rightVal);
        return Math.max(leftVal, rightVal) + node.val;
    }
}
/*
 Approach: we use post-order traversal here 
 for each node, we calculate max on the path that starts from left child
 then we calculate max value of path that starts from right node

 and only then we visic current node
 we are trying to update our maxValue if the max_left_path_value and
 max_right_path_value combined with current node value is bigger than current maxValue ever seen.
So in other words we calculate the path that crosses through our node.

And then we return maximum of max_left_path_value and max_right_path_value 
plus our current node value to be able to pass back to caller function the
potential biggest path that starts from our node.

Eventually, traversing from bottom to top we always know, 
left  sub-tree max path 
right sub-tree max path 
current node value

we pass max path originating in the node backwards, for backtracking purposes (path  can go to the left or to  the right direction)

and knowing max_path for left and max_path for right, we can 
calculate max_path for the node itself and compare with global max value.
 

Time Complexity: O(n)
Space Complexity: O(n) 

Key Points of the Approach:
Post-Order Traversal: You correctly identified that the algorithm utilizes a post-order traversal strategy. In post-order traversal, a node is processed after its left and right subtrees have been processed. This allows the algorithm to have all necessary information from both subtrees to compute the maximum path sum involving the current node.

Max Path Calculation: At each node, the algorithm calculates two things:

The maximum path sum including the current node, which could potentially span from the left subtree through the node to the right subtree. This value is compared with maxValue to track the maximum path sum found in the entire tree.
The maximum sum of a path starting from the current node and going up, which is passed back to the caller. This is used to possibly update the maximum path sum in the parent nodes.
Max Value Update: The global variable maxValue is used to keep track of the highest path sum encountered during the traversal of the tree. This ensures that, regardless of the tree's structure, the maximum sum is accurately captured and returned after the entire tree is traversed.

Handling Negative Paths: By using Math.max(0, traverse(node.left)) and Math.max(0, traverse(node.right)), the algorithm effectively ignores any negative path sums, as incorporating them would only decrease the overall path sum. This ensures that only paths which contribute positively to the overall sum are considered.

Time and Space Complexity:
Time Complexity (O(n)): Each node in the tree is visited exactly once, leading to a linear time complexity relative to the number of nodes in the tree.

Space Complexity (O(n)): The space complexity is primarily due to the recursion stack. In the worst case (a completely unbalanced tree), the height of the tree could be O(n), leading to a space complexity of O(n). In a balanced tree, the height would be O(log n), resulting in a better space complexity. However, the worst-case space complexity remains O(n).
*/
