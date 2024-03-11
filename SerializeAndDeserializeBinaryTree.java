/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    private String spliter = ",";
    private String na  = "x";
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder result = new StringBuilder();
        traverseAndSerialize(root, result);
        
        return result.toString();
    }

    public void traverseAndSerialize(TreeNode node, StringBuilder sb){
        if (node == null) {
            sb.append(na).append(spliter);
        } else {
            sb.append(node.val).append(spliter);
            traverseAndSerialize(node.left, sb);
            traverseAndSerialize(node.right,sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null) return null;
        if(data.isEmpty()) return null;
        return buildTree(new LinkedList<>(Arrays.asList(data.split(","))));
    }
    
    public TreeNode buildTree(Queue<String> elements){
        if(elements.isEmpty()) return null;
        
        String value = elements.poll();
        if(value.equals(na)) return null;
        
        TreeNode newNode = new TreeNode(Integer.parseInt(value));
        newNode.left = buildTree(elements);
        newNode.right = buildTree(elements);
        
        return newNode;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));

/*
 Approach: We traverse our tree in pre-order manner, and use
 string builder to concatenate all nodes with commma as delimiter.
 null values are represented as 'x'.
 
 After traversing of the tree we will have pre-order traversal represented 
 in the string.

 For deserialization, we have safety checks for empty tree
 then we split input string by delimiter and add all values to queue

 Then we have recursive function buildTree that passes queue through 
 all recursive calls, and each ivocation polls value from the queue
 untill it is empty

 if polled value is 'x' then we return null, otherwise we convert it to
 int and forming a new TreeNode, but before returning tree node 
 we also are calling buildTree function to form left and right sub-trees

 Because we pass our queue through entire call stack we have ability to 
 poll elements from it in the right order

 Our build-tree method is written in pre-order manner and our serialized
 tree was serilized in pre-order manner, thus we have ability to 
 restore the tree properly.

serialize
 Time Complexity: O(n) - every node has to be visited once 
 Space Complexity: O(n) // call stack and string builder 

deserialize
 Time Complexity: O(n) - every token from string has to be visited once 
 Space Complexity: O(n) // call stack and queue
*/
