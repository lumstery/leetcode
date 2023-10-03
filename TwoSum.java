class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap();
        for(int i = 0; i < nums.length; i++ ){
            map.put(nums[i], i);
        }
        
        for(int i = 0; i < nums.length; i++ ){
           int complement = target - nums[i];
           if(map.containsKey(complement) && map.get(complement) !=i ){
               return new int[]{i, map.get(complement)};
           } 
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
/*
Approach: one iteration -> to initialize auxilary map
          second iteration -> to find complement
Time complexity: O(n)
Space complexity: O(n)
Logic:
first of all we iterate trough array initialize map with key as number and value as index where number is located;

then with another iteration we are identifying diference between target and
given number nums[i] as complement number

and trying to locate such key in map that has value different than other index;

this logic guarantees us that we will use number from two different indices,
as we cannot use same element twice
*/
