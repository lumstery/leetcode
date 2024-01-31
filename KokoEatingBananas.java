class Solution {
  public int minEatingSpeed(int[] piles, int h) {
        int start = 1;
        int end = 1000000000;
        
        while(start < end){
            int mid = start + (end - start) / 2;
            if(canEatInTime(piles, mid, h)) end = mid; // we try to search even smaller rate 
            else start = mid + 1; // this rate is too small, lets try bigger
        }
        return start; // smallest possible rate of eating
    }
    public boolean canEatInTime(int piles[], int eatingRate, int h){
        int totalHoursNeeded = 0;
        for(int pile : piles){
            int hoursPerPile = pile / eatingRate;
            totalHoursNeeded += hoursPerPile;
            if(pile % eatingRate != 0) totalHoursNeeded++;
        }
        return totalHoursNeeded <= h;
    }
}
/*
 Approach: Binary search of value K such that we are able to eat all piles of bananas in h hours, if value L is > than our h then rate of eating of banans is too slow, so we need to move our search window of rate to the right half, otherwise search window of the rate moves to the left half.

Time Complexity O(N * log(M)) where N is no of piles & M is range of K (left to right)
Space Complexity: O(1)
*/
