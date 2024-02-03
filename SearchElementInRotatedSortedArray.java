class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        if (nums.length == 1) return (nums[0] == target) ? 0 : -1;

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) { 
            int mid = start + (end - start) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] < nums[end]) {
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else {
                if (nums[start] <= target && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }

        return -1;
    }
}
/*
 Approach: We use binary search to find element by first checking which part of array is sorted 
 and then by checking if target could be found in that part by logic 
 nums[start] <= target <= nums[end]
 if this codition is not satisfied then it means that target is for sure in another unsorted half  of array

 Time complexity: O(n)
 Space complexity: O(1)
*/
