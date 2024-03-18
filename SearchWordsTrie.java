class WordDictionary {
    private TrieNode root;

    public WordDictionary() {
        this.root = new TrieNode();
        this.root.val = ' ';
    }
    
    public void addWord(String word) {
        TrieNode cur = root;

        for(int i = 0; i < word.length(); i++){
            char curChar = word.charAt(i);
            int charIndex =  curChar - 'a';

            if(cur.children[charIndex] == null) {
              TrieNode newNode = new TrieNode();
              cur.children[charIndex] = newNode;
              newNode.val = curChar;
            }
            cur = cur.children[charIndex];
        }
       cur.isEndOfWord = true; 
    }
    
    public boolean search(String word) {
        return traverse(root, word, 0);
    }

    private boolean traverse(TrieNode node, String word, int startIndex){
        if(startIndex == word.length())
           return node.isEndOfWord;
       

       char curChar = word.charAt(startIndex);
       
       if(curChar == '.') {
         for(int i = 0; i < 26; i++){
            if(node.children[i] != null && traverse(node.children[i], word, startIndex + 1)){
                return true;
            }
         }
         return false;
       } else {
        int charIndex = curChar - 'a'; 
        if(node.children[charIndex] == null) return false;
        return traverse(node.children[charIndex], word, startIndex + 1);
       }
    }

    private static class TrieNode{
        char val;
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord;

        public TrieNode(){}
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
 /*
 Approach: We have a regular prefix tree, trie, that is implemented with the 
 TrieNode class to signify letter of each inserted word, each node has 26 references to it's possible children that might be filled as continuations of inserter words.
 Each node also has flag to mark end of inserted word, and distinguish complete words from in-complete words, that are just a part of another words;

 For search, where we have wildcard.  we are using DFS to traverse all possible continuations and seek for the word that matches wildcard.

The time and space complexity of this implementation depend on the number of words added to the WordDictionary and the length of the words.

Time Complexity:

Adding a word to the WordDictionary: The time complexity of adding a word is O(L), where L is the length of the word. This is because we traverse the trie for each character of the word, and for each character, we perform constant-time operations.
Searching for a word in the WordDictionary: The time complexity of searching for a word is O(L * N), where L is the length of the word and N is the number of nodes in the trie. This is because we need to traverse the trie from the root to the end of the word, which could involve traversing up to L levels, and at each level, we might have to check up to 26 child nodes (for each letter of the alphabet). However, in practice, the search time may be less than O(L * N) if the word being searched for does not exist in the trie, as we can terminate the search early.
Space Complexity:

The space complexity of this implementation is O(N * M), where N is the total number of characters across all words inserted into the WordDictionary, and M is the average length of the words. This is because we are using a trie data structure to store the words, and each node in the trie corresponds to a character. Since we are storing characters in the trie, the space complexity depends on the total number of characters in all the words.
Additionally, each node in the trie contains an array of size 26 to store child nodes, which contributes to the space complexity.

 */
