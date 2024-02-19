class Solution {
    public String minWindow(String s, String t) {
       if (t.length() == 0 || t.length() > s.length()) {
            return "";
        }

        String result = "";

        int[] chars = new int[128];
        int[] t_chars = new int[128];

        for (char c : t.toCharArray()) {
            t_chars[c]++;
        }

        int l = 0;
        int min_length = Integer.MAX_VALUE;
        int requiredChars = t.length();

        for(int r = 0; r < s.length(); r++){
            if (t_chars[s.charAt(r)] > 0) {
                requiredChars--;
            }

            t_chars[s.charAt(r)]--;

            while (requiredChars == 0) {
                if (r - l + 1 < min_length) {
                    min_length = r - l + 1;
                    result = s.substring(l, r + 1);
                }

                t_chars[s.charAt(l)]++;
                if (t_chars[s.charAt(l)] > 0) {
                    requiredChars++;
                }

                l++;
            }
        }

        return result;
    }
}
/*
 Approach: Sliding window moves to the right untill we find all required characters, and then we shrink left side untill we still have all necessary characters, thus with every iteration we can identify smallest window that has all necessary characters
 Time Complexity: O (m + n)
 Space Complexity: O (1)

*/
