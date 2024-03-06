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
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head.next == null){
            return head;
        }
        if(k == 1){
            return head;
        }

        ListNode dummy  = new ListNode();
        dummy.next = head;

        // will hold pointer of element before group
        ListNode prev = dummy;
        // current pointer for iteration
        ListNode cur = head;


        while(cur != null){
            boolean hasSpace = hasSpace(k, cur);

            if(hasSpace){
                // it will store last element of the group,
                // so next iteration can use it
                prev = reverse(prev, cur, k);
                // we jump to position after last element
                // in a processed group here
                cur = prev.next;
            }else{
                break;
            }         
       }

       return dummy.next;
    }

    private boolean hasSpace(int k, ListNode cur){
        for(int i = 0; i < k; i++){
            if(cur == null) return false;
            cur = cur.next;
        }
        return true;
    }

    private ListNode reverse(ListNode prev, ListNode cur, int k){
        for(int i = 0; i < k -1; i++){
            // let assume we have to permute 3 and 4
            // 1 2 3 4 5
            //   p c n
            // 1 2 4 3 5
            // memorize next element
            // we memorize 4
            ListNode next = cur.next;
            // 3 starts to point to 5 instead of 4 
            cur.next = next.next;
            // 4 starts to point 3 
            next.next = prev.next;
            // 2 starts to point to 4
            prev.next = next;
            // thus 2 -> 4 -> 3 -> 5 instead of 2 -> 3 -> 4 -> 5
        }
        // cur will hold last element of a group
        return cur;
    }
}
/*
 Approach: iterate through the list in chunks/steps, where we 
 identify whethere there is enough space remains = k, so that we could
 take that chunk and reverse it, and then simply reverse.

 Time Complexity:  O(n)
 Space Complexity: O(1)
*/
