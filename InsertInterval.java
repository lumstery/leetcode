class Solution {
   public int[][] insert(int[][] intervals, int[] newInterval) {
		List<int[]> result = new LinkedList<>();
	    int i = 0;
	    // add all the intervals ending before newInterval starts
	    while (i < intervals.length && intervals[i][1] < newInterval[0]){
	        result.add(intervals[i]);
	        i++;
	    }
	    
	    // merge all overlapping intervals to one considering newInterval
	    while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
	    	// we could mutate newInterval here also
	        newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
	        newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
	        i++;
	    }
	    
	    // add the union of intervals we got
	    result.add(newInterval); 
	    
	    // add all the rest
	    while (i < intervals.length){
	    	result.add(intervals[i]); 
	    	i++;
	    }
	    
	    return result.toArray(new int[result.size()][]);
    }
}
/*
 Approach: 
   1. take all intervals that end before our new interval starts
   2. merge all intervals that match criteria
         any_interval_start >=  new_interval_start
         any_interval_end <=  new_interval_end
   3. add the rest of intervals

Example:

Step 1: Add intervals ending before the new interval starts.

Existing intervals:   [1---3] [6---9] [11---15]
New interval:                [4-----10]     

In this step, we find the interval [1---3], which ends before the start of the new interval [4---10]. So, we add this interval to our result.

Result: [1---3]

Step 2: Merge overlapping intervals with the new interval.
Existing intervals:   [1---3] [6---9] [11---15]
New interval:               [4------10]

We find the intervals that overlap with the new interval, which is [6---9]. We merge these intervals with the new interval, resulting in [4----10].

Step 3: Add the union of intervals. 
Resulting intervals:  [1---3] [4-------10]


Step 4: Add the remaining intervals.

Finally, we add the remaining intervals, which are [11---15], to our result
Resulting intervals:  [1---3] [4-------10] [11---15]

Time complexity of the solution is O(N)
Space complexity is also O(N)

*/
