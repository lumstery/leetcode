class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s2.length()) return false;

        int[] chars = new int[26];
        int[] s1_chars = new int[26];

        for(int i = 0; i < s1.length(); i++){
            s1_chars[s1.charAt(i) - 'a']++;
        }

        int l = 0;

        for(int r = 0; r < s2.length(); r++){
            
            chars[s2.charAt(r) - 'a']++;

            if(r - l + 1 == s1.length()){
                if(Arrays.equals(chars, s1_chars)){
                    return true;
                }else {
                    chars[s2.charAt(l) - 'a']--;
                    l++;
                }
            } 

        }
     return false;
    }
}
/*
  Approach: Sliding window to check every window which cound potentially contain substring s1, checking for window validity is done by comparison of chars array ( char cound array holding counts of chars in current window) and chars_s1 array holding count of chars in the target s1 string;

Time Complexity: O(N) 
Space Complexity: O(1)


*/
