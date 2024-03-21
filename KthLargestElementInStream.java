class KthLargest {
    PriorityQueue<Integer> pq;
    int k = 0;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<>();
        for(int i: nums){
            this.add(i);
        }
    }
    
    public int add(int val) {
        pq.add(val);

        if(pq.size() > k){
            pq.remove();
        }
        return pq.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */

 /*
   Approach: we have priority queue for storing integers in sorted order
   head of queue will always store largest elements

   By defailt PriorityQueue<Integer> will sort numbers in natural asc order

   like [ 1, 2 , 3, 4 ]

   so we add all elements from array, and when queue size is bigger than k
   we just remove element from the head of queue ( smallest element ).

   e.g.

   queue [ 1, 2 , 3, 4 ], k = 3
   we remove 1 from top of the queue and we have [2, 3, 4] 

   in the end of all iterations we will have our queue with size of k, and element on the head will be our k-th smallest element.

  Time complexity for adding an element or initializing the KthLargest object is
    O(log k).
  Space complexity is O(k).
   
 */
