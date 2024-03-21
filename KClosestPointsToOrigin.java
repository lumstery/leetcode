class Solution {

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Point> pq = new PriorityQueue<>(
           (p1, p2) -> Double.compare(distance(p2), distance(p1))
        );

        int n = points.length;

        // Add points to the priority queue
        for (int[] point : points) {
            pq.offer(new Point(point[0], point[1]));
            if (pq.size() > k) {
                pq.poll(); // Remove the farthest point
            }
        }
        
       // Convert the priority queue to the result array
        int[][] result = new int[k][2];
        int i = 0;
        while (!pq.isEmpty()) {
            Point p = pq.poll();
            result[i][0] = p.x;
            result[i][1] = p.y;
            i++;
        }


        return result;     
    }

    // Calculate Euclidean distance to origin (0,0 )
    double distance(Point p) {
        return Math.sqrt(p.x * p.x + p.y * p.y);
    }



    private static class Point{
        int x;
        int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
/*
 Approach: to find the k closest points to the origin, we need to keep k points that are closest to the origin, thus k min elements should be kept in the queue.

  We should sort/prioritize our elements in the queue in the way where we will 
  have largest/furthest distance at top of the queue. (descending/reverse order)

  And when queue reaches size of k, we just remove element from head ( furthest)

  e.g.
  if we have points with distances
 
  [ 4, 3, 2, 1] , so if we need k = 3 closest points, we will remove distance (4), and we will have just points with distances [3, 2, 1] in the queue. 

Time complexity: O(n log k) - because of PriorityQueue operations 
Space complexity: O(k) - Priorty Queue Size 
*/
