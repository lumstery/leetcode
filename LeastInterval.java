import java.util.Arrays;

class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];

        for (char task : tasks) {
            freq[task - 'A']++;
        }

        Arrays.sort(freq);
        int maxFreq = freq[25] - 1;
        int idleSlots = maxFreq * n;

        for (int i = 24; i >= 0; i--) {
            idleSlots -= Math.min(maxFreq, freq[i]);
        }

        return idleSlots < 0 ? tasks.length : tasks.length + idleSlots;
    }
}
/*
 Approach: initialize array of frequencies of all tasks, sort it
 and we will get most frequent task at last index of array

 then we just calculate max_freq as frequency of most frequent task - 1
we substract 1 to account for the last execution of certain task, after which we don't need to have idle times

then we calculate the how many idle slots would we need to execute most frequent task
once we have value of our idle_slots we can iterate backwards from most frequent task
to least frequent, and just subsctract those frequencies from idle_slots, as we will
basically fill those slots with certain tasks.

we have Math.min(maxFreq, freq[i]) because 
if the max value is present more than once in freq array, there can be a case where some freq element is greater than max_value-1

for instance
[ 0,0 .. 4 , 4]

then max_freq will be = 3, and we should "truncate" 2nd frequency from the end so that 
it's not bigger than max_freq.

eventually we will have number of idle slots that is still left after filling of all the tasks, and if we still have idle slots, then we need to execute tasks.length + idle_slots intervals, othewise just tasks.length as of idle_slots <=0 it means we would be able
to schedule all tasks in such a way where we do not need idle intervals between executions of any tasks.

Time Complexity: O(n)
Space Complexity: O(1)
*/
