class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] result = new int[n - k + 1]; // there will be n - k + 1 windows 
        int resultIndex = 0;

        // will store indices of values, top will store index of max value for current window
        Deque<Integer> deq = new LinkedList<>();

        for (int i = 0; i < n; i++) {
        // Maintain a monotonically decreasing queue by removing 
        // elements smaller than the current element from the back
            while (!deq.isEmpty() && nums[deq.peekLast()] < nums[i]) {
                deq.pollLast();
            }

            

            // Add the current index to the back of the deque
            deq.offer(i);

            // If the index of the current max-value is out of the boundaries
            // of the current window, remove it from the front

            if(deq.peek() <= i - k){
                deq.poll();
            }

            // Add the current maximum for the current window to the result array
            // The front of the deque is going to contain the index of the maximum value
            if (i >= k - 1) {
                result[resultIndex++] = nums[deq.peek()];
            }
            
        }
     return result;
    }
}
/*
 Approach: Move sliding window and use Monotonicall Decreasing Deque for storing of 
index of maximum value at the top of the queue

Before adding any index (that corresponds to some value) to the back of the queue, we will remove all indices from the back of the queue that reference to elements that are smaller than our current value. This will let us achieve nature of monotonically decresing queue.

At each iteration we will check if our index at the top of the queue ( max-value index)
is out of bondaries for current window, if it is then we will poll this index from the top.

Finally we assign value that corresponds to the index from  the top of the queue as a maximum for 
any given window that we are scanning.

Time Complexity: O(n) , as adding and removing values from queue is O(1) and K is always < than N
Space Complexity: O(n - k + 1)

*/
