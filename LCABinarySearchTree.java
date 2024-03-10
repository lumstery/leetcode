class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        int rootVal = root.val;
        int pVal = p.val;
        int qVal = q.val;

        // both p and q should be in right sub-tree 
        // as node.right.val > node.val in BST
        if (pVal > rootVal && qVal > rootVal) {
            return lowestCommonAncestor(root.right, p, q);
        // both p and q should be in left sub-tree 
        // as node.left.val < node.val in BST    
        } else if (pVal < rootVal && qVal < rootVal) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            // Current root is the LCA
            // if neither of above conditions match
            return root;
        }
    }
}
/*
  Approach: Recursive search of such node that will satisfy condition
  In BST tree we know that left value is smaller , right is bigger , always;

  thus, if both p and q are > than node.val  -> we have to dig deeper to the right
  if p and q are < than node.val  -> we have to dig deeper to the left
 
  otherwise we have a node that satisfies LCA criteria
  as in remaining possible scenarious 
     1. p and q are either == node, and another node has to be either in the left or rigth sub-tree
     2. node.left contains min(p.val, q.val)
        and node.right contains max(p.val, q.val)

    Time Complexity: O(h) where h is height of the tree
    Space Complexity: O(1)

*/ 
