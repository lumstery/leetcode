class Solution {
   public boolean isValidSudoku(char[][] board) {
            for (int i = 0; i < 9; i++) {
                Set<Character> rowSet = new HashSet<>();
                Set<Character> colSet = new HashSet<>();
                Set<Character> sectorSet = new HashSet<>();

                for (int j = 0; j < 9; j++) {
                    // Check rows
                    if (board[i][j] != '.' && !rowSet.add(board[i][j])) {
                        return false;
                    }
                    // Check columns
                    if (board[j][i] != '.' && !colSet.add(board[j][i])) {
                        return false;
                    }

                    // Check sectors
                    int sectorRow = 3 * (i / 3);
                    int sectorCol = 3 * (i % 3);

                    char value = board[sectorRow + j / 3][sectorCol + j % 3];
                    if (value != '.' && !sectorSet.add(value)) {
                        return false;
                    }
                }
            }
            return true;
        }
}
/*
   Approach: we have two params i,j that we loop through to generate 
   all posible combinations of 0..8 , check rows/columns/sectors with individaul sets
   Time complexity: O(n)
   Space complexity: O(1)
   
   for row we are checking whether char was ever present in rowSet using [i][j] in matrix
   for column we are checking whether char was ever present in colSet using [j][i] in matrix
    
   we are clearing rowSet and colSet for every new outer loop, thus we have guarantee that 
   we do not mix up characters from different rows/cols 
   
   for sectors we are using i,j to find all possible top-left positions [sX][sY] of all 9 sectors, row start index always starts on 0,3,6 and col start index on 0,3,6 so we are generating all combinations 

i  1 2 3 4 5 6 7 8 9
--------------------
sX 0 0 0 3 3 3 6 6 6 = 3 * (i / 3)
sY 0 3 6 0 3 6 0 3 6 = 3 * (i % 3)
--------------------   

after that once we have coordinates of top-left cell of sector we can check
each cell at [sX+hor_offset][sY+vert_offset]
where hor_offset and vert_offset are numbers 0,1,2 so we have to generate all possible combinations of [hor_offset][vert_offset] 

we can simply achieve this 

j  1 2 3 4 5 6 7 8 9
--------------------
hO 0 0 0 1 1 1 2 2 2 =  j / 3
vO 0 1 2 0 1 2 0 1 2 =  j % 3
-------------------- 


*/
