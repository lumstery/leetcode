/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
   class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        // Step 1: Find the middle of the list
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Step 2: Reverse the second half
        ListNode prev = null, curr = slow, temp;
        while (curr != null) {
            // memorize next
            temp = curr.next;
            // replace curr.next with prev
            curr.next = prev;
            // memorize curr to prev, for next iteration
            prev = curr;
            // switch curr to memorized next
            curr = temp;
        }
        
        // Step 3: Merge the two halves
        ListNode first = head, second = prev;
        // while we have elements in 2nd reversed half
        while (second.next != null) {
            // memorize next of 1st half
            temp = first.next;
            // replace next by element from 2nd reversed half
            first.next = second;
            // move pointer in 1st half further
            first = temp;
            
            // memorize next of 2nd half pointer
            temp = second.next;
            // replace .next relation in 2nd half pointer
            second.next = first;
            // move pointer in 2nd half further
            second = temp;
        }
    }
}
/* 
  Approach: 1. Find the middle of the list using fast and slow pointers technique 2. reverse 2nd half of the list 3. merge 1st half with reversed 2nd half

  Time Complexity: O(n)
  Space Complexity: O(1) 

*/
