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

/**
 * The Solution class implements a method to reverse a singly-linked list.
 */
class Solution {

    /**
     * Reverses a given singly-linked list.
     *
     * @param head The head of the original list.
     * @return The head of the reversed list.
     */
    public ListNode reverseList(ListNode head) {
        // Will store the previous node for each iteration, initially set to null.
        ListNode prev = null;
        
        // Used for iteration, initially points to the head of the list.
        ListNode curr = head;


        // Iterate until we reach the end of the list.
        while (curr != null) {

            // Memorize the next node, as the link between curr and next will be dropped.
           ListNode next =  curr.next;

            // Redirect the "next" pointer of the current node to the previous node.
            // Prev is null at the first iteration, but it will store the curr node from the previous iteration which we have stashed.
            curr.next = prev;  

            // The current node becomes assigned to the prev pointer, we stash it for the next iteration.
            prev = curr;    

            // Move to the next node in the list.
            curr = next;    
        }

        return prev;
    }
}
/*
 Approach: We iterate through the list with assignment of curr = curr.next
  for every iteration,  for every curr node we do the following steps:

1. take "curr.next" pointer and stash it to be able to continue with iteration, and free up
    curent's element next slot. It willl be used at step 4. 
2. as "curr.next" was kind of freed up, we now can assign "curr.next" to the "prev" pointer, prev pointer is going to be stashed at step 3.
3. once we are done with re-assigment of "curr.next" in every iteration
    3.1 we are making sure that we will stash our "curr" for the next iteration, and in the future, during subsequent iteration we are going to have "prev" pointing to the "curr" from the past iteration, and thus we will be able to do step 2 in the next iteration.
    3.2. prev pointer is initially null, but it's fine as our head becomes tail that points to nothing
4. finally, as we've  reassigned curr.next, stashed "curr" to the "prev" pointer for the next iteration, we are fine with assigning our next node to "curr" pointer and  move on with subsequent iterations.

Time complexity: O(n)
Space complexity: O(1)
 
*/
