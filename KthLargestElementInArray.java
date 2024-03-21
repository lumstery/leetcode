class Solution {
    public int findKthLargest(int[] nums, int k) {
        // Create a min heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Iterate through the array
        for (int num : nums) {
            // Add current element to the heap
            minHeap.offer(num);

            // If the size of the heap exceeds k, remove the smallest element
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // The root of the heap will be the kth largest element
        return minHeap.peek();
     }
}
/*
Approach:  Use PriorityQueue as min head, where root contains min value 
by maintaing certain size of k for min-heap with removal of min values whenever
size reaches limit, we will achieve situation where k-th largest element in in the root

Time complexity :  O(n log k) 
where n is the number of elements in the array 
and k is the given value. 

This is because each insertion and deletion operation on the priority queue takes O(log k) time, and we perform these operations n times.

Space complexity: O(k)
*/
