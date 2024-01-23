class Solution {
        public int[] dailyTemperatures(int[] temperatures) {
            int n = temperatures.length;
            int[] result = new int[n];
            Stack<Integer> stack = new Stack<>();

            for (int i = n - 1; i >= 0; i--) {
                while (!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()]) {
                    stack.pop();
                }
                result[i] = stack.isEmpty() ? 0 : stack.peek() - i;
                stack.push(i);
            }

            return result;
        }
}
/*
 Approach: we use reverse iteration through array, and use  monotinic stack to store "next closest warmer day" 

 For every arr[i] we are :
 1. removing indices from top of the stack stack[top] 
 1where arr[i] >= arr[stack[top]] (future temperature is colder)
untill we reach some arr[stack[top]], which is actually warmer than our current day.

 2. If we don't have any future warmer day, stack will be empty and thus we will store 0 as result 

If we do have some warmer day, then we calculate distance between i and stack[top] by formula: distance = stack[top] - i;
 
 3. We push current temperature to the top of stack 

This template is commonly used for monotonic stacks problems

In this way, at every i-th iteration, top of the stack stores "next closest warmer day" as of i+1-th day

 When we process every previous day, we know that top of the stack stores minimum among maximums which were found so far (during traversing from future to past).

 If certain minumum among maximums is actually colder than our current day, we just throw it away, and try to compare with next "minumum among maximums" , and so on .  Once we have found such "minumum among maximums" that is actuall higher than our current temperature, it means that we have found closest warmer day.

 After that we can push our current temperature to the top of the stack, so that it will now store proper  "minumum among maximums" for the next iteration.

 Time complextity: O(n)
 Space complexity: O(n)

*/
