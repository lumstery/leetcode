
public class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        int n = queries.length;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        
        //map query and corresponding index
        int[][] queriesWithIndex = new int[n][2];
        for(int i = 0; i < n; i++){
            queriesWithIndex[i] = new int[]{queries[i], i};
        }
        Arrays.sort(queriesWithIndex, Comparator.comparingInt(a -> a[0]));


        //sort intervals in increasing order of size
        PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>((a, b) -> ((a[1] - a[0]) - (b[1] - b[0])));

        int[] result = new int[n];

        int j = 0;

        for(int i = 0; i < queries.length; i++){
            int queryVal = queriesWithIndex[i][0];
            int queryIndex = queriesWithIndex[i][1];

  //add all the intervals which start is less or equal than current query value 
            while(j < intervals.length && intervals[j][0] <= queryVal){
                minHeap.add(intervals[j]);
                j++;
            }
            
 //remove all the smallest size intervals which end val is less than current query value
            while(!minHeap.isEmpty() && minHeap.peek()[1] < queryVal){
                minHeap.remove();
            }
            //now if heap is empty it means there is no interval which satisfy query val
            //othewise smallest size interval is our answer
            result[queryIndex] = minHeap.isEmpty() ? -1 : (minHeap.peek()[1] - minHeap.peek()[0] + 1);
        }
        return result;
    }
}
/*
 Approach: 
 

1.  sort the intervals based on their start times.
2.  create an array with queries and their corresponding indices, sorted by query value.
3. initializes a priority queue (minHeap) to store intervals sorted by their size.
4. initializes an array to store the result.
5. It iterates through each query.
6. For each query, add intervals whose start time is less than or equal to the current query value to the minHeap
7. Remove those intervals from minHeap whose end time is less than the current query value.
8. Finally, it stores the result in the array, where the result is the smallest size interval that satisfies the query value, or -1 if no such interval exists.

Time Complexity: O((n + m) log n), where n is the number of intervals and m is the number of queries. Sorting the intervals and queries takes O(n log n) and O(m log m) respectively. Then, iterating through queries and intervals takes O(n + m), as each interval and query is processed once.

Space Complexity: O(n + m), where n is the number of intervals and m is the number of queries. Additional space is used for sorting arrays and storing results, both proportional to the number of intervals and queries.

*/
