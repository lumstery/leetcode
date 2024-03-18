class Solution {
   
    public List<String> findWords(char[][] board, String[] words) {
      TrieNode root = new TrieNode();
      Set<String> result = new HashSet<>();

      // Build the Trie
      for (String word : words) {
         insert(root, word);
      }

     int m = board.length;
     int n = board[0].length;

     for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            dfs(board, i, j, root, "", result);
        }
     }
    
      return new ArrayList<>(result);
    }

      private void dfs(char[][] board, int i, int j, TrieNode node, String current, Set<String> result) {
        // check boundaries
        // check if node was not visited yet
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == '#') {
            return;
        }

        char letter = board[i][j];
        TrieNode nextNode = node.children[letter - 'a'];

        // check if trie contains letter from this traversal
        if (nextNode == null) {
            return;
        }

        // if Trie  contains letter, we append it to current string that we carry for 
        // following adding to the result set 
        current += letter;

        // if the node that we have reached is signifying the end of certain word
        // we can add it to the result list, as we were able to find such path
        // on our cross-words matrix that matches some word that exists in the Trie structure
        
        if (nextNode.isWord) {
            result.add(current);
            // Avoid duplicate results 
            // As this path in trie might be found in another traversal in future
            // we mark this node as not the one that completes the word
            // as this word was already found and added to the result list
            nextNode.isWord = false;
        }

        board[i][j] = '#'; // mark as visited to avoid infinite loop

        // move dfs to all directions
        dfs(board, i + 1, j, nextNode, current, result);
        dfs(board, i - 1, j, nextNode, current, result);
        dfs(board, i, j + 1, nextNode, current, result);
        dfs(board, i, j - 1, nextNode, current, result);

        board[i][j] = letter; // backtrack
    }
   
    private void insert(TrieNode root, String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.isWord = true;
    }

    private class TrieNode{
        char val;
        TrieNode children[] = new TrieNode[26];
        boolean isWord;

        public TrieNode(){

        }
    }
}
/*
 Approach: Build Trie from the list of all words that we are looking for
  
 Then for every cell in the matrix, we are starting DFS recursion

 in DFS recursion, we do the following:
  1. check boundaries, if board element is  not visited yet in scope of current dfs
  2. check if there is continuation/path in the Trie for the given character 
    2.1 If there is path it means that we start forming some potential word
  3. If path in Trie is found, append current character to string 'current'
    3.1 this string is accumulator for the future adding to the result set
  4. If current visited node in Trie is signifying a word, voila we have found some word during dfs traversal of our cross-words matrix 
    4.1 now we can simply add it to the result set
    4.2 there is optimization to eliminate duplicates, where we mark node in a Trie as non-word, so that it would be skipped in future traversals
    4.3 this optimization is not useful for HashSet but would be neccessary for list
  5. Mark current cell in matrix as visited
     5.1 traverse to all 4 directions
     5.2 un-mark cell back, for further work on the matrix
     5.3 this is common practice in dfs traversals of the matrices  


Time Complexity:
  Construct Trie: O(W)
  DFS Traversal: O(m * n * 4^L)

Space Complexity:
  Trie: O(W)
  DFS Stack: O(L)
In this representation:

W represents the total number of characters in all the words.
m and  n represent the dimensions of the board.
L represents the length of the longest word in the trie.
     
*/
