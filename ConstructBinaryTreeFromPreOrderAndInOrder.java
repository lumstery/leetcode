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
    int preorderIndex;
    Map<Integer, Integer> inorderIndexMap;
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        // build a hashmap to store value -> its index relations
        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return arrayToTree(preorder, 0, preorder.length - 1);
    }

    private TreeNode arrayToTree(int[] preorder, int left, int right) {
        // if there are no elements to construct the tree
        if (left > right) return null;

        // select the preorder_index element as the root and increment it
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);

        // build left and right subtree
        // excluding inorderIndexMap[rootValue] element because it's the root
        root.left = arrayToTree(preorder, left, inorderIndexMap.get(rootValue) - 1);
        root.right = arrayToTree(preorder, inorderIndexMap.get(rootValue) + 1, right);
        return root;
    }
}
/*
 Approach: We initialize inorderIndexMap, where we map values 
 from inorder array to correspoinding indices, this map will be 
 necessary for continuous splitting of inorder array to left and right sub arrays
 that represent left and right sub-tree for any given node.

 We use divide and conquer pattern here to divide arrays to sub-arrays, and 
 then again sub-arrays will be divided , and so on, untill there is no values left.

 So in preorder we have root-left-right order, and root of our tree is
 basically the first value in preorder array, then 2nd value is left child and 3rd 
 value is right child.

 We have global variable pointer that we use for picking of values from preorder
 array during recursive calls to evaluate left sub-tree and right sub-tree that
 are running in the context of inorder array.

 If  for any execution of our recursive call, there is at least one
 element, then we will form the node with next value from preorder array,
and will try to evaluate left and right sub-trees.

Left sub-tree should be taken from all elements that are left to the index of 
our given element in the inorder array, and right sub-tree will consist of
value that are located to the right relatively to our current element.

Our recursive function is written in the pre-order way,
 becuase it visits the node first and then processes left and right nodes,
 that is why while recursively traversing in-order space for correct forming
 of call-stack executions and boundaries, we still use values from pre-order 
 array to fill the nodes.

 the function adheres to a preorder traversal pattern not just in visiting nodes but also in how it uses the preorder array to determine the sequence of node processing. This alignment with preorder traversal ensures that the tree is reconstructed correctly from the given traversals. 

Time Complexity: O(n)
Space Complexity: O(n)
*/
