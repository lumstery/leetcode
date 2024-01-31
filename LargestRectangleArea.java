class Solution {


public int largestRectangleArea(int[] height) {
    int n = height.length;
    int[] nsr = new int[n];// idx of the first bar the left that is lower than current
    int[] nsl = new int[n];// idx of the first bar the right that is lower than current

    Stack<Integer> s = new Stack<>();

    for (int i = n - 1; i >= 0; i--) {
        while (!s.isEmpty() && height[i] <= height[s.peek()]) {
            s.pop();
        }
        if (s.isEmpty()) nsr[i] = n;
        else nsr[i] = s.peek();
        s.push(i);
    }

    while (!s.isEmpty()) s.pop();

    for (int i = 0; i < n; i++) {
            while (!s.isEmpty() && height[i] <= height[s.peek()]) {
                s.pop();
            }
            if (s.isEmpty()) nsl[i] = -1;
            else nsl[i] = s.peek();
            s.push(i);
        }


    int maxArea = 0;
    for (int i = 0; i < height.length; i++) {
        maxArea = Math.max(maxArea, height[i] * (nsr[i] - nsl[i] - 1));
    }

    return maxArea;
}


}
/*
 Approach: Use monotonic stack approach to fill 2 arrays
 idx of nearest bar to the left  which is lower than height[i] nsl
 idx of nearest bar to the right which is lower than height[i] nsr

then for any given bar we can calculate area by formula
area = barHeight * ( idx of nearest lower to the right - idx of nearest lower to the left - 1 )

Thus we iterate through array of all bars and find max area

Time Complexity: O(n)
Space Complexity: O(n)

*/
