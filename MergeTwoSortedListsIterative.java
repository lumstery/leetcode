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
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) return l2;
            if (l2 == null) return l1;
            
            // Node for filling of result list
            ListNode curr = new ListNode(0);
            // Dummy node for the result
            ListNode result = curr;

            // While pointers the the elements of both lists are valid
            while(l1 != null && l2 !=null){
                
                // we pick smaller value and assign it to our curr.next
                // so that we fill up our result list
                if(l1.val < l2.val){
                    curr.next = l1;
                    l1 = l1.next;
                }else{
                    curr.next = l2;
                    l2 = l2.next;
                }
                
                // we move our curr pointer to the next, to keep filling 
                // resulting list properly 
                curr = curr.next;
            }

            // if any of the lists finished before another, then we just append 
            // another list to our resulting list, as remaining part is going to 
            // be bigger than anything else encountered before in past iterations
            // as we were picking smallest values always for our result list
            if (l1 == null) {
                curr.next = l2;
            } else {
                curr.next = l1;
            }


            // we use our dummy node next to get pointer to the head of result
            return result.next;
        }
}
/* 
 Approach: We iterate through two lists and compare values from two pointers
 l1 and l2, we try to get smaller value and append it to result list.

 Once any of the lists ends earlier than another, we fille the rest of result list with the remaining part of longer list.

 Time complexity: O(n)
 Space complexity: O(n)
*/
