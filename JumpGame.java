class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        
        int maxReach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) return false; // If the current index is not reachable from any prev pos
            maxReach = Math.max(maxReach, i + nums[i]); // Update the farthest reachable index
            if (maxReach >= nums.length - 1) return true; // If we can reach the last index
        }
        
        return false; // If we cannot reach the last index
    }
}
/*
 Approach: 
 We use Greedy approach, to find max_reach point for every index.
 
 for every position, we are checking what is maximum reachable poin at the moment,
 it might be a jump from current position that allows us to reach furthest point, or it might
 be any jump in the past that allows us to reach furthest point.
 So we track max reachable point at every iteration untill our reachability allows us to reach last index.

 If we will be in the situation when we reach such index that is not reachable from any point in
 the past, it means that we cannot cross this "valley" and thus we will not be able to reach last index.

 Time Complexity: O(n)
 Space Complexity: O(1)

 In the case of the "Jump Game" problem, at each position in the array, the algorithm greedily chooses the maximum reachable index (maxReach) based on the current position and the maximum jump length from that position. It doesn't look ahead or consider future possibilities; instead, it makes the locally optimal choice at each step by maximizing the reach. This greedy approach ultimately leads to a correct solution for determining whether it's possible to reach the last index.

*/
