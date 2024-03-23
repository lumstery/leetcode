class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> result = new LinkedList<>();
        
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        int[] interval = intervals[0];
        int i = 1;
        
        while(i < intervals.length){
           int[] cur = intervals[i];
           
           if(cur[0] <= interval[1]){
            interval[0] = Math.min(cur[0], interval[0]);
            interval[1] = Math.max(cur[1], interval[1]);
           }else{
            result.add(interval);
            interval = cur;
           }
           i++;
        }
        result.add(interval);

        return result.toArray(new int[result.size()][]);
    }
}
/*
 Approach: Iterate through all intervals sorted by start, use variable to carry
  previous interval and if it overlaps with current we merge them 
  otherwise we push previously combined interval start building new one

 Time Complexity:  O(n)
 Space Complexity: O(n)
*/
