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
       public ListNode removeNthFromEnd(ListNode head, int n) {
            int childCount = traverse(head, n) + 1;
            if(childCount == n){
                return head.next;
            }
            return head;
        }

        public int traverse(ListNode node, int n) {
            if (node.next == null) {
                return 0;
            }

            int childCount = traverse(node.next, n) + 1;
            
            if (childCount == n) {
                node.next = node.next.next;
            }
            
            return childCount;
        }
}
/*
 Approach: We use recursion and make DFS traversal, once we reach the end of
 the list we start counting child nodes, once we reach a node that has
 exactly n childs, we understand that it is the previous node before our target n-th node from the tail. Then we just get rid of n-th node from the tail and returning child count at the same time.

 We have  edge case:
 when n = number of nodes  
       -> it means that we need to remove head
            -> thus we return head.next
Time Complexity: O(N) -> number of nodes in the list
Space Complexity: O(N) call stack

*/
