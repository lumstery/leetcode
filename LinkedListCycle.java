/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode walker = head;
        ListNode runner = head;
        while(runner!=null && runner.next!=null) {
            walker = walker.next;
            runner = runner.next.next;
            if(walker==runner) return true;
        }
        return false;
    }
}
/*
  Approach: Fast and slow pointers are used, if runner will ever meet walker then it means we have cycle
  Time Complexity: O(N) 
*/
