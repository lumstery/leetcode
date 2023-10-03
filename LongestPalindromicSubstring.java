class Solution {
 public String longestPalindrome(String s) {
    if (s == null || s.length() < 1) return "";
    int start = 0, end = 0;
    for (int i = 0; i < s.length(); i++) {
        int len1 = expandAroundCenter(s, i, i);
        int len2 = expandAroundCenter(s, i, i + 1);
        int len = Math.max(len1, len2);
        if (len > end - start) {
            start = i - (len - 1) / 2;
            end = i + len / 2;
        }
    }
    return s.substring(start, end + 1);
}

private int expandAroundCenter(String s, int left, int right) {
    int L = left, R = right;
    while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
        L--;
        R++;
    }
    return R - L - 1;
}
}
/*
 Approach: expanding around each index in string
 Time complexity: O(n^2) , as in worst case we need to expand n times for each string[i] with length of n
 Space complexity: O(1) as we don't use any additional collections

 Logic: We iterate through string, and on each index we try to expand around that index in two ways,
 1. assuming that we have palindromic string like 'aba' with some character in the middle, we start two pointers left, right
  that are pointing to the same index in the beginning, and then we move them by one index each until boundaries reached
  or characters on left and right pointer do not match
 2. assuming that we have palindromic string like 'abba' with some two same characters in the middle, we start two pointers left, right
  that are pointing to the sibling characters indices in the beginning,
   firstly we check if characters are the same,
   and then we move them by one index (towards left and right )
  each until boundaries reached or characters on left and right pointer do not match

then having lengths of these two variants we need to take max length of them, and if it's longer than previously found
the longest palindrome we are re-assigning, start and end indices for the new palindrome

 */
