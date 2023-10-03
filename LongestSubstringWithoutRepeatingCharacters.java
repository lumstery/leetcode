class Solution {
    public int lengthOfLongestSubstring(String s) {
     Set<Character> set = new HashSet();
     int max = 0;
     int left = 0;
     int right  = 0;

     while(right < s.length()){
        if(!set.contains(s.charAt(right))){
            set.add(s.charAt(right));
            right++;
            max = Math.max(max, set.size());
        }else{
            set.remove(s.charAt(left));
            left++;
        }
     }

     return max;
    }
}
/*
Approach: use sliding window with two pointers left and right
Time Complexity: O(n)
Space complexity: O(k), k number of unique characters present in set

Logic:  We iterate through string using right pointer, if set does not contain character at right pointer, we add this char to set and update value of max to the current size of set.

If set already contains value at right pointer then we need to remove character from set and increment left pointer, thus left boundary of the  window slides to the right.

During next iteration, as we've removed character that was already present in the set, it become unknown again from the perspective of our set, thus we add it again to the set and proceed moving rigth boundary of sliding window.

*/
