class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
      // sort by end time of interval
      Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

     
     int prev = 0;
     // we assume that first interval/meeting is attended
     int count = 1;

    for (int i = 1; i < n; i++) {
        // if new interval is not conflicting with previously attended
        // e.g. [1...3][4...5]  
        //        prev   new
        // start of new interval 4 > 3 , grater than end of previously attended
        if (intervals[i][0] >= intervals[prev][1]) {
            // we store that we have attended current meeting
            prev = i;
            // we increment count of non-conflicting meetings that were attended
            count++;
        }
    }
    // return number of conflicting/overlapping meetings
    return n - count;
    }
}
/*
 Approach: We treat intervals as meetings
 our task is to find all meetings that we can attend (they do not overlap)

 We sort all intervals by end times, and we "attend" first interval
 Then we are iterating through all intervals, and if given interval overlaps
 with previously attended meeting, we just skip it and move on to the next 
 meeting that we can attend, and so on.

Every time when we are able to "attend" some meeting we increment counter.

So number of intervals that we have to remove is equal to the number
of meetings that we are not able to attend, because they have conflict with 
other meetings.

Thus we have to remove n - (number of non-conflicting meetings) intevals

Time Complexity: O(n)
Space Complexity: O(1)

*/
