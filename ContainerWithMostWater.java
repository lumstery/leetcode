class Solution {
    public int maxArea(int[] height) {
      int head = 0;
      int tail = height.length -1;
      
      int maxArea = 0;
      while(head < tail){
        
          int minHeight = Math.min(height[head], height[tail]);
          int widht = tail - head;
          int area = minHeight *  widht;

          maxArea = Math.max(maxArea, area);

          if(height[head] > height[tail]){
              tail--;
          }else{
              head++;
          }
      }

      return maxArea;
    }
}
/*
Approach: Gradually move two pointers untill they meet, and calculate area of each box using min height and widght between two vertical lines
Time complexity: O(n)
Space complexity: O(1)
*/
