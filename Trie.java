class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
        root.val = ' ';
    }
    
    public void insert(String word) {
        TrieNode cur = root;

        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);

            if(cur.children[c - 'a'] == null){
                cur.children[c - 'a'] = new TrieNode(c);
            }

            cur = cur.children[c - 'a'];
        }
        cur.isWord = true;
    }
    
    public boolean search(String word) {
        TrieNode cur = root;

        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(cur.children[c - 'a'] == null) return false;
            cur = cur.children[c - 'a'];
        }

        return cur.isWord;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode cur = root;

        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(cur.children[c - 'a'] == null) return false;
            cur = cur.children[c - 'a'];
        }

        return true;
    }

    private static class TrieNode {
        public char val;
        public boolean isWord;
        public TrieNode[] children = new TrieNode[26];

        public TrieNode(){
        }

        public TrieNode(char c){
            TrieNode node = new TrieNode();
            node.val = c;
        }
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
 /*
  Approach: To implement Trie we have Trie Node that
  has a character and 26 references to subsequent characters
  
  We have one root node and we fill it with strings in the 
  way that each character is represented as a TrieNode, and
  each subsequent character of the words that are inserted, is 
  added to the array of children nodes.

  Such structure allows us to store words that start with the same 
  string without duplicating of space.

  We also have property isWord that is used to determine whether some
  node is a last character for word that was inserted, or it's just a
  inner character node that is container is some word.

  The time and space complexity of operations in the Trie data structure implementation are as follows:

Insertion (insert method):

Time Complexity: O(m), where m is the length of the word being inserted. This is because we iterate through each character of the word, and for each character, we perform constant time operations.
Space Complexity: O(m), where m is the length of the word being inserted. This is because we store each character of the word in a TrieNode, and the number of nodes created corresponds directly to the length of the word.
Search (search method):

Time Complexity: O(m), where m is the length of the word being searched. Similar to insertion, we iterate through each character of the word, performing constant time operations at each step.
Space Complexity: O(1). Searching does not require any additional space proportional to the input size. It only involves traversal of existing nodes.
StartsWith (startsWith method):

Time Complexity: O(p), where p is the length of the prefix being searched. Similar to search, we iterate through each character of the prefix, performing constant time operations at each step.
Space Complexity: O(1). Similar to search, no additional space is required beyond the input size.
Space Complexity of the Trie Data Structure:

The space complexity of the Trie data structure depends on the number of unique words inserted into it and the average length of those words.
Assuming there are n words inserted into the Trie and the average length of the words is m, the space complexity would be O(n * m).
In the worst case scenario where there are no common prefixes among the words, the space complexity could approach O(n * m).
In summary:

Insertion, search, and startsWith operations have a time complexity of O(m) where m is the length of the word or prefix.
The space complexity of the Trie data structure depends on the number of unique words inserted and their average length, which is O(n * m), where n is the number of words and m is the average length of the words.

 */
