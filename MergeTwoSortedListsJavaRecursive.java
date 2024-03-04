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
            if (l1.val < l2.val) {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            }
        }
}
/*
  Approach: To solve this recursively we need to identify base case;
  l1 is null (we reached end in l1) -> we try l2
  l2 is null (we reached end in l2) -> we try l1

  l1.val < l2.val ->  we take l1,     but before returning it we make recursive call to mergeTwoLists where we move are probing l1.next vs l2 as candidate for l1.next
  l1.val not < l2.val ->  we take l2, but before returning it we make recursive call to mergeTwoLists where we move are probing l1 vs l2.next as candidate for l2.next

  the key here is that in order to avoid stack overflow we need to call mergeTwoLists(l1.next, l2) and mergeTwoLists(l1, l2.next) in mirorr-like way,
  when parameters are passed not in the same order.

  Time complexity: O(n)
  Space complextiy: O(n) call stack size
 
  where n is size of both l1 and l2  combined
  
*/
