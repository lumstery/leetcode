class MedianFinder {
    // max heap , holds biggest element in 1st half
    private PriorityQueue<Integer> max;
    // min heap, holds smallest element in 2nd half 
    private PriorityQueue<Integer> min;

    public MedianFinder() {
        max = new PriorityQueue<>(Collections.reverseOrder());
        min = new PriorityQueue<>();
    }

    public double findMedian() {
      if(min.size() == max.size()){
         return (min.peek() + max.peek()) / 2.0;
       } else {
         return min.peek();
       } 
    }

    public void addNum(int num) {
       min.offer(num);
       max.offer(min.poll());

       // rigth half (min heap) should always be of equal or greater size to the left half (max heap) 
       if(min.size() < max.size()){
         min.offer(max.poll());
       }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
 /*
 Approach: 
The max heap (implemented with a priority queue in reverse order) stores the largest elements of the first half of the numbers.

The min heap (implemented with a priority queue) stores the smallest elements of the second half of the numbers.

The findMedian() method calculates and returns the median based on the sizes of the two heaps:
If both heaps have the same size, it returns the average of the top elements of both heaps.
If the sizes are different, it returns the top element of the min heap, as it represents the median in this case.

The addNum() method adds a new number to the appropriate heap (min), then balances the heaps by moving the smallest element from min to max if necessary.

Time Complexity:

Adding a number (addNum()): O(log N), where N is the total number of elements seen so far. This is because adding a number requires inserting it into one of the two heaps, which takes O(log N) time due to the internal balancing of the heaps.
Finding the median (findMedian()): O(1), as it only involves peeking at the top elements of the heaps.

Space Complexity:
The space complexity is O(N), where N is the total number of elements seen so far. This is because the heaps store all the elements, and the space required grows linearly with the number of elements added.
 */
