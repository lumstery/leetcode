class Solution {
    
    public int lastStoneWeight(int[] stones) {
      PriorityQueue<Integer> pq = new PriorityQueue<>(
        Collections.reverseOrder()
      );

      for(int stone: stones){
        pq.add(stone);
      }    

      while(pq.size() >= 2){
        int y = pq.poll();
        int x = pq.poll();

        if(x != y){
            pq.add(y - x);
        }
      }

      if(pq.size() > 0 )  return pq.poll();

      return 0;  
    }
}
/*
 Approach: Fill priority queue (reverse order) with all stones
 and then poll two largest stones from head of queue and simulate smashing logic
 untill only one or no stones are left

Time complexity: 
 the overall time complexity is dominated by the priority queue operations, resulting in O(n * log(n)).

Space complexity: O(n) as queue stores at most n elements in the begining 

*/
