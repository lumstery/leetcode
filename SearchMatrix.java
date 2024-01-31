class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int cols = matrix[0].length;
        int start = 0;
        int end = matrix.length * cols - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            int r = mid / cols;
            int c = mid % cols;
            if (target == matrix[r][c]) {
                return true;
            }
            if (target < matrix[r][c]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return false;
    }

}

/*
   Approach : We are doing binary search by the logic where we calculate number of cells in matrix, then we adjust either start or end accordingly 

    int r = mid / cols;
    int c = mid % cols;

    helps to convert linear index to row and col in matrix

   Time Complexity: O(log(R * C))
   Space Complexity:  O(1)
*/
