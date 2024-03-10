/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    private TreeNode lca;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        traverse(root, p, q);
        return lca;
    }

    private Container traverse(TreeNode root, TreeNode p, TreeNode q){
        if(root == null) return new Container(false, false);

        Container left  = traverse(root.left, p, q);
        Container right = traverse(root.right, p, q);

        boolean qFound = (root.val == q.val) || left.qFound || right.qFound;
        boolean pFound = (root.val == p.val) || left.pFound || right.pFound;
        
        if(qFound && pFound && lca==null){
            lca = root;
        }

        return new Container(qFound, pFound);
    }

    private static class Container{
        boolean qFound;
        boolean pFound;

        public Container(boolean qFound, boolean pFound){
            this.qFound = qFound;
            this.pFound = pFound;
        }
    }

}
/*
 Approach: DFS with back-wards propagation of flags qFound and pFound
 that are calculated in a way that if 
    currentNode is q or has q as child then qFound = true
    currentNode is p or has p as child then pFound = true

 once we reach first node from the bottom that has both qFound and pFound
 we can treat it as lca for both q and p;

 Time Complexity: O(n) - where n is number of nodes
 Space Complexity: O(1) - as we don't use extra space 

*/
