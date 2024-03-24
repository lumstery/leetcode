class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        
        for(int i=0; i <n; i++){
           sum += nums[i];
           max = Math.max(sum,max);
           if(sum<0) sum = 0;
        }
        return max;
    }
}
/*
 Approach: Use Kadane's algorithm to dynamically track possible sums
 of any sub-array, when some value contributes in the way that sum starts to be
 negative, we reset sum to 0 and move on.

 When we have negative numbers, like [-1, -2, -3] all of them will be checked
 individually and we will store -1 as a max possible sub-array that consists of
 just one element.

So we will have following:
Iteration 1:
sum: 0
max: -1
Iteration 2:
sum: 0
max: -1
Iteration 3:
sum: 0
max: -1

in other words, if any negative number is found we might have 2 situations:
 1. sum = 0  (e.g. first iteration, or iteration after another negative number)
    1.1 if it's first iteration, we try to update max with current negative number
        as it still might be  largest out of all elements
    1.2 if previous number  was negative, and again we have negative it only makes sense to try to update max and reset sum to zero again, as we don't want to combine negatives, as if we did this eventually we will end-up with even lesser sum than our negatives individually. So we just treat them individually.
 2. sum > 0 
    2.1 previous iterations all combined did not lead to negative direction
        so we just proceed "growing" our sum and update max variable to track peaks   
    2.2 if any number contributes to our sum in the way that we start having negative sum,
        it means that current sub-array has reached it's max value somewhere in the past iterations
        2.2.1 and we can no longer expect it "grow" even more after current element, as we cannot throw away a part of sub-array that leads to negativity, and it only makes sense to start combining a new sum starting from 0.
        2.2.2 This is due to the fact that we cannot skip elements in sub-array, we can only expand our sub-array boundaries, but if sub-array goes negative at certain point, it's a sign for us to start exploring new area which may bring us higher max sum without our explored sub-array that certainly leads to negative direction.

Time Complexity: O(n)
Space Complexity: O(1)        
*/
