class Solution {
     public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];
        
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return slow;
    }
}
/*
 Approach: Fast-Slow Pointers Approach 
 We treat our array as linked list, where each  value nums[i] represents 
 to which node there is a connection in virtual linked list.
 Once slow and fast pointers meet, we know that we catched a cycle, thus, we know that in the cycle there are two duplicate elements.
 Then we proceed moving both pointers with the same speed, and once
 they meet again we know that we found the beginning of the cycle, thus
 the element which is duplicated.
 Time complexity: O(n)
 Space complexity: O(1)
*/
