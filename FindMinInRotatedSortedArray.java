class Solution {
  public int findMin(int[] nums) {
    int start = 0;
    int end = nums.length - 1;

    while (start < end) {
      final int mid = start + (end - start) / 2;
      if (nums[mid] < nums[end]) // right side is sorted so we can drop it
        end = mid;
      else
        start = mid + 1; // left side is sorted so we can drop it
    }

    return nums[start];
  }
}
/*
 Approach:  We use binary search to get O(log n) time complexity
 If array is rotated then one half of it will be sorted and another part
 ( where there is min element along with max element together ) 
 will be unsorted.
 
 if nums[mid] > nums[end] || nums[mid] < nums[start]
 then we can safely assume that whole sub-array is unsorted 

 so we reduse our search space to the part that is unsorted

 Time complexity: O(n log n )
 Space complexity: O(1)

*/
