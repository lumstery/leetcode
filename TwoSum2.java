class Solution {
    public int[] twoSum(int[] numbers, int target) {

        int head = 0;
        int tail = numbers.length - 1;

        while (head < tail) {
            int sum = numbers[head] + numbers[tail];

            if (sum == target) {
                // The problem asks for 1-based indices, so add 1 to each index.
                return new int[]{head + 1, tail + 1};
            } else if (sum < target) {
                head++;
            } else {
                tail--;
            }
        }

        // No solution found.
        return new int[]{-1, -1};
    }
}
/*
 Approach: Two pointers from head and tail, we check if sum == 
 to our target, if no then in case if sum < target we move left pointer to get higher sum (as numbers are sorter in asc order), otherwise we move rigth pointer to the left, so that we can have less number as a sum
 Time complexity: O(n)
 Space complexity: O(1)
*/
