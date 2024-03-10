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
    public List<List<Integer>> levelOrder(TreeNode root) {
        
        List<List<Integer>> result = new ArrayList<>();
        
        if(root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while(!queue.isEmpty()){
            
            int elementsOnLevel = queue.size();
            List<Integer> subList = new LinkedList<Integer>();
            
            for(int i=0; i < elementsOnLevel; i++) {
                if(queue.peek().left != null) queue.offer(queue.peek().left);
                if(queue.peek().right != null) queue.offer(queue.peek().right);
                subList.add(queue.poll().val);
            }
            
            result.add(subList);
        }
        
        return result;
    }
    
}
/*
  Approach: We use BFS here, we push root node to queue
  and then level by level we iterate through a queue untill it's empty
   1. queue size determines how many elements to process on current level
   2. add left and right to the queue so that next level is added for next iteration
   3. poll value of visited element and add it to the list containing level's values
  
  We return filled list of lists as result;
  
  The time complexity of this algorithm is O(n), where n is the number of nodes in the binary tree, as each node is visited once. 
  
  The space complexity is O(m), where m is the maximum number of nodes at any level in the binary tree (the maximum width of the tree at any level).
*/
