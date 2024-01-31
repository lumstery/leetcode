class Solution {
    public int search(int[] nums, int target) {
        return doSearch(nums, 0, nums.length - 1, target);
    }

    public int doSearch(int[] nums, int start, int end, int target){
        if(start <= end){
            int mid = (start + end) / 2;
            if(nums[mid] == target ){
                return mid;
            }else if (nums[mid]> target){
                return doSearch(nums, start, mid - 1, target);
            }else{
                return doSearch(nums, mid + 1, end, target);
            }
        } else {
            return -1;
        }
    }
}
/*
 Approach: We use recursive calls with logic

 Initialize left and right pointers to the beginning and end of the array, respectively.

While the left pointer is less than or equal to the right pointer:

a. Calculate the middle index as the average of the left and right pointers.
b. If the middle element is equal to the target, return the index of the middle element.
c. If the middle element is less than the target, update the left pointer to mid + 1.
d. If the middle element is greater than the target, update the right pointer to mid - 1.

If the target is not found, return -1.

Complexity
Time complexity: O(log n) - Since binary search reduces the search space by half at each step, the maximum number of iterations required to find the target is log base 2 of n, where n is the size of the array. Therefore, the time complexity of binary search is O(log n)).

Space complexity: O(1) - Binary search only uses a constant amount of additional space for the two pointers and the middle index variable, so the space complexity is O(1).
*/
